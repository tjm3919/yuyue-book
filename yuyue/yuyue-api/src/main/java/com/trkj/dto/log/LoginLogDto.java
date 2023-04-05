package com.trkj.dto.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * 登录日志表(LoginLog)数据传输对象类
 *
 * @author makejava
 * @since 2023-01-24 11:11:37
 */
public class LoginLogDto implements Serializable {
    private static final long serialVersionUID = -44913256432587755L;
    
    private Integer llId;
    /**
     * 登录人id（用户、管理员）
     */
    private Integer suId;
    /**
     * 登录人账号
     */
    private Integer saId;
    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date llUpTime;
    /**
     * 登出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date llOutTime;
    /**
     * 登录IP
     */
    private String llIp;
    /**
     * 浏览器类型
     */
    private String llBrowserType;
    /**
     * 客户端类型
     */
    private String llClientsideType;
    /**
     * 登录人类型 1-管理员 2-用户 3-作者
     */
    private Integer llLoginType;


    public Integer getLlId() {
        return llId;
    }

    public void setLlId(Integer llId) {
        this.llId = llId;
    }

    public Integer getSuId() {
        return suId;
    }

    public void setSuId(Integer suId) {
        this.suId = suId;
    }

    public Integer getSaId() {
        return saId;
    }

    public void setSaId(Integer saId) {
        this.saId = saId;
    }

    public Date getLlUpTime() {
        return llUpTime;
    }

    public void setLlUpTime(Date llUpTime) {
        this.llUpTime = llUpTime;
    }

    public Date getLlOutTime() {
        return llOutTime;
    }

    public void setLlOutTime(Date llOutTime) {
        this.llOutTime = llOutTime;
    }

    public String getLlIp() {
        return llIp;
    }

    public void setLlIp(String llIp) {
        this.llIp = llIp;
    }

    public String getLlBrowserType() {
        return llBrowserType;
    }

    public void setLlBrowserType(String llBrowserType) {
        this.llBrowserType = llBrowserType;
    }

    public String getLlClientsideType() {
        return llClientsideType;
    }

    public void setLlClientsideType(String llClientsideType) {
        this.llClientsideType = llClientsideType;
    }

    public Integer getLlLoginType() {
        return llLoginType;
    }

    public void setLlLoginType(Integer llLoginType) {
        this.llLoginType = llLoginType;
    }

}

