package com.cdfg.thdfhcl.controller;

import com.cdfg.thdfhcl.pojo.dto.HoldByDateDto;
import com.cdfg.thdfhcl.pojo.dto.JcHoldByDateDto;
import com.cdfg.thdfhcl.pojo.until.JcXsdbillEntity;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.service.JcHoldByDateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.cdfg.thdfhcl.pojo.until.Constant.sucCode;
import static com.cdfg.thdfhcl.pojo.until.Constant.sucMsg;


/**
 * 寄存包裹日期暂存
 */
@CrossOrigin
@RequestMapping("/cdfg")
@RestController
public class JcHoldByDateController {
    @Autowired
    JcHoldByDateService hbdService=null;
    Logger logger = Logger.getLogger(JcHoldByDateController.class);
    /**
     * 提货单查询接口
     * @return
     */
    @PostMapping("/qryJcBill")
    @ResponseBody
    public Result<List<JcXsdbillEntity>> qryCheckBill(@RequestBody JcHoldByDateDto hbdDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
//        String worknumber = new Token().CheckToken(token);
        String worknumber = "3859";
        logger.info("获取到暂存提货单查询接口的工号："+worknumber);
        return new Result<List<JcXsdbillEntity>>(sucCode,sucMsg,hbdService.qryCheckBill(hbdDto));
    }

    /**
     * 邮寄包裹日期暂存接口
     * @return
     */
    @PostMapping("/insertJcRqzc")
    @ResponseBody
    public Result<String> insertDts(@RequestBody JcHoldByDateDto hbdDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
//        String worknumber = new Token().CheckToken(token);
        String worknumber = "3859";

        String ctpCode = hbdService.insertDts(hbdDto,worknumber);
        return new Result<String>(sucCode,sucMsg,ctpCode);
    }
}
