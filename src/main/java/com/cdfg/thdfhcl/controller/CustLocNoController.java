package com.cdfg.thdfhcl.controller;

import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.pojo.dto.BillDto;
import com.cdfg.thdfhcl.pojo.dto.CustlocnoDto;
import com.cdfg.thdfhcl.pojo.dto.FlightAndShelfnoDto;
import com.cdfg.thdfhcl.pojo.until.DtoJwt;
import com.cdfg.thdfhcl.pojo.until.Jwt;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.Token;
import com.cdfg.thdfhcl.service.CustLocNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;
import static com.cdfg.thdfhcl.pojo.until.Constant.errMsg_4;

/**
 *上架
 */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class CustLocNoController {

    @Autowired
    CustLocNoService custLocService;

    @PostMapping(value = "/insertcustloc")
    @ResponseBody
    public Result<String> InsertCustLocNo(HttpServletRequest request,@RequestBody CustlocnoDto clnDto ) {
        if (clnDto == null) {
            throw new ThdfhclNotFoundException(errCode_5,errMsg_5);
        }
//        String worknumber = new Token().CheckToken(request);
        String worknumber = "3859";
        boolean bool = custLocService.InsertCustLocNO(clnDto,worknumber);
        if (bool) {
            return new Result<String>(sucCode,sucMsg,"");
        }else {
            return new Result<String>(errCode_6,errMsg_6,"");
        }
    }

    @PostMapping(value = "/qrycustloc")
//    @RequestMapping(value = "/qrycustloc",method = RequestMethod.POST, produces="json/html; charset=UTF-8")
    @ResponseBody
    public Result<FlightAndShelfnoDto> QryCustLocNo(HttpServletRequest request,@RequestBody BillDto billDto ) {
        if (billDto == null) {
            throw new ThdfhclNotFoundException(errCode_5,errMsg_5);
        }
//        String worknumber = new Token().CheckToken(request);
        String worknumber = "3859";
        FlightAndShelfnoDto fasDto = custLocService.QryCustLocNO(billDto);

        return new Result<FlightAndShelfnoDto>(sucCode,sucMsg,fasDto);
    }
    @PostMapping(value = "/testcustloc")
//    @RequestMapping(value = "/testcustloc",method = RequestMethod.POST, produces="json/html; charset=UTF-8")
    @ResponseBody
    public Result<String> test() {
        return new Result<String>(sucCode,sucMsg,"");
    }
}
