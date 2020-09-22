package com.cdfg.thdfhcl.service.impl;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.dao.CheckpackbillDao;
import com.cdfg.thdfhcl.pojo.dto.CheckpackbillDto;
import com.cdfg.thdfhcl.pojo.dto.InsertPackBillDto;
import com.cdfg.thdfhcl.service.CheckPackBillService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.cdfg.thdfhcl.pojo.until.Constant.errCode_6;
import static com.cdfg.thdfhcl.pojo.until.Constant.errMsg_6;

@Service
public class CheckPackBillServiceImpl implements CheckPackBillService {
    @Autowired
    private CheckpackbillDao cpbDao;

    Logger logger = Logger.getLogger(CheckPackBillServiceImpl.class);

    /**
     *机场到货验收
     * @param packbillitem
     * @param worknumber
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 30,rollbackFor = Exception.class)
    @Override
    public boolean insert(InsertPackBillDto packbillitem, String worknumber) {

        String packbillStr = packbillitem.getBillNO();
        String billtype = packbillitem.getBillType();
        Date cpbDate = packbillitem.getCpbDate();
        logger.info("取到机场到货验收接口数据"+packbillStr+billtype+cpbDate);

        String[] packbillArr = packbillStr.split(",");
        List<CheckpackbillDto> cpbDtoList = new ArrayList<>();
        Date currentdate = new Date();

        for (int i = 0; i < packbillArr.length; i++) {
            String packbill = packbillArr[i];

            CheckpackbillDto cpbDto = new CheckpackbillDto();
            cpbDto.setBillNo(packbill);
            cpbDto.setBillType(billtype);
            cpbDto.setCpbDate(cpbDate);
            cpbDto.setCpbFlag("D");//待验收
            cpbDto.setCpbRemark("");
            cpbDto.setCreatDate(currentdate);
            cpbDto.setCpbOperator(worknumber);

            cpbDtoList.add(cpbDto);
        }
        //传入dao层
        int result = 0;
        try {
            result = cpbDao.insert(cpbDtoList);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("机场到货验收单数据写入异常");
            throw new ThdfhclNotFoundException(errCode_6,errMsg_6);
        }

        if (result == packbillArr.length) {
            return true;
        }else {
            throw new ThdfhclNotFoundException(errCode_6,errMsg_6);
        }
    }
}
