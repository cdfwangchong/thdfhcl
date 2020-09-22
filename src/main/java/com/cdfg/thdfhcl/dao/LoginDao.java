package com.cdfg.thdfhcl.dao;

import com.cdfg.thdfhcl.pojo.dto.ThduserDto;
import com.cdfg.thdfhcl.pojo.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {
    ThduserDto selectByPrimaryKey(UserDto userDto);
}
