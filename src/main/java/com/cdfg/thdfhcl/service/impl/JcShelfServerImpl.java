package com.cdfg.thdfhcl.service.impl;

import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.dao.JcShelfDao;
import com.cdfg.thdfhcl.pojo.dto.JcInsertShelfDto;
import com.cdfg.thdfhcl.pojo.dto.JcQrySxdnoDto;
import com.cdfg.thdfhcl.service.JcShelfServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;

@Service
public class JcShelfServerImpl implements JcShelfServer {
    @Autowired
    JcShelfDao jsDao = null;

    Logger logger = Logger.getLogger(JcHoldByDateServiceImpl.class);
    @Override
    public String insertShelf(JcInsertShelfDto hbdDto, String operator) {
        String xsdno = hbdDto.getXsdno();
        String shelfno = hbdDto.getShelfno();
        String flag = hbdDto.getFlag();
        logger.info("取到寄存上架接口传入的值"+xsdno+"#"+shelfno+"#"+flag);

        Map param = new HashMap();
        param.put("xsdno",xsdno);
        param.put("shelfno",shelfno);
        param.put("flag",flag);
        param.put("operator",operator);
        try {
            jsDao.insertShelf(param);
        } catch (Exception e) {
            logger.error(xsdno+"寄存上架写入异常");
            throw new ThdfhclNotFoundException(errCode, errMsg);
        }
        String ret_flag = (String) param.get("ret_flag");
        String ret_msg = (String) param.get("ret_msg");
        if (ret_flag == null) {
            logger.error(xsdno+"寄存上架ret_flag返回值为空");
            throw new ThdfhclNotFoundException(errCode, "寄存上架返回值为空");
        }
        if ("1001".equals(ret_flag)) {
            logger.error(xsdno+"寄存上架写入异常");
            throw new ThdfhclNotFoundException(errCode, ret_msg);
        }
        return ret_flag;
    }

    @Override
    public JcQrySxdnoDto qrySxdno(String xsdno,String operator) {

        Map param = new HashMap();
        param.put("billNo",xsdno);
//        param.put("operator",operator);
        logger.info("取到寄存上架提货单查询传入的值"+xsdno+"#"+operator);
        try {
            jsDao.qrySxdno(param);
        } catch (Exception e) {
            logger.error(xsdno+"寄存上架写提货单查询异常");
            throw new ThdfhclNotFoundException(errCode, errMsg);
        }
        String ret_flag = (String) param.get("ret_flag");
        String ret_msg = (String) param.get("ret_msg");
        if (ret_flag == null) {
            logger.error(xsdno+"寄存上架提货单查询ret_flag返回值为空");
            throw new ThdfhclNotFoundException(errCode, "寄存上架返回值为空");
        }
        if ("1001".equals(ret_flag)) {
            logger.error(xsdno+"寄存上架提货单查询异常");
            throw new ThdfhclNotFoundException(errCode, ret_msg);
        }
        JcQrySxdnoDto jqsDto = new JcQrySxdnoDto();
        String thdh = (String) param.get("xsdno");
        String status = (String)param.get("status");
        Date yysj = (Date) param.get("yysj");
        String qhdd = (String)param.get("qhdd");
        String oname = (String)param.get("oname");
        String cardid = (String)param.get("cardid");

        jqsDto.setCardid(cardid);
        jqsDto.setName(oname);
        jqsDto.setQhdd(qhdd);
        jqsDto.setStatus(status);
        jqsDto.setYysj(yysj);
        jqsDto.setXsdno(thdh);

        return jqsDto;
    }
}
