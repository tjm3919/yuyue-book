package com.trkj.yuyuebook.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 作品标签中间表(WorksIntermediate)实体类
 *
 * @author makejava
 * @since 2023-02-07 17:02:13
 */

@Data
public class WorksIntermediateEntity implements Serializable {
    private static final long serialVersionUID = -66499381458999221L;
    
    private Integer wiId;
    /**
     * 作品id
     */
    private Integer woId;
    /**
     * 作品标签id
     */
    private Integer wtId;


    public Integer getWiId() {
        return wiId;
    }

    public void setWiId(Integer wiId) {
        this.wiId = wiId;
    }

    public Integer getWoId() {
        return woId;
    }

    public void setWoId(Integer woId) {
        this.woId = woId;
    }

    public Integer getWtId() {
        return wtId;
    }

    public void setWtId(Integer wtId) {
        this.wtId = wtId;
    }

}

