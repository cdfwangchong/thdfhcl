package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.HoldByDateDto;
import com.cdfg.thdfhcl.pojo.until.SellHeadEntity;

public interface HoldByDateService {
    String qryCheckBill(HoldByDateDto hbdDto);

    String insertDts(HoldByDateDto hbdDto,String worknumber);
}
