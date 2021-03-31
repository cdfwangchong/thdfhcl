package com.cdfg.thdfhcl.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InsertPackBillDto {
    private String billType;
    private String billNO;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cpbDate;

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillNO() {
        return billNO;
    }

    public void setBillNO(String billNO) {
        this.billNO = billNO;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCpbDate() {
        return cpbDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public void setCpbDate(Date cpbDate) {
        this.cpbDate = cpbDate;
    }
}
