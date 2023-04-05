package com.trkj.yuyuejwt.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysAccount)实体类
 *
 * @author makejava
 * @since 2023-01-25 10:33:12
 */
public class SysAccountEntity implements Serializable {
    private static final long serialVersionUID = -83617020909436195L;
    
    private Integer saId;
    /**
     * 账号
     */
    private String saName;
    /**
     * 密码
     */
    private String saPassword;
    /**
     * 账号类型 1-管理员账号 2-用户账号
     */
    private Integer saType;
    /**
     * 账号状态 0-禁用 1-正常
     */
    private Integer saState;
    /**
     * 是否过期 0-过期 1-未过期
     */
    private Integer isExpired;
    /**
     * 创建时间
     */
    private Date saCreateTime;
    /**
     * 更新时间
     */
    private Date saUpdateTime;
    /**
     * 用户id
     */
    private Integer suId;


    public Integer getSaId() {
        return saId;
    }

    public void setSaId(Integer saId) {
        this.saId = saId;
    }

    public String getSaName() {
        return saName;
    }

    public void setSaName(String saName) {
        this.saName = saName;
    }

    public String getSaPassword() {
        return saPassword;
    }

    public void setSaPassword(String saPassword) {
        this.saPassword = saPassword;
    }

    public Integer getSaType() {
        return saType;
    }

    public void setSaType(Integer saType) {
        this.saType = saType;
    }

    public Integer getSaState() {
        return saState;
    }

    public void setSaState(Integer saState) {
        this.saState = saState;
    }

    public Integer getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Integer isExpired) {
        this.isExpired = isExpired;
    }

    public Date getSaCreateTime() {
        return saCreateTime;
    }

    public void setSaCreateTime(Date saCreateTime) {
        this.saCreateTime = saCreateTime;
    }

    public Date getSaUpdateTime() {
        return saUpdateTime;
    }

    public void setSaUpdateTime(Date saUpdateTime) {
        this.saUpdateTime = saUpdateTime;
    }

    public Integer getSuId() {
        return suId;
    }

    public void setSuId(Integer suId) {
        this.suId = suId;
    }

}

