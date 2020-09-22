package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.UserDto;

import java.util.Map;

public interface LoginService {
    public Map<String, Object> login(UserDto userDto);
}
