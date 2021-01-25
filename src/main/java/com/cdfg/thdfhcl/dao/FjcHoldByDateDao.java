package com.cdfg.thdfhcl.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FjcHoldByDateDao {
    Map qryXsdBill(Map param);

    Map insertDts(Map param);

    Map qryZcsd(Map param);

    Map thdfjgl(Map param);
}
