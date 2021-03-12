package com.cdfg.thdfhcl.service.impl;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.controller.CustPickController;
import com.cdfg.thdfhcl.dao.CustPickDao;
import com.cdfg.thdfhcl.pojo.dto.BoardpassDto;
import com.cdfg.thdfhcl.pojo.dto.CustPickDto;
import com.cdfg.thdfhcl.pojo.dto.FlightDto;
import com.cdfg.thdfhcl.pojo.dto.ModifyFlightDto;
import com.cdfg.thdfhcl.pojo.until.CustBillEntity;
import com.cdfg.thdfhcl.pojo.until.SellBillEntity;
import com.cdfg.thdfhcl.pojo.until.ThddEntity;
import com.cdfg.thdfhcl.service.CustPickService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;

@Service
public class CustPickServiceImpl implements CustPickService {
    @Autowired
    CustPickDao cpDao = null;
    Logger logger = Logger.getLogger(CustPickController.class);

    @Override
    public CustBillEntity qryCustPickInfo(String CardId) {
        Map parammap = new HashMap<String, String>();
        List<SellBillEntity> sbelist;

        logger.info("取到顾客提货-提货单查询接口传入的证件号"+CardId);
        try {
            parammap.put("cardId",CardId);
            cpDao.qryCustPickInfo(parammap);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("顾客提货查询客人航班、提货单信息异常");
            throw new ThdfhclNotFoundException(errCode_22,errMsg_22);
        }
        sbelist = (List<SellBillEntity>) parammap.get("shRc");
        if (sbelist.size() == 0) {
            logger.error("顾客提货查询客人提货单信息返回值为空");
            throw new ThdfhclNotFoundException(errCode_26,CardId+errMsg_26);
        }
        String userName = (String) parammap.get("userName");
        String userId = (String)parammap.get("userId");
        String flightNum = (String)parammap.get("flightNum");
        String flightTime = (String)parammap.get("flightTime");
        String flightAddress = (String)parammap.get("flightAddress");
        String fhNum = (String)parammap.get("fhNum");
        int total = (Integer) parammap.get("pickBillCnt");
        String flightFlag = (String)parammap.get("flightFlag");
        String shelfnos = (String)parammap.get("shelfnos");
        String addressname = (String)parammap.get("addressname");
        logger.info("取到顾客提货查询返回值"+CardId+userName+"航班号："+flightNum
                +"离境时间"+flightTime+"提货地点："+flightAddress+"分货号"+fhNum+"是否有多个航班"+flightFlag);

        CustBillEntity cbEntity = new CustBillEntity();
        cbEntity.setSellhead(sbelist);
        cbEntity.setUserName(userName);
        cbEntity.setCardId(userId);
        cbEntity.setFlightNum(flightNum);
        cbEntity.setFlightTime(flightTime);
        cbEntity.setFlightAddress(flightAddress);
        cbEntity.setFhNum(fhNum);
        cbEntity.setTotal(total);
        cbEntity.setFlightFlag(flightFlag);
        cbEntity.setShelfno(shelfnos);
        cbEntity.setAddressName(addressname);
        return cbEntity;
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 30,rollbackFor = Exception.class)
    @Override
    public CustBillEntity updateModifyFlight(ModifyFlightDto mfDto,String worknumber ) {
        String cardid = mfDto.getCardId();
        String new_flightNum = mfDto.getNew_flightNum();
        String new_flightTime = mfDto.getNew_flightTime();
        String old_flightNum = mfDto.getOld_flightNum();
        String old_flightTime = mfDto.getOld_flightTime();
        List<SellBillEntity> thdlist = mfDto.getSellhead();
        logger.info("取到顾客提货-航班修改接口传入的证件号"+cardid+"原航班："+old_flightNum+
                "原离岛时间："+old_flightTime+"现航班："+new_flightNum+"现离岛时间："+new_flightTime);

        for (int i = 0; i < thdlist.size(); i++) {
            SellBillEntity sbEntity = thdlist.get(i);
            String market = sbEntity.getMarket();
            String billno = sbEntity.getBillNo();
            String flightNo = sbEntity.getFlightNo();
            String ldrq = sbEntity.getLdrq();
            String status = sbEntity.getStatus();
            logger.info("取到顾客提货-航班修改接口传入的提货单信息证件号" + cardid + "门店：" + market +
                    "提货单号：" + billno + "航班号：" + flightNo + "现离岛时间：" + ldrq + "状态：" + status);
            Map map = new HashMap();
            map.put("market", market);
            map.put("cardId", cardid);
            map.put("new_flightNum", new_flightNum);
            map.put("new_flightTime", new_flightTime);
            map.put("old_flightNum", old_flightNum);
            map.put("old_flightTime", old_flightTime);
            map.put("xsdno", billno);
            map.put("operator", worknumber);
            try {
                cpDao.updateModifyFlight(map);
            } catch (Exception e) {
                logger.error("顾客提货航班修改存储过程执行异常");
                throw new ThdfhclNotFoundException(errCode_23, errMsg_23);
            }
        }
            //查询修改航班后的航班和提货单信息
            Map parammap = new HashMap<String, String>();
            List<SellBillEntity> sbelist;

            logger.info("取到顾客提货-提货单查询接口传入的证件号"+cardid);
            try {
                parammap.put("cardId",cardid);
                cpDao.qryCustPickInfo(parammap);
            } catch (Exception e) {
                logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
                logger.error("顾客提货查询客人航班、提货单信息异常");
                throw new ThdfhclNotFoundException(errCode_22,errMsg_22);
            }
            sbelist = (List<SellBillEntity>) parammap.get("shRc");
            if (sbelist.size() == 0) {
                logger.error("顾客提货查询客人提货单信息返回值为空");
                throw new ThdfhclNotFoundException(errCode_26,errMsg_26);
            }
            String userName = (String) parammap.get("userName");
            String userId = (String)parammap.get("userId");
            String flightNum = (String)parammap.get("flightNum");
            String flightTime = (String)parammap.get("flightTime");
            String flightAddress = (String)parammap.get("flightAddress");
            String fhNum = (String)parammap.get("fhNum");
            int total = (Integer) parammap.get("pickBillCnt");
            String flightFlag = (String)parammap.get("flightFlag");
            String shelfnos = (String)parammap.get("shelfnos");
            String addressname = (String)parammap.get("addressname");
            logger.info("取到顾客提货查询返回值"+cardid+userName+"航班号："+flightNum
                    +"离境时间"+flightTime+"提货地点："+flightAddress+"分货号"+fhNum+"是否有多个航班"+flightFlag);

            CustBillEntity cbEntity = new CustBillEntity();
            cbEntity.setSellhead(sbelist);
            cbEntity.setUserName(userName);
            cbEntity.setCardId(userId);
            cbEntity.setFlightNum(flightNum);
            cbEntity.setFlightTime(flightTime);
            cbEntity.setFlightAddress(flightAddress);
            cbEntity.setFhNum(fhNum);
            cbEntity.setTotal(total);
            cbEntity.setFlightFlag(flightFlag);
            cbEntity.setShelfno(shelfnos);
            cbEntity.setAddressName(addressname);
            return cbEntity;
        }

    @Override
    public String scanDjp(BoardpassDto bpDto) {
        //取到客人提货单号
        String xsdno = bpDto.getBillNo();
        String djp = bpDto.getTicketCode();
        String cardid = bpDto.getCardId();
        logger.info("取到顾客提货-扫描身份证接口传入的证件号"+cardid+"登机牌："+djp+
                "销售单号："+xsdno);
        String flightNo;
        String ldrq;
        String djpStr = djp.replace(" ","");
        if (djpStr.length() >= 17) {
            flightNo = djpStr.substring(0,6);//取出第一第六位
            ldrq = djpStr.substring(6,8);//取出第七第八位
            logger.info("顾客"+cardid+"离岛日期登机牌"+djpStr+"航班号："+flightNo);
        }else if (djpStr.length() ==6) {
            flightNo = djpStr;
            ldrq = "";
            logger.info("顾客"+cardid+"离岛日期登机牌"+djpStr+"航班号："+flightNo);
        }else if (djpStr.length() >6 && djpStr.length()<17) {
            flightNo = djpStr.substring(0,6);;//取出第一第六位
            ldrq = "";
            logger.info("顾客"+cardid+"离岛日期登机牌"+djpStr+"航班号："+flightNo);
        }else {
            return "N";
        }
        Map map = new HashMap();
        map.put("flightNo",flightNo);
        map.put("ldrq",ldrq);
        map.put("xsdno",xsdno);

        try {
            cpDao.qryScanDjp(map);
        } catch (Exception e) {
            logger.error("顾客登机牌航班查询存储过程执行异常");
            throw new ThdfhclNotFoundException(errCode_27,errMsg_27);
        }
        String retflag = (String) map.get("retflag");
        String retmsg = (String) map.get("retmsg");

        logger.info("顾客" + cardid + "登机牌查询返回信息 retflag：" + retflag +
                "retmsg：" + retmsg);
        if ("2003".equals(retflag)) {
            logger.error("顾客登机牌航班不存在");
            throw new ThdfhclNotFoundException(errCode_28,"航班号"+flightNo+errMsg_28);
        }else if ("2004".equals(retflag)) {
            logger.error("顾客登机牌航班查询存储过程执行异常");
            throw new ThdfhclNotFoundException(errCode_27,errMsg_27);
        }else if ("2002".equals(retflag)) {
            return "Y";
        }else {
            return "N";
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 30,rollbackFor = Exception.class)
    @Override
    public int custPick(CustPickDto cpDto,String worknumber) {
        String cardid = cpDto.getCardId();
        String ticketcode = cpDto.getTicketCode();
        List<SellBillEntity> billnolist = cpDto.getSellhead();
        String ticketCode = cpDto.getTicketCode();
        logger.info("取到顾客提货-航班修改接口传入的证件号"+cardid+"登机牌："+ticketcode);
        for (int i = 0; i < billnolist.size(); i++) {
            SellBillEntity sbEntity = billnolist.get(i);
            String market = sbEntity.getMarket();
            String billno = sbEntity.getBillNo();
            String flightNo = sbEntity.getFlightNo();
            String ldrq = sbEntity.getLdrq();
            logger.info("取到顾客提货-航班修改接口传入的提货单信息证件号" + cardid + "门店：" + market +
                    "提货单号：" + billno + "航班号：" + flightNo + "现离岛时间：" + ldrq);

            Map map = new HashMap();
            map.put("market",market);
            map.put("cardId",cardid);
            map.put("flightNum",flightNo);
            map.put("flightTime",ldrq);
            map.put("xsdno",billno);
            map.put("operator",worknumber);
            map.put("ticketCode",ticketCode);
            try {
                cpDao.cusnPick(map);
            } catch (Exception e) {
                logger.error("顾客提货确认存储过程执行异常");
                throw new ThdfhclNotFoundException(errCode_24,errMsg_24);
            }
            String retflag = (String) map.get("retflag");
            String retmsg = (String) map.get("retmsg");

            if (retflag == null || retmsg==null) {
                logger.error("顾客提货确认返回值为空");
                throw new ThdfhclNotFoundException(errCode_25,errMsg_25);
            }

            if ("2006".equals(retflag) || "2005".equals(retflag)||"2004".equals(retflag)) {
                logger.error(retmsg);
                throw new ThdfhclNotFoundException(errCode_25,retmsg);
            }

            logger.info("取到顾客提货-顾客提货确认接口传入的提货单信息证件号" + cardid + "门店："
                    + market +"的返回标志"+retflag+"返回信息"+retmsg);
        }
        return 0;
    }

    @Override
    public ThddEntity qryThdd(FlightDto flightDto) {
        String flightNo = flightDto.getFlightNo();
        ThddEntity thddEntity;
        try {
            thddEntity = cpDao.qryThdd(flightNo);
        } catch (Exception e) {
            logger.error("航班查询提货点信息异常");
            throw new ThdfhclNotFoundException(errCode_29,errMsg_29);
        }
        if (thddEntity == null) {
            logger.error("航班查询提货点信息返回值为空");
            throw new ThdfhclNotFoundException(errCode_30,errMsg_30);
        }
        logger.info("取到航班查询提货点信息返回值" +thddEntity.getFlightNo() + "提货地点："
                + thddEntity.getThdd() +"提货地点名"+thddEntity.getThddName());
        return thddEntity;
    }
}
