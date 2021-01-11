package com.cdfg.thdfhcl.dao;

import com.cdfg.thdfhcl.pojo.dto.BoardpassDto;
import com.cdfg.thdfhcl.pojo.until.CustBillEntity;
import com.cdfg.thdfhcl.pojo.until.ThddEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CustPickDao {
    Map qryCustPickInfo(Map parammap);

    Map updateModifyFlight(Map map);

//    CustBillEntity scanDjp(BoardpassDto bpDto);

    Map cusnPick(Map map);

    Map qryScanDjp(Map map);

    ThddEntity qryThdd(String FlightNo);


}
