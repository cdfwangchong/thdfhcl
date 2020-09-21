package com.cdfg.thdfhcl.pojo.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * THDUSER
 * @author 
 */
@Data
public class ThduserDto implements Serializable {
    /**
     * 工号
     */
    private String userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 提货点
     */
    private String thdAddress;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 账号状态
     */
    private String status;

    private static final long serialVersionUID = 1L;
}