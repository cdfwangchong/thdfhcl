package com.cdfg.thdfhcl.service.impl;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.dao.LoginDao;
import com.cdfg.thdfhcl.pojo.dto.ThduserDto;
import com.cdfg.thdfhcl.pojo.dto.UserDto;
import com.cdfg.thdfhcl.pojo.until.Jwt;
import com.cdfg.thdfhcl.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao logindao = null;

    Logger logger = Logger.getLogger(LoginServiceImpl.class);

    /**
     * @param userDto
     * @return
     */
    @Override
    public String login(UserDto userDto) {
        //创建Token
        String token = null;
        try {
            ThduserDto thduser = logindao.selectByPrimaryKey(userDto);

            if (thduser == null) {
                logger.error("员工ID在表中不存在");
                throw new ThdfhclNotFoundException(errCode_2,errMsg_2);
            }else {
                String worknumber = thduser.getUserId();//员工工号
                String departmentid = thduser.getThdAddress();// 部门id
                String status = thduser.getStatus();//状态
                String accountname = thduser.getUserName();// 用户名称

                Map<String, Object> payload = new HashMap<String, Object>();
                Date date = new Date();
                payload.put("accountname",accountname);// 用户名称
                payload.put("departmentid",departmentid);// 部门id
                payload.put("status",status);//状态
                payload.put("worknumber",worknumber);//员工工号
                payload.put("iat", date.getTime());// 生成时间
                payload.put("ext", date.getTime() + 1 * 1000 * 60 * 60 * 24);// 过期时间1 小时 单位是毫秒

                //得到token
                token = new Jwt().createToken(payload);
            }
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("提货点用户表数据查询异常");
            throw new ThdfhclNotFoundException(errCode_3,errMsg_3);
        }
        return token;
    }
}
