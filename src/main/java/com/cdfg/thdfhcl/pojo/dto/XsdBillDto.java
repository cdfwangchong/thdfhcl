package com.cdfg.thdfhcl.pojo.dto;

import java.util.Date;

public class XsdBillDto {
    public String getBillNO() {
        return billNO;
    }

    public void setBillNO(String billNO) {
        this.billNO = billNO;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getZcsdid() {
        return zcsdid;
    }

    public void setZcsdid(String zcsdid) {
        this.zcsdid = zcsdid;
    }

    public String getThdd() {
        return thdd;
    }

    public void setThdd(String thdd) {
        this.thdd = thdd;
    }

    public Date getZcrq() {
        return zcrq;
    }

    public void setZcrq(Date zcrq) {
        this.zcrq = zcrq;
    }

    private String billNO;
    private String market;
    private Date zcrq;
    private String zcsdid;
    private String thdd;
}
