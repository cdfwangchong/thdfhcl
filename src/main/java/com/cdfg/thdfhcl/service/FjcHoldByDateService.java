package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.BillDto;
import com.cdfg.thdfhcl.pojo.dto.ThdfjglDto;
import com.cdfg.thdfhcl.pojo.dto.XsdBillDto;

import java.util.Map;

public interface FjcHoldByDateService {
    Map qryCheckBill(XsdBillDto xsdbillDto);

    String insertDts(XsdBillDto xsdbillDto, String worknumber);

    Map qryZcsd(BillDto billDto);

    Map thdfjgl(ThdfjglDto thdfjglDto, String worknumber);
}
