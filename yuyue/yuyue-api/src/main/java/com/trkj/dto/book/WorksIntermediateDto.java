package com.trkj.dto.book;

import lombok.Data;

import java.io.Serializable;

/**
 * 作品标签中间表(WorksIntermediate)数据传输对象类
 *
 * @author makejava
 * @since 2023-02-07 17:02:13
 */
@Data
public class WorksIntermediateDto implements Serializable {
    private static final long serialVersionUID = 821491691195126201L;
    
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

