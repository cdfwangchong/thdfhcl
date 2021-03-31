package com.cdfg.thdfhcl.controller;

import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.pojo.dto.*;
import com.cdfg.thdfhcl.pojo.until.*;
import com.cdfg.thdfhcl.service.CustPickService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;

/**
 * 顾客提货
 */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class CustPickController {

    @Autowired
    CustPickService cpService;

    Logger logger = Logger.getLogger(CustPickController.class);

    /**
     * 提货单查询接口
     * @param ciDto
     * @return
     */
    @PostMapping("/qryCustPickInfo")
    @ResponseBody
    public Result<CustBillEntity> qryCustPickInfo(@RequestBody CardidDto ciDto, HttpServletRequest request) {
        if (ciDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        String token = request.getHeader("Authorization");
        new Token().CheckToken(token);

        CustBillEntity cbEntity = cpService.qryCustPickInfo(ciDto.getCardId());
        if (cbEntity == null) {
            logger.error("顾客提货查询客人航班、提货单信息返回值为空");
            throw new ThdfhclNotFoundException(errCode_22,errMsg_22);
        }else {
            return new Result<CustBillEntity>(sucCode,sucMsg,cbEntity);
        }
    }

    /**
     * 修改航班
     * @param mfDto
     * @param request
     * @return
     */
    @PostMapping("/updateModifyFlight")
    @ResponseBody
    public Result<CustBillEntity> updateModifyFlight(@RequestBody ModifyFlightDto mfDto, HttpServletRequest request) {
        if (mfDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";
        CustBillEntity cbEntity = cpService.updateModifyFlight(mfDto,worknumber);

        return new Result<CustBillEntity>(sucCode,sucMsg,cbEntity);
    }

   /**
     * 扫描登机牌
     * @param bpDto
     * @return
     */
   @PostMapping("/scanDjp")
   @ResponseBody
    public Result<Map> scanDjp(@RequestBody BoardpassDto bpDto, HttpServletRequest request) {
        if (bpDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
       String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//       String worknumber = "3859";
       String flag = cpService.scanDjp(bpDto);
       Map<String,String> retparam=new HashMap<String,String>();
       retparam.put("flag",flag);
       return new Result<Map>(sucCode,sucMsg,retparam);
    }

    /**
     * @param cpDto
     * @param request
     * @return
     */
    @PostMapping("/custPick")
    @ResponseBody
    public Result<String> custPick(@RequestBody CustPickDto cpDto, HttpServletRequest request) {
        if (cpDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";
        cpService.custPick(cpDto,worknumber);
        String billnos = null;
        List<SellBillEntity>sbelist =  cpDto.getSellhead();
        for (SellBillEntity sbentity:sbelist) {
            if (billnos == null) {
                billnos = sbentity.getBillNo();
            }else
            {
                billnos = billnos+","+sbentity.getBillNo();
            }
        }
        return new Result<String>(sucCode,sucMsg,billnos);
    }

    /**
     * @param flightDto
     * @param request
     * @return
     */
    @PostMapping("/qrythdd")
    @ResponseBody
    public Result<ThddEntity> custPick(@RequestBody FlightDto flightDto, HttpServletRequest request) {
        if (flightDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";
        ThddEntity thddEntity = cpService.qryThdd(flightDto);

        return new Result<ThddEntity>(sucCode,sucMsg,thddEntity);
    }
}
