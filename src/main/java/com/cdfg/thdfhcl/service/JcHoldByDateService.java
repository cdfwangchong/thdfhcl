package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.HoldByDateDto;
import com.cdfg.thdfhcl.pojo.dto.JcHoldByDateDto;
import com.cdfg.thdfhcl.pojo.dto.JcThDto;
import com.cdfg.thdfhcl.pojo.until.JcXsdbillEntity;

import java.util.List;
import java.util.Map;

public interface JcHoldByDateService {
    List<JcXsdbillEntity> qryCheckBill(JcHoldByDateDto hbdDto,String worknumber);

    String insertDts(JcHoldByDateDto hbdDto, String worknumber);

    List<JcXsdbillEntity> qryJcThBill(JcHoldByDateDto hbdDto, String worknumber);

    Map updateJcTh(JcThDto jcthDto, String worknumber);
}
