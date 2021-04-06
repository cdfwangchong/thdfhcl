package com.cdfg.thdfhcl.controller;

import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.pojo.dto.JcInsertShelfDto;
import com.cdfg.thdfhcl.pojo.dto.JcQrySxdnoDto;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.Token;
import com.cdfg.thdfhcl.service.JcShelfServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;
import static com.cdfg.thdfhcl.pojo.until.Constant.sucMsg;

/**
 * 寄存包裹上架
 */
@CrossOrigin
@RequestMapping("/cdfg")
@RestController
public class JcShelfController {
    @Autowired
    JcShelfServer jsServer;
    Logger logger = Logger.getLogger(JcShelfController.class);

    @PostMapping("/insertShelf")
    @ResponseBody
    public Result<String> insertShelf(@RequestBody JcInsertShelfDto jisDto, HttpServletRequest request) {
        if (jisDto == null) {
            logger.info("寄存上架下架接口获取到的对象值为空");
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";
        String ret_flag = jsServer.insertShelf(jisDto,worknumber);
        if ("1002".equals(ret_flag)) {
            return new Result<String>(sucCode,sucMsg,ret_flag);
        }else {
            logger.info("寄存上架下架接口写入失败");
            throw new ThdfhclNotFoundException(errCode, "寄存上架下架接口写入失败");
        }
    }

    @PostMapping("/qryJcxsdNo")
    @ResponseBody
    public Result<JcQrySxdnoDto> qryxsdNo(@RequestBody JcQrySxdnoDto jqsDto, HttpServletRequest request) {
        if (jqsDto == null) {
            logger.info("寄存上架提货单查询接口获取到的对象值为空");
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
//        String worknumber = "3859";
        String billNo = jqsDto.getXsdno();
        if (billNo == null) {
            logger.info("寄存上架提货单查询接口传入的提货单号为空");
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        JcQrySxdnoDto ret_jqsDto = jsServer.qrySxdno(billNo,worknumber);
        if (ret_jqsDto == null) {
            logger.info("寄存上架提货单查询返回值为空");
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }else {
            return new Result<JcQrySxdnoDto>(sucCode,sucMsg,ret_jqsDto);
        }
    }
}
