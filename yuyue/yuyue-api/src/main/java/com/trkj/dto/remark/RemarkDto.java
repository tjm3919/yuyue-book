package com.trkj.dto.remark;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 作品评论表(Remark)数据传输对象类
 *
 * @author makejava
 * @since 2023-02-05 16:57:16
 */
@Data
public class RemarkDto implements Serializable {
    private static final long serialVersionUID = 225394841769725090L;
    
    private Integer reId;
    /**
     * 评论人id
     */
    private Integer suId;
    /**
     * 作品id
     */
    private Integer woId;
    /**
     * 评论内容
     */
    private String reContent;
    /**
     * 评论时间
     */
    private Date reTime;
    /**
     * 评论状态 0-删除 1-正常
     */
    private Integer reState;


    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
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

    public String getReContent() {
        return reContent;
    }

    public void setReContent(String reContent) {
        this.reContent = reContent;
    }

    public Date getReTime() {
        return reTime;
    }

    public void setReTime(Date reTime) {
        this.reTime = reTime;
    }

    public Integer getReState() {
        return reState;
    }

    public void setReState(Integer reState) {
        this.reState = reState;
    }

}

