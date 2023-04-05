package com.trkj.dto.integral;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 积分详情表(Integral)数据传输对象类
 *
 * @author makejava
 * @since 2023-02-07 14:30:07
 */
@Data
public class IntegralDto implements Serializable {
    private static final long serialVersionUID = 574021692896548254L;
    
    private Integer inId;
    /**
     * 用户id
     */
    private Integer suId;
    /**
     * 增减积分类型 用户注册（+10） 作者注册（+100）
阅读图书（-？）
阅读两分钟图书（+2）
管理员赠送（+？）
     */
    private String amType;
    /**
     * 增减积分数量
     */
    private Integer amNum;
    /**
     * 赠送积分的管理员id
     */
    private Integer sysId;
    /**
     * 阅读订阅的作品id
     */
    private Integer woId;
    /**
     * 创建时间
     */
    private Date inCreateTime;

    public Integer getInId() {
        return inId;
    }

    public void setInId(Integer inId) {
        this.inId = inId;
    }

    public Integer getSuId() {
        return suId;
    }

    public void setSuId(Integer suId) {
        this.suId = suId;
    }

    public String getAmType() {
        return amType;
    }

    public void setAmType(String amType) {
        this.amType = amType;
    }

    public Integer getAmNum() {
        return amNum;
    }

    public void setAmNum(Integer amNum) {
        this.amNum = amNum;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public Integer getWoId() {
        return woId;
    }

    public void setWoId(Integer woId) {
        this.woId = woId;
    }

    public Date getInCreateTime() {
        return inCreateTime;
    }

    public void setInCreateTime(Date inCreateTime) {
        this.inCreateTime = inCreateTime;
    }

}

