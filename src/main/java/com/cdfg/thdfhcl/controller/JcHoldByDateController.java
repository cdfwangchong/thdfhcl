package com.cdfg.thdfhcl.controller;

import com.cdfg.thdfhcl.pojo.dto.JcHoldByDateDto;
import com.cdfg.thdfhcl.pojo.dto.JcThDto;
import com.cdfg.thdfhcl.pojo.until.JcXsdbillEntity;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.Token;
import com.cdfg.thdfhcl.service.JcHoldByDateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";
        logger.info("获取到暂存提货单查询接口的工号："+worknumber);
        return new Result<List<JcXsdbillEntity>>(sucCode,sucMsg,hbdService.qryCheckBill(hbdDto,worknumber));
    }

    /**
     * 寄存日期暂存接口
     * @return
     */
    @PostMapping("/insertJcRqzc")
    @ResponseBody
    public Result<String> insertDts(@RequestBody JcHoldByDateDto hbdDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";

        String ctpCode = hbdService.insertDts(hbdDto,worknumber);
        return new Result<String>(sucCode,sucMsg,ctpCode);
    }
    /**
     * 寄存日期暂存接口
     * @return
     */
    @PostMapping("/qryJcThBill")
    @ResponseBody
    public Result<List<JcXsdbillEntity>> qryJcThBill(@RequestBody JcHoldByDateDto hbdDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";

        List<JcXsdbillEntity> JcThlist = hbdService.qryJcThBill(hbdDto,worknumber);
        return new Result<List<JcXsdbillEntity>>(sucCode,sucMsg,JcThlist);
    }

    /**
     * 寄存日期暂存接口
     * @return
     */
    @PostMapping("/updateJcTh")
    @ResponseBody
    public Result<String> updateJcTh(@RequestBody JcThDto jcthDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
      String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";
        Map retmap = hbdService.updateJcTh(jcthDto,worknumber);
        String flag = (String) retmap.get("ret_flag");
        int ret_flag = Integer.parseInt(flag);
        String ret_msg = (String) retmap.get("ret_msg");
        return new Result<String>(ret_flag,ret_msg,"");
    }
}
