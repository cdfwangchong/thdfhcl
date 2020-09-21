package com.cdfg.thdfhcl.controller;

import com.cdfg.thdfhcl.pojo.dto.UserDto;
import com.cdfg.thdfhcl.pojo.until.AES;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;

@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class LoginController {

    @Autowired
    LoginService loginserice = null;

    /**
     *
     * @param thduser
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Result<String> Login(@RequestBody UserDto thduser) {

        String userID = thduser.getUserId();
        String strToEncrypt = thduser.getPassWord();
        //获取加密后的密码
        String passWord = AES.encrypt(strToEncrypt,key);
        thduser.setPassWord(passWord);
        String token = loginserice.login(thduser);

        return new Result<String>(sucCode,sucMsg,token);
    }
}
