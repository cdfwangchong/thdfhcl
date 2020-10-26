package com.cdfg.thdfhcl.pojo.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * CHECKPACKBILL
 * @author 
 */
@Data
public class CheckpackbillDto implements Serializable {
    /**
     * 单据号
     */
    private String billNo;

    /**
     * 单据类型
     */
    private String billType;

    /**
     * 操作员
     */
    private String cpbOperator;

    /**
     * 验收标志
     */
    private String cpbFlag;

    /**
     * 验收日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cpbDate;

    /**
     * 备注
     */
    private String cpbRemark;

    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date creatDate;

    /**
     * 更新日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    private static final long serialVersionUID = 1L;
}