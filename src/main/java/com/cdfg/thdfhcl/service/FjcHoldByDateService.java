package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.XsdBillDto;

import java.util.Map;

public interface FjcHoldByDateService {
    Map qryCheckBill(XsdBillDto xsdbillDto);

    String insertDts(XsdBillDto xsdbillDto, String worknumber);
}
