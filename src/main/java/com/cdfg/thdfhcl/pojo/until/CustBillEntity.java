package com.cdfg.thdfhcl.pojo.until;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class CustBillEntity {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getFlightTime() {
        return flightTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mi:dd",timezone = "GMT+8")
    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public String getFlightAddress() {
        return flightAddress;
    }

    public void setFlightAddress(String flightAddress) {
        this.flightAddress = flightAddress;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getFhNum() {
        return fhNum;
    }

    public void setFhNum(String fhNum) {
        this.fhNum = fhNum == null?"":fhNum.trim();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getShelfno() {
        return shelfno;
    }

    public void setShelfno(String shelfno) {
        this.shelfno = shelfno == null?"":shelfno.trim();
    }

    public String getFlightFlag() {
        return flightFlag;
    }

    public void setFlightFlag(String flightFlag) {
        this.flightFlag = flightFlag;
    }

    public List<SellBillEntity> getSellhead() {
        return sellhead;
    }

    public void setSellhead(List<SellBillEntity> sellhead) {
        this.sellhead = sellhead;
    }

    private String userName="";

    private String cardId;

    private String flightNum="";

    private String flightTime="";

    private String flightAddress="";

    private String addressName="";

    private String fhNum="";

    private int total;

    private String shelfno="";

    private String flightFlag="";

    private List<SellBillEntity> sellhead;
}
