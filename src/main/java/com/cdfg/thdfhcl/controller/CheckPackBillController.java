package com.cdfg.thdfhcl.controller;

import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.pojo.dto.InsertPackBillDto;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.Token;
import com.cdfg.thdfhcl.service.CheckPackBillService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;
import static com.cdfg.thdfhcl.pojo.until.Constant.sucMsg;

/**
 * 机场到货验收
 */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class CheckPackBillController {

    @Autowired
    private CheckPackBillService cbpservice = null;

    Logger logger = Logger.getLogger(LoginController.class);

    /**
     * @param packbillitem
     * @param request
     * @return
     */
    @PostMapping(value="/inserpackbill")
    @ResponseBody
    public Result<String> InsertPackBill(@RequestBody InsertPackBillDto packbillitem, HttpServletRequest request){

        if (packbillitem == null) {
            logger.error("获取到的对象值为空");
            throw new ThdfhclNotFoundException(errCode_5,errMsg_5);
        }
        String token = request.getHeader("Authorization");
        String worknumber = new Token().CheckToken(token);
        cbpservice.insert(packbillitem,worknumber);

        return new Result<String>(sucCode,sucMsg,"");
    }
}
