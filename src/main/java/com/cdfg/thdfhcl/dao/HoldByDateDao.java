package com.cdfg.thdfhcl.dao;

import com.cdfg.thdfhcl.pojo.dto.HoldByDateDto;
import com.cdfg.thdfhcl.pojo.until.SellHeadEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface HoldByDateDao {
    SellHeadEntity qryCheckBill(String billNO);

    Map insertDts(Map<String,String> param);
}
