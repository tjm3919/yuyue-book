package com.trkj.dto.approval;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 审批表(Approval)数据传输对象类
 *
 * @author makejava
 * @since 2023-02-08 09:59:29
 */
@Data
public class ApprovalDto implements Serializable {
    private static final long serialVersionUID = -35273306067976154L;
    
    private Integer apId;
    /**
     * 审批人id
     */
    private Integer sysId;
    /**
     * 被审批的账号
     */
    private Integer saId;
    /**
     * 被审批的用户
     */
    private Integer suId;
    /**
     * 审批事件
     */
    private String apEvent;
    /**
     * 审批状态 0-删除 1-未审批 2-通过 3-拒绝
     */
    private Integer apState;
    /**
     * 审批时间
     */
    private Date apTime;
    /**
     * 审批类型
     */
    private String apType;
    /**
     * 原因
     */
    private String apCause;


    public Integer getApId() {
        return apId;
    }

    public void setApId(Integer apId) {
        this.apId = apId;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public Integer getSaId() {
        return saId;
    }

    public void setSaId(Integer saId) {
        this.saId = saId;
    }

    public Integer getSuId() {
        return suId;
    }

    public void setSuId(Integer suId) {
        this.suId = suId;
    }

    public String getApEvent() {
        return apEvent;
    }

    public void setApEvent(String apEvent) {
        this.apEvent = apEvent;
    }

    public Integer getApState() {
        return apState;
    }

    public void setApState(Integer apState) {
        this.apState = apState;
    }

    public Date getApTime() {
        return apTime;
    }

    public void setApTime(Date apTime) {
        this.apTime = apTime;
    }

    public String getApType() {
        return apType;
    }

    public void setApType(String apType) {
        this.apType = apType;
    }

    public String getApCause() {
        return apCause;
    }

    public void setApCause(String apCause) {
        this.apCause = apCause;
    }

}

