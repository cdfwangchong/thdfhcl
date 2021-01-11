package com.cdfg.thdfhcl.pojo.dto;

import com.cdfg.thdfhcl.pojo.until.SellBillEntity;

import java.util.List;

public class ModifyFlightDto {
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getNew_flightNum() {
        return new_flightNum;
    }

    public void setNew_flightNum(String new_flightNum) {
        this.new_flightNum = new_flightNum;
    }

    public String getNew_flightTime() {
        return new_flightTime;
    }

    public void setNew_flightTime(String new_flightTime) {
        this.new_flightTime = new_flightTime;
    }

    public String getOld_flightNum() {
        return old_flightNum;
    }

    public void setOld_flightNum(String old_flightNum) {
        this.old_flightNum = old_flightNum;
    }

    public String getOld_flightTime() {
        return old_flightTime;
    }

    public void setOld_flightTime(String old_flightTime) {
        this.old_flightTime = old_flightTime;
    }

    public List<SellBillEntity> getSellhead() {
        return sellhead;
    }

    public void setSellhead(List<SellBillEntity> sellhead) {
        this.sellhead = sellhead;
    }

    private String cardId;

    private String new_flightNum;

    private String new_flightTime;

    private String old_flightNum;

    private String old_flightTime;

    private List<SellBillEntity> sellhead;

}
