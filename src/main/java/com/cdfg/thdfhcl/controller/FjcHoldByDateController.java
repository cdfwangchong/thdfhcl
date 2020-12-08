package com.cdfg.thdfhcl.controller;

import com.cdfg.thdfhcl.pojo.dto.HoldByDateDto;
import com.cdfg.thdfhcl.pojo.dto.XsdBillDto;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.Token;
import com.cdfg.thdfhcl.service.FjcHoldByDateService;
import com.cdfg.thdfhcl.service.HoldByDateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.sucCode;
import static com.cdfg.thdfhcl.pojo.until.Constant.sucMsg;

/**
 * project:分拣仓日期暂存
 * anthor:wangchong
 * time:2020/12/06
 */

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
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";

        String ctpCode = fhbdService.insertDts(xsdbillDto,worknumber);
        return new Result<String>(sucCode,sucMsg,ctpCode);
    }
}
