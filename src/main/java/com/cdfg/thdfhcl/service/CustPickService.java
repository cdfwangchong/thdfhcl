package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.BoardpassDto;
import com.cdfg.thdfhcl.pojo.dto.CustPickDto;
import com.cdfg.thdfhcl.pojo.dto.FlightDto;
import com.cdfg.thdfhcl.pojo.dto.ModifyFlightDto;
import com.cdfg.thdfhcl.pojo.until.CustBillEntity;
import com.cdfg.thdfhcl.pojo.until.ThddEntity;

public interface CustPickService {
    CustBillEntity qryCustPickInfo(String CardId);

    CustBillEntity updateModifyFlight(ModifyFlightDto mfDto,String worknumber);

    String scanDjp(BoardpassDto bpDto);

    int custPick(CustPickDto cpDto,String worknumber);

    ThddEntity qryThdd(FlightDto flightDto);


}
