package com.cdfg.thdfhcl.pojo.dto;

/**
 * 扫描登记牌
 */
public class BoardpassDto {
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

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    private String cardId;

    private String ticketCode;

    private String billNo;

}
