package com.trkj.yuyuemessage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统消息表(Message)实体类
 *
 * @author makejava
 * @since 2023-01-28 19:21:08
 */

@Data
public class MessageEntity implements Serializable {
    private static final long serialVersionUID = 227544139721344095L;

    private Integer meId;
    /**
     * 发送人id
     */
    private Integer sysId;
    /**
     * 消息内容
     */
    private String meContent;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 接收人id
     */
    private Integer suId;
    /**
     * 消息状态 0-删除 1-未接收 2-已接收
     */
    private Integer meState;
    /**
     * 消息接收时间
     */
    private Date receiptTime;


    public Integer getMeId() {
        return meId;
    }

    public void setMeId(Integer meId) {
        this.meId = meId;
    }

    public Integer getSysId() {
        return sysId;
    }

    public Integer getSuId() {
        return suId;
    }

    public void setSuId(Integer suId) {
        this.suId = suId;
    }
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getMeContent() {
        return meContent;
    }

    public void setMeContent(String meContent) {
        this.meContent = meContent;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getMeState() {
        return meState;
    }

    public void setMeState(Integer meState) {
        this.meState = meState;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

}

