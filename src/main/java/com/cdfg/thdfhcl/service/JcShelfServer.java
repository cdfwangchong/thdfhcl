package com.cdfg.thdfhcl.service;

import com.cdfg.thdfhcl.pojo.dto.JcInsertShelfDto;
import com.cdfg.thdfhcl.pojo.dto.JcQrySxdnoDto;

public interface JcShelfServer {
   String insertShelf(JcInsertShelfDto hbdDto, String operator);

   JcQrySxdnoDto qrySxdno(String xsdno,String operator);
}
