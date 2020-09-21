package com.cdfg.thdfhcl.dao;

import com.cdfg.thdfhcl.pojo.dto.CheckpackbillDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckpackbillDao {
    int deleteByPrimaryKey(String billNo);

    int insert(List<CheckpackbillDto> record);

    int insertSelective(CheckpackbillDto record);

    CheckpackbillDto selectByPrimaryKey(String billNo);

    int updateByPrimaryKeySelective(CheckpackbillDto record);

    int updateByPrimaryKey(CheckpackbillDto record);
}