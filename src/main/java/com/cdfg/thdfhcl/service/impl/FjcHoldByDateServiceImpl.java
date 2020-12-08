package com.cdfg.thdfhcl.service.impl;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.dao.FjcHoldByDateDao;
import com.cdfg.thdfhcl.pojo.dto.XsdBillDto;
import com.cdfg.thdfhcl.service.FjcHoldByDateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;
import static com.cdfg.thdfhcl.pojo.until.Constant.errMsg_9;

public class FjcHoldByDateServiceImpl implements FjcHoldByDateService {
    @Autowired
    FjcHoldByDateDao fjchbdDao = null;
    Logger logger = Logger.getLogger(FjcHoldByDateServiceImpl.class);

    @Override
    public Map qryCheckBill(XsdBillDto xsdbillDto) {
        if (xsdbillDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        Date zcrq = xsdbillDto.getZcrq();
        String mark = xsdbillDto.getMarket();
        String billNO = xsdbillDto.getBillNO();
        String thdd =xsdbillDto.getThdd();
        String zcsdid = xsdbillDto.getZcsdid();
        Map param = new HashMap();
        param.put("billNO",billNO);
        param.put("mark",mark);
        param.put("zcrq",zcrq);
        param.put("zcsdid",zcsdid);
        param.put("thdd",thdd);

        try {
            fjchbdDao.qryXsdBill(param);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error(billNO+"提货单查询异常");
            throw new ThdfhclNotFoundException(errCode_3, errMsg_3);
        }

        String ret_flag = (String) param.get("ret_flag");
        if(ret_flag.isEmpty()) {
            logger.info("提货单："+billNO+"查询，返回标志为空");
            throw new ThdfhclNotFoundException(errCode_14, errMsg_8+billNO+errMsg_14);
        }
        if("3006".equals(ret_flag) ) {
            logger.info("提货单："+billNO+"不存在");
            throw new ThdfhclNotFoundException(errCode_13, errMsg_8+billNO+errMsg_13);
        }
        if("3001".equals(ret_flag) ) {
            logger.info("提货单："+billNO+"门店与输入门店不一致");
            throw new ThdfhclNotFoundException(errCode_8, errMsg_8+billNO+errMsg_8_1);
        }
        if("3101".equals(ret_flag) ) {
            logger.info("提货单："+billNO+"离岛日期与输入暂存日期不一致");
            throw new ThdfhclNotFoundException(errCode_15, errMsg_8+billNO+errMsg_15);
        }
        if("3102".equals(ret_flag) ) {
            logger.info("提货单："+billNO+"离岛时段与输入暂存时段不一致");
            throw new ThdfhclNotFoundException(errCode_16, errMsg_8+billNO+errMsg_16);
        }
        if("3103".equals(ret_flag) ) {
            logger.info("提货单："+billNO+"提货地点与输入地点不一致");
            throw new ThdfhclNotFoundException(errCode_17, errMsg_8+billNO+errMsg_17);
        }
        if("3003".equals(ret_flag) ) {
            logger.info("提货单："+billNO+"已经退货");
            throw new ThdfhclNotFoundException(errCode_10, errMsg_8+billNO+errCode_10);
        }
        String xsdno = (String) param.get("xsdno");
        String status = (String) param.get("status");
        String ljtime = (String) param.get("ljtime");

        Map retMap = new HashMap();
        retMap.put("xsdno",xsdno);
        retMap.put("status",status);
        retMap.put("ljtime",ljtime);

        return retMap;
    }

    @Override
    public String insertDts(XsdBillDto xsdbillDto, String worknumber) {
        if (xsdbillDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        Date zcrq = xsdbillDto.getZcrq();
        String mark = xsdbillDto.getMarket();
        String billNO = xsdbillDto.getBillNO();
        String thdd =xsdbillDto.getThdd();
        String zcsdid = xsdbillDto.getZcsdid();
        Map param = new HashMap();
        param.put("billNO",billNO);
        param.put("mark",mark);
        param.put("zcrq",zcrq);
        param.put("zcsdid",zcsdid);
        param.put("thdd",thdd);

        try {
            fjchbdDao.insertDts(param);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("提货单"+billNO+"日期暂存异常");
            throw new ThdfhclNotFoundException(errCode_18, errMsg_8+billNO+errMsg_18);
        }
        String out_flag = (String) param.get("OUT_FLAG");
        if (out_flag.isEmpty()) {
            logger.info("提货单："+billNO+"日期暂存，返回标志为空");
            throw new ThdfhclNotFoundException(errCode_19, errMsg_8+billNO+errMsg_19);
        }
        if ("4002".equals(out_flag)) {
            logger.info("提货单："+billNO+"日期存放编码获取失败");
            throw new ThdfhclNotFoundException(errCode_20, errMsg_8+billNO+errMsg_20);
        }
        if ("4003".equals(out_flag)) {
            logger.info("提货单："+billNO+"日期暂存异常");
            throw new ThdfhclNotFoundException(errCode_21, errMsg_8+billNO+errMsg_21);
        }

        String o_TmpCode = (String) param.get("o_TmpCode");
        return o_TmpCode;
    }
}