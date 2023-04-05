package com.trkj.dto.book;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 图书订阅表(Subscription)数据传输对象类
 *
 * @author makejava
 * @since 2023-02-08 15:24:32
 */
@Data
public class SubscriptionDto implements Serializable {
    private static final long serialVersionUID = 199683108798243528L;
    
    private Integer subId;
    /**
     * 订阅人id
     */
    private Integer suId;
    /**
     * 图书id
     */
    private Integer woId;
    /**
     * 订阅时间
     */
    private Date subTime;
    /**
     * 其他
     */
    private String subOther;
    /**
     * 状态
     */
    private Integer subState;


    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getSuId() {
        return suId;
    }

    public void setSuId(Integer suId) {
        this.suId = suId;
    }

    public Integer getWoId() {
        return woId;
    }

    public void setWoId(Integer woId) {
        this.woId = woId;
    }

    public Date getSubTime() {
        return subTime;
    }

    public void setSubTime(Date subTime) {
        this.subTime = subTime;
    }

    public String getSubOther() {
        return subOther;
    }

    public void setSubOther(String subOther) {
        this.subOther = subOther;
    }

    public Integer getSubState() {
        return subState;
    }

    public void setSubState(Integer subState) {
        this.subState = subState;
    }
}

