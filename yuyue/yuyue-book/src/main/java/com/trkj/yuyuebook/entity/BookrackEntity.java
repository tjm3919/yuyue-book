package com.trkj.yuyuebook.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 书架表(Bookrack)实体类
 *
 * @author makejava
 * @since 2023-01-28 16:00:35
 */
@Data
public class BookrackEntity implements Serializable {
    private static final long serialVersionUID = -75898152752361307L;
    
    private Integer boId;
    /**
     * 创建时间
     */
    private Date boCreateTime;
    /**
     * 创建人
     */
    private String boCreateBy;
    /**
     * 书架状态 0-删除 1-正常
     */
    private Integer boState;


    public Integer getBoId() {
        return boId;
    }

    public void setBoId(Integer boId) {
        this.boId = boId;
    }

    public Date getBoCreateTime() {
        return boCreateTime;
    }

    public void setBoCreateTime(Date boCreateTime) {
        this.boCreateTime = boCreateTime;
    }

    public String getBoCreateBy() {
        return boCreateBy;
    }

    public void setBoCreateBy(String boCreateBy) {
        this.boCreateBy = boCreateBy;
    }

    public Integer getBoState() {
        return boState;
    }

    public void setBoState(Integer boState) {
        this.boState = boState;
    }

}

