package com.cdfg.thdfhcl.service.impl;

import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import com.cdfg.thdfhcl.dao.JcHoldByDateDao;
import com.cdfg.thdfhcl.pojo.dto.JcHoldByDateDto;
import com.cdfg.thdfhcl.pojo.dto.JcThDto;
import com.cdfg.thdfhcl.pojo.until.JcXsdbillEntity;
import com.cdfg.thdfhcl.service.JcHoldByDateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;
import static com.cdfg.thdfhcl.pojo.until.Constant.errCode_5;

@Service
public class JcHoldByDateServiceImpl implements JcHoldByDateService {
    @Autowired
    JcHoldByDateDao hbdDao = null;
    Logger logger = Logger.getLogger(JcHoldByDateServiceImpl.class);

    @Override
    public List<JcXsdbillEntity> qryCheckBill(JcHoldByDateDto hbdDto,String worknumber) {
        if (hbdDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        Map param = new HashMap();
        param.put("yysj",hbdDto.getYysj());
        param.put("market",hbdDto.getMarket());
        param.put("qhdd",hbdDto.getQhdd());
        param.put("zctype",hbdDto.getZctype());
        param.put("operator",worknumber);
        List<JcXsdbillEntity> beyList;
        try {
            hbdDao.qryCheckBill(param);
            //取出结果集
            String ret_flag = (String) param.get("ret_flag");
            if ("1001".equals(ret_flag)) {
                throw new ThdfhclNotFoundException (errCode_3, worknumber+"不能查询其他门店提货单");
            }
            beyList = (List<JcXsdbillEntity>) param.get("zcRc");
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("表数据查询返回值异常");
            throw new ThdfhclNotFoundException (errCode_3, errMsg_3);
        }

        for (int i = 0; i <beyList.size() ; i++) {
            JcXsdbillEntity jxEntity = new JcXsdbillEntity();
            logger.info("取到寄存提货单查询返回值"+jxEntity.getIsth()+jxEntity.getMarket()
            +jxEntity.getQhdd()+jxEntity.getYysj()+jxEntity.getXsdno());
        }
        return beyList;
    }

    @Override
    public String insertDts(JcHoldByDateDto hbdDto,String worknumber) {
        if (hbdDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        Map param  = new HashMap<String,String>();
        param.put("operator",worknumber);
        param.put("billNO",hbdDto.getXsdno());
        param.put("market",hbdDto.getMarket());
        param.put("yysj",hbdDto.getYysj());
        param.put("qhdd",hbdDto.getQhdd());
        param.put("tmpCode",hbdDto.getTmpCode());
        try {
            hbdDao.insertDts(param);
        } catch (Exception e) {
            logger.error("邮寄日期暂存写入异常");
            throw new ThdfhclNotFoundException(errCode_3, errMsg_3);
        }
        String tmpCode = (String) param.get("o_TmpCode");
        String billno = (String) param.get("o_billno");
        String isth = (String) param.get("o_isth");

        if (tmpCode == "") {
            logger.error("寄存日期暂存写入异常");
            throw new ThdfhclNotFoundException(errCode, errMsg);
        }else {
            return tmpCode;
        }
    }

    @Override
    public List<JcXsdbillEntity> qryJcThBill(JcHoldByDateDto hbdDto, String worknumber) {
        if (hbdDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        Map param = new HashMap();
        param.put("market",hbdDto.getMarket());
        param.put("operator",worknumber);
        List<JcXsdbillEntity> beyList;
        try {
            hbdDao.qryJcThBill(param);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("表数据查询返回值异常");
            throw new ThdfhclNotFoundException (errCode_3, errMsg_3);
        }
        //取出结果集
        String ret_flag = (String) param.get("ret_flag");
        if ("1001".equals(ret_flag)) {
            throw new ThdfhclNotFoundException (errCode_3, worknumber+"不能查询其他门店提货单");
        }
        beyList = (List<JcXsdbillEntity>) param.get("thRc");

        if (beyList == null) {
            logger.error("当前无退货记录");
            throw new ThdfhclNotFoundException (errCode_5, "当前无退货记录");
        }
        for (int i = 0; i <beyList.size() ; i++) {
            JcXsdbillEntity jxEntity = new JcXsdbillEntity();
            logger.info("取到寄存提货单查询返回值"+jxEntity.getIsth()+jxEntity.getMarket()
                    +jxEntity.getQhdd()+jxEntity.getYysj()+jxEntity.getXsdno());
        }
        return beyList;
    }

    @Override
    public Map updateJcTh(JcThDto jcthDto, String worknumber) {
        if (jcthDto == null) {
            throw new ThdfhclNotFoundException(errCode_5, errMsg_5);
        }
        Map param = new HashMap();
        param.put("xsdno",jcthDto.getXsdno());
        param.put("operator",worknumber);
        param.put("market",jcthDto.getMarket());
        try {
            hbdDao.updateJcTh(param);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("表数据查询返回值异常");
            throw new ThdfhclNotFoundException (errCode_3, errMsg_3);
        }
        //取出结果集
        String ret_flag = (String) param.get("ret_flag");
        String ret_msg = (String) param.get("ret_msg");
        if (!"1002".equals(ret_flag)) {
            logger.info(ret_msg);
            throw new ThdfhclNotFoundException (errCode_3,ret_msg);
        }
        logger.info("提货单："+jcthDto.getXsdno()+ret_msg);
        return param;
    }
}
