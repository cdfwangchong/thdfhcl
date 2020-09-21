package com.cdfg.thdfhcl.service.impl;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.dao.CustlocnoDao;
import com.cdfg.thdfhcl.pojo.dto.BillDto;
import com.cdfg.thdfhcl.pojo.dto.CheckpackbillDto;
import com.cdfg.thdfhcl.pojo.dto.CustlocnoDto;
import com.cdfg.thdfhcl.pojo.dto.FlightAndShelfnoDto;
import com.cdfg.thdfhcl.service.CustLocNoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                
                System.out.println(custlocDto.getCustpass());
                System.out.println(custlocDto.getShelfno());
                System.out.println(custlocDto.getOperno());
                
                clnDtoList.add(custlocDto);
            }
            result = custlocnoDao.insert(clnDtoList);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("上架数据写入异常");
            System.out.println(e);
            throw new ThdfhclNotFoundException(errCode_6,errMsg_6);
        }

        if (result != shelfnoArr.length) {
            throw new ThdfhclNotFoundException(errCode_6,errMsg_6);
        }
        return true;
    }

    @Override
    public FlightAndShelfnoDto QryCustLocNO(BillDto billDto) {

        FlightAndShelfnoDto fasDto=null;
       
        try {
            fasDto = custlocnoDao.selectByPrimaryKey(billDto);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("表数据查询返回值异常");
            throw new ThdfhclNotFoundException(errCode_3,errMsg_3);
        }
      return fasDto; 
    }
}
