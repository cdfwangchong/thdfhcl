package com.cdfg.thdfhcl.pojo.until;

import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;
import static com.cdfg.thdfhcl.pojo.until.Constant.errMsg_4;

public class Token {

    public String CheckToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if ("".equals(token) || token==null) {
            throw new ThdfhclNotFoundException(errCode_4,errMsg_4);
        }
        //验证Token是否有效
        Map<String, Object> resultMap = new Jwt().validToken(token);
        boolean isSuccess = (boolean) resultMap.get("isSuccess");
        int status = (int) resultMap.get("status");
        String Msg = (String) resultMap.get("Msg");
        if (!isSuccess) {
            throw new ThdfhclNotFoundException(status,Msg);
        }

        DtoJwt dtojwt = new DtoJwt(token);
        String worknumber = dtojwt.getWorknumber();
        return worknumber;
    }

}
