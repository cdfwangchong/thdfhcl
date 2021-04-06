package com.cdfg.thdfhcl.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface JcShelfDao {

    Map insertShelf(Map param);

    Map qrySxdno(Map param);


}
