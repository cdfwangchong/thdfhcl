package com.cdfg.thdfhcl.pojo.dto;

import java.util.Date;

public class InsertPackBillDto {
    private String billType;
    private String billNO;
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

    public Date getCpbDate() {
        return cpbDate;
    }

    public void setCpbDate(Date cpbDate) {
        this.cpbDate = cpbDate;
    }
}
