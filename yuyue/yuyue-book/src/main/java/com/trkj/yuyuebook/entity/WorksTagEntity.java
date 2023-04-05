package com.trkj.yuyuebook.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 作品标签表(WorksTag)实体类
 *
 * @author makejava
 * @since 2023-01-28 15:06:25
 */
@Data
public class WorksTagEntity implements Serializable {
    private static final long serialVersionUID = -92060339303472359L;
    
    private Integer wtId;
    /**
     * 标签名
     */
    private String wtName;
    /**
     * 创建时间
     */
    private Date wtCreateTime;
    /**
     * 创建人
     */
    private String wtCreateBy;


    public Integer getWtId() {
        return wtId;
    }

    public void setWtId(Integer wtId) {
        this.wtId = wtId;
    }

    public String getWtName() {
        return wtName;
    }

    public void setWtName(String wtName) {
        this.wtName = wtName;
    }

    public Date getWtCreateTime() {
        return wtCreateTime;
    }

    public void setWtCreateTime(Date wtCreateTime) {
        this.wtCreateTime = wtCreateTime;
    }

    public String getWtCreateBy() {
        return wtCreateBy;
    }

    public void setWtCreateBy(String wtCreateBy) {
        this.wtCreateBy = wtCreateBy;
    }

}

