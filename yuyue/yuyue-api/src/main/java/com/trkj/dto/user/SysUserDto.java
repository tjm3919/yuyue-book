package com.trkj.dto.user;

import com.trkj.dto.account.SysAccountDto;
import com.trkj.dto.book.SubscriptionDto;
import com.trkj.dto.book.WorksDto;
import com.trkj.dto.log.LoginLogDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 管理员表(SysUser)数据传输对象类
 *
 * @author makejava
 * @since 2023-02-07 14:38:23
 */
@Data
public class SysUserDto implements Serializable {
    private static final long serialVersionUID = -12030722543480160L;

    /**
     * 用户账号
     */
    private SysAccountDto sysAccountDto;

    /**
     * 订阅的图书
     */
    List<SubscriptionDto> subscriptionDtos;
    /**
     * 登录日志
     */
    List<LoginLogDto> loginLogDtos;
    /**
     * 作品
     */
    List<WorksDto> worksDtos;

    private Integer suId;
    /**
     * 用户名称
     */
    private String suName;
    /**
     * 用户昵称
     */
    private String suNickname;
    /**
     * 用户类型 超级管理员 普通管理员 作者 读者
     */
    private String suType;
    /**
     * 用户电话
     */
    private String suPhone;
    /**
     * 用户状态 0-删除 1-正常
     */
    private Integer suState;
    /**
     * 账号id
     */
    private Integer saId;
    /**
     * 总积分
     */
    private Integer sumNum;
    /**
     * 创建时间
     */
    private Date suCreateTime;
    /**
     * 更新时间
     */
    private Date suUpdateTime;


    public Integer getSuId() {
        return suId;
    }

    public void setSuId(Integer suId) {
        this.suId = suId;
    }

    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String getSuNickname() {
        return suNickname;
    }

    public void setSuNickname(String suNickname) {
        this.suNickname = suNickname;
    }

    public String getSuType() {
        return suType;
    }

    public void setSuType(String suType) {
        this.suType = suType;
    }

    public String getSuPhone() {
        return suPhone;
    }

    public void setSuPhone(String suPhone) {
        this.suPhone = suPhone;
    }

    public Integer getSuState() {
        return suState;
    }

    public void setSuState(Integer suState) {
        this.suState = suState;
    }

    public Integer getSaId() {
        return saId;
    }

    public void setSaId(Integer saId) {
        this.saId = saId;
    }

    public Integer getSumNum() {
        return sumNum;
    }

    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
    }

    public Date getSuCreateTime() {
        return suCreateTime;
    }

    public void setSuCreateTime(Date suCreateTime) {
        this.suCreateTime = suCreateTime;
    }

    public Date getSuUpdateTime() {
        return suUpdateTime;
    }

    public void setSuUpdateTime(Date suUpdateTime) {
        this.suUpdateTime = suUpdateTime;
    }

}

