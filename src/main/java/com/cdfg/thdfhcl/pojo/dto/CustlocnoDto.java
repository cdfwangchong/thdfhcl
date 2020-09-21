package com.cdfg.thdfhcl.pojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * CUSTLOCNO
 * @author 
 */

public class CustlocnoDto implements Serializable {

    public String getCustpass() {
        return custpass;
    }

    public void setCustpass(String custpass) {
        this.custpass = custpass;
    }

    public String getShelfno() {
        return shelfno;
    }

    public void setShelfno(String shelfno) {
        this.shelfno = shelfno;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOperno() {
        return operno;
    }

    public void setOperno(String operno) {
        this.operno = operno;
    }
    @JsonFormat(pattern="yyyy-mm-dd HH:mm:ss")
    public Date getOpertime() {
        return opertime;
    }
    @JsonFormat(pattern="yyyy-mm-dd HH:mm:ss")
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public String getThdd() {
        return thdd;
    }

    public void setThdd(String thdd) {
        this.thdd = thdd;
    }

    /**
     * 顾客证件号
     */
    private String custpass;

    /**
     * 包裹货位号
     */
    private String shelfno;

    /**
     * 标志位（Y为已提货）
     */
    private String flag;

    /**
     * 操作员
     */
    private String operno;

    /**
     * 操作时间
     */
    @JsonFormat(pattern="yyyy-mm-dd HH:mm:ss")
    private Date opertime;

    /**
     * 提货地点
     */
    private String thdd;

    private static final long serialVersionUID = 1L;
}