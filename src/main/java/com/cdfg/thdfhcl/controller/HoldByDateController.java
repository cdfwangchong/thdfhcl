package com.cdfg.thdfhcl.controller;

import com.cdfg.thdfhcl.pojo.dto.HoldByDateDto;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.Token;
import com.cdfg.thdfhcl.service.HoldByDateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.cdfg.thdfhcl.pojo.until.Constant.sucCode;
import static com.cdfg.thdfhcl.pojo.until.Constant.sucMsg;

/**
 * 邮寄包裹日期暂存
 */
@CrossOrigin
@RequestMapping("/cdfg")
@RestController
public class HoldByDateController {
    @Autowired
    HoldByDateService hbdService=null;
    Logger logger = Logger.getLogger(HoldByDateController.class);
    /**
     * 提货单查询接口
     * @return
     */
    @PostMapping("/qrycheckbill")
    @ResponseBody
    public Result<String> qryCheckBill(@RequestBody HoldByDateDto hbdDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";
        logger.info("获取到邮寄提货单查询接口的工号："+worknumber);
        return new Result<String>(sucCode,sucMsg,hbdService.qryCheckBill(hbdDto));
    }

    /**
     * 邮寄包裹日期暂存接口
     * @return
     */
    @PostMapping("/insertdts")
    @ResponseBody
    public Result<String> insertDts(@RequestBody HoldByDateDto hbdDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";

        String ctpCode = hbdService.insertDts(hbdDto,worknumber);
        return new Result<String>(sucCode,sucMsg,ctpCode);
    }
}
