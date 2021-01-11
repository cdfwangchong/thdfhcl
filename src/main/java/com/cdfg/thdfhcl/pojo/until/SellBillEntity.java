package com.cdfg.thdfhcl.pojo.until;

public class SellBillEntity {
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getLdrq() {
        return ldrq;
    }

    public void setLdrq(String ldrq) {
        this.ldrq = ldrq;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    private String billNo="";

    private String status="";

    private String flightNo="";

    private String ldrq="";

    private String market="";
}
