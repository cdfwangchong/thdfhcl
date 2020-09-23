package com.cdfg.thdfhcl.service.impl;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.dao.CustlocnoDao;
import com.cdfg.thdfhcl.pojo.dto.BillDto;
import com.cdfg.thdfhcl.pojo.dto.CheckpackbillDto;
import com.cdfg.thdfhcl.pojo.dto.CustlocnoDto;
import com.cdfg.thdfhcl.pojo.dto.FlightAndShelfnoDto;
import com.cdfg.thdfhcl.pojo.until.Thddparam;
import com.cdfg.thdfhcl.service.CustLocNoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;

@Service
public class CustLocNoServiceImpl implements CustLocNoService {

    @Autowired
    CustlocnoDao custlocnoDao;
    Logger logger = Logger.getLogger(CustLocNoServiceImpl.class);

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 30,rollbackFor = Exception.class)
    @Override
    public boolean InsertCustLocNO(CustlocnoDto clnDto,String worknumber) {
        String shelfnoStr = clnDto.getShelfno();
        String custpass = clnDto.getCustpass();
        Date opertime = clnDto.getOpertime();
        String thdd = clnDto.getThdd();
        logger.info("取到上架接口值"+shelfnoStr+"@"+custpass+"@"+opertime+"@"+thdd);

        //传入dao层
        int result = 0;

        String[] shelfnoArr = shelfnoStr.split(",");
        List<CustlocnoDto> clnDtoList = new ArrayList<CustlocnoDto>();
        try {
            for (int i = 0; i < shelfnoArr.length; i++) {
                String shelfno = shelfnoArr[i];
    
                CustlocnoDto custlocDto = new CustlocnoDto();
                custlocDto.setShelfno(shelfno);
                custlocDto.setCustpass(custpass);
                custlocDto.setFlag("N");
                custlocDto.setOperno(worknumber);
                custlocDto.setOpertime(opertime);
                custlocDto.setThdd(thdd);

                clnDtoList.add(custlocDto);
            }
            result = custlocnoDao.insert(clnDtoList);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("上架数据写入异常");
            throw new ThdfhclNotFoundException(errCode_6,errMsg_6);
        }

        if (result != shelfnoArr.length) {
            throw new ThdfhclNotFoundException(errCode_6,errMsg_6);
        }

        logger.info("正常写入表中"+shelfnoStr+"@"+custpass+"@"+opertime+"@"+thdd);
        return true;
    }

    @Override
    public FlightAndShelfnoDto QryCustLocNO(BillDto billDto) {

        logger.info("查询接口取到单据号"+billDto.getBillNO());
        Map fasMap = new HashMap<String, String>();
        FlightAndShelfnoDto fasDto = new FlightAndShelfnoDto();
        try {

            fasMap.put("billNO", billDto.getBillNO());
            custlocnoDao.selectByPrimaryKey(fasMap);
            
            String shelfno = (String) fasMap.get("shelfnos");
            String shcustpass = (String) fasMap.get("shcustpass");
            String shname = (String) fasMap.get("shname");
            Date shdpttime = (Date) fasMap.get("shdpttime");
            String shdptairline = (String) fasMap.get("shdptairline");
            String shdthdd = (String) fasMap.get("shdthdd");
            String addressName = Thddparam.getAddress(shdthdd);
            String shfhbillno = (String) fasMap.get("shfhbillno");
            int sl = (int) fasMap.get("sl");
            
            fasDto.setShelfno(shelfno);
            fasDto.setCardID(shcustpass);
            fasDto.setFhNum(shfhbillno);
            fasDto.setFlightAddress(shdthdd);
            fasDto.setAddressName(addressName);
            fasDto.setFlightNum(shdptairline);
            fasDto.setUserName(shname);
            fasDto.setFlightTime(shdpttime);
            fasDto.setTotal(sl);

            logger.info("取出提货单货位数据"+shelfno+"@"+
                    shcustpass+"@"+shname+"@"+shdpttime+"@"+
                    shdptairline+"@"+shdthdd+"@"+addressName+
                    "@"+shfhbillno+"@"+sl);

        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("表数据查询返回值异常");
            throw new ThdfhclNotFoundException(errCode_3,errMsg_3);
        }
      return fasDto; 
    }
}
