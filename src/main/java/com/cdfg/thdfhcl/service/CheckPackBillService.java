package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.InsertPackBillDto;

public interface CheckPackBillService {
    boolean insert(InsertPackBillDto packbillitem,String worknumber);
}
