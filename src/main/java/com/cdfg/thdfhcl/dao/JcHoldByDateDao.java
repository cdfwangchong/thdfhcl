package com.cdfg.thdfhcl.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface JcHoldByDateDao {
    Map qryCheckBill(Map param);

    Map insertDts(Map param);

    Map qryJcThBill(Map param);

    Map updateJcTh(Map param);

    Map qryJcyyInfo(Map param);

}
