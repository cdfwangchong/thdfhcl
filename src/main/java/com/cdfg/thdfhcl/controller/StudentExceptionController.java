package com.cdfg.thdfhcl.controller;


import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.pojo.until.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@ControllerAdvice
public class StudentExceptionController {

    @ExceptionHandler(value = ThdfhclNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(produces="json/html; charset=UTF-8")
    @ResponseBody
    public Result<String> exception (ThdfhclNotFoundException exception){
        System.out.println(exception.getMsg());
        return new Result<String>(exception.getRetCode(),exception.getMsg(),"");
    }
}
