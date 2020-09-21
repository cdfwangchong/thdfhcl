package com.cdfg.thdfhcl.pojo.dto;

import javax.xml.crypto.Data;
import java.util.Date;

public class FlightAndShelfnoDto {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getFlightAdrres() {
        return flightAdrres;
    }

    public void setFlightAdrres(String flightAdrres) {
        this.flightAdrres = flightAdrres;
    }

    public String getFhNum() {
        return fhNum;
    }

    public void setFhNum(String fhNum) {
        this.fhNum = fhNum;
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
        this.shelfno = shelfno;
    }

    private String userName;
    private String cardID;
    private Date flightTime;
    private String flightNum;
    private String flightAdrres;
    private String fhNum;
    private int total;
    private String shelfno;

}
