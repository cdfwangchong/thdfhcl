package com.cdfg.thdfhcl.pojo.dto;

public class BillDto {
    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    private String billType;
    private String billNo;
}
