package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.BillDto;
import com.cdfg.thdfhcl.pojo.dto.CustlocnoDto;
import com.cdfg.thdfhcl.pojo.dto.FlightAndShelfnoDto;

public interface CustLocNoService {
    boolean InsertCustLocNO(CustlocnoDto clnDto,String worknumber);

    FlightAndShelfnoDto QryCustLocNO(BillDto billDto);
}
