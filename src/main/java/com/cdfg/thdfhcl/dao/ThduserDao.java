package com.cdfg.thdfhcl.dao;

import com.cdfg.thdfhcl.pojo.dto.ThduserDto;

public interface ThduserDao {
    int deleteByPrimaryKey(String userId);

    int insert(ThduserDto record);

    int insertSelective(ThduserDto record);

    ThduserDto selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(ThduserDto record);

    int updateByPrimaryKey(ThduserDto record);
}