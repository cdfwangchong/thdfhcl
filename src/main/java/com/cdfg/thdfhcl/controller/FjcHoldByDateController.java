package com.cdfg.thdfhcl.controller;

import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.pojo.dto.BillDto;
import com.cdfg.thdfhcl.pojo.dto.ThdfjglDto;
import com.cdfg.thdfhcl.pojo.dto.XsdBillDto;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.Token;
import com.cdfg.thdfhcl.service.FjcHoldByDateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;
import static com.cdfg.thdfhcl.pojo.until.Constant.errMsg_5;

/**
 * project:分拣仓日期暂存
 * anthor:wangchong
 * time:2020/12/06
 */
@CrossOrigin
@RequestMapping("/cdfg")
@RestController
public class FjcHoldByDateController {
    @Autowired
    FjcHoldByDateService fhbdService=null;
    Logger logger = Logger.getLogger(HoldByDateController.class);
    /**
     * 提货单查询接口
     * @return
     */
    @PostMapping("/qryxsdNo")
    @ResponseBody
    public Result<Map> qryCheckBill(@RequestBody XsdBillDto xsdbillDto, HttpServletRequest request) {
        if (xsdbillDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
      String token = request.getHeader("Authorization");
      String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";
        logger.info("获取到邮寄提货单查询接口的工号："+worknumber);
        Map retMap = fhbdService.qryCheckBill(xsdbillDto);
        return new Result<Map>(sucCode,sucMsg,retMap);
    }

    /**
     * 邮寄包裹日期暂存接口
     * @return
     */
    @PostMapping("/insertRqzc")
    @ResponseBody
    public Result<String> insertDts(@RequestBody XsdBillDto xsdbillDto, HttpServletRequest request) {
        if (xsdbillDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
      String token = request.getHeader("Authorization");
      String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";

        String ctpCode = fhbdService.insertDts(xsdbillDto,worknumber);
        return new Result<String>(sucCode,sucMsg,ctpCode);
    }

    /**
     * 邮寄包裹日期暂存接口
     * @return
     */
    @PostMapping("/qryZcsd")
    @ResponseBody
    public Result<Map> qryZcsd(@RequestBody BillDto billDto, HttpServletRequest request) {
        if (billDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        String token = request.getHeader("Authorization");
        new Token().CheckToken(token);

        Map retmap = fhbdService.qryZcsd(billDto);
        return new Result<Map>(sucCode,sucMsg,retmap);
    }

    /**
     * 提货单分拣管理接口
     * @return
     */
    @PostMapping("/thdfjgl")
    @ResponseBody
    public Result<Map> thdfjgl(@RequestBody ThdfjglDto thdfjglDto , HttpServletRequest request) {
        if (thdfjglDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
//        String token = request.getHeader("Authorization");
//        String worknumber = new Token().CheckToken(token);
        String worknumber = "3859";

        Map retmap = fhbdService.thdfjgl(thdfjglDto,worknumber);
        String flagStr = (String) retmap.get("ret_flag");
        String ret_msg = (String) retmap.get("ret_msg");
        int ret_flag = Integer.parseInt(flagStr);
        retmap.remove("ret_flag");
        retmap.remove("ret_msg");
        return new Result<Map>(ret_flag,ret_msg,retmap);
    }
}
