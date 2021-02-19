package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.HoldByDateDto;
import com.cdfg.thdfhcl.pojo.dto.JcHoldByDateDto;
import com.cdfg.thdfhcl.pojo.until.JcXsdbillEntity;

import java.util.List;

public interface JcHoldByDateService {
    List<JcXsdbillEntity> qryCheckBill(JcHoldByDateDto hbdDto);

    String insertDts(JcHoldByDateDto hbdDto, String worknumber);
}
