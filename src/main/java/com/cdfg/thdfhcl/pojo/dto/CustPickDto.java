package com.cdfg.thdfhcl.pojo.dto;

import com.cdfg.thdfhcl.pojo.until.SellBillEntity;

import java.util.List;

public class CustPickDto {
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public List<SellBillEntity> getSellhead() {
        return sellhead;
    }

    public void setSellhead(List<SellBillEntity> sellhead) {
        this.sellhead = sellhead;
    }

    private String cardId;

    private String ticketCode;

    private List<SellBillEntity> sellhead;
}
