package com.trkj.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trkj.dto.approval.ApprovalDto;
import com.trkj.dto.log.LoginLogDto;
import com.trkj.dto.user.SysUserDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysAccount)数据传输对象类
 *
 * @author makejava
 * @since 2023-01-25 10:33:13
 */

@Data
public class SysAccountDto implements Serializable {
    private static final long serialVersionUID = -50025308281190974L;

    /**
     * 审批对象
     */
    private ApprovalDto approvalDto;

    /**
     * 用户对象
     */
    private SysUserDto sysUserDto;

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
     * 账号类型 1-管理员账号 2-用户账号 3-作者账号
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date saCreateTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date saUpdateTime;
    /**
     * 用户id
     */
    private Integer suId;

    private String token;

    /**
     * 登录日志信息对象
     */
    private LoginLogDto logDto;


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

