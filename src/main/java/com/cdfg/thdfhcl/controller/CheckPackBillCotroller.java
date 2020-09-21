package com.cdfg.thdfhcl.controller;

import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.pojo.dto.InsertPackBillDto;
import com.cdfg.thdfhcl.pojo.until.DtoJwt;
import com.cdfg.thdfhcl.pojo.until.Jwt;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.Token;
import com.cdfg.thdfhcl.service.CheckPackBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;
import static com.cdfg.thdfhcl.pojo.until.Constant.sucMsg;

/**
 * 机场到货验收
 */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class CheckPackBillCotroller {

    @Autowired
    private CheckPackBillService cbpservice = null;

    /**
     *
     * @param packbillitem
     * @param request
     * @return
     */
    @PostMapping(value="/inserpackbill")
    @ResponseBody
    public Result<String> InsertPackBill(@RequestBody InsertPackBillDto packbillitem, HttpServletRequest request){

        if (packbillitem == null) {
            throw new ThdfhclNotFoundException(errCode_5,errMsg_5);
        }
        String worknumber = new Token().CheckToken(request);
        cbpservice.insert(packbillitem,worknumber);

        return new Result<String>(sucCode,sucMsg,"");
    }
}
