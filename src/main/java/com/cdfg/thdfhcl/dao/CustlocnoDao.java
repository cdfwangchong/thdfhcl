package com.cdfg.thdfhcl.dao;

import com.cdfg.thdfhcl.pojo.dto.BillDto;
import com.cdfg.thdfhcl.pojo.dto.CustlocnoDto;
import com.cdfg.thdfhcl.pojo.dto.FlightAndShelfnoDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface CustlocnoDao {

    int insert(List<CustlocnoDto> record);

    FlightAndShelfnoDto selectByPrimaryKey(BillDto billDto);

}