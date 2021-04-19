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
import org.apache.log4j.Logger;
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

    Logger logger = Logger.getLogger(LoginController.class);

    @PostMapping(value = "/insertcustloc")
    @ResponseBody
    public Result<String> InsertCustLocNo(HttpServletRequest request,@RequestBody CustlocnoDto clnDto ) {
        if (clnDto == null) {
            logger.error("获取到的对象值为空");
            throw new ThdfhclNotFoundException(errCode_5,errMsg_5);
        }
//        String token = request.getHeader("Authorization");
//        String worknumber = new Token().CheckToken(token);
        String worknumber = "3859";

        boolean bool = custLocService.InsertCustLocNO(clnDto,worknumber);
        if (bool) {
            return new Result<String>(sucCode,sucMsg,"");
        }else {
            return new Result<String>(errCode_6,errMsg_6,"");
        }
    }

    /**
     * 查询
     * @param request
     * @param billDto
     * @return
     */
    @PostMapping(value = "/qrycustloc")
    @ResponseBody
    public Result<FlightAndShelfnoDto> QryCustLocNo(HttpServletRequest request,@RequestBody BillDto billDto ) {
        if (billDto == null) {
            logger.error("获取到的对象值为空");
            throw new ThdfhclNotFoundException(errCode_5,errMsg_5);
        }
        String token = request.getHeader("Authorization");
        //检查Token
        new Token().CheckToken(token);
        FlightAndShelfnoDto fasDto = custLocService.QryCustLocNO(billDto);

        return new Result<FlightAndShelfnoDto>(sucCode,sucMsg,fasDto);
    }

    @PostMapping(value = "/testcustloc")
    @ResponseBody
    public Result<String> test() {
        return new Result<String>(sucCode,sucMsg,"");
    }
}
