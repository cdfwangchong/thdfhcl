package com.cdfg.thdfhcl.dao;

import com.cdfg.thdfhcl.pojo.dto.CheckpackbillDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckpackbillDao {

    int insert(List<CheckpackbillDto> record);
}