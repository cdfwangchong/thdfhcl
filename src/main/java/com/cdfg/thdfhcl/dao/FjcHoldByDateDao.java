package com.cdfg.thdfhcl.dao;

import com.cdfg.thdfhcl.pojo.until.SellHeadEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FjcHoldByDateDao {
    Map qryXsdBill(Map param);

    Map insertDts(Map param);
}
