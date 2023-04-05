package com.trkj.yuyuebook.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 作品内容表(WorksContent)实体类
 *
 * @author makejava
 * @since 2023-02-07 16:34:16
 */

@Data
public class WorksContentEntity implements Serializable {
    private static final long serialVersionUID = -31811692743169954L;
    /**
     * 作品章节
     */
    private Integer wcId;
    /**
     * 章节标题
     */
    private String wcTitle;
    /**
     * 章节内容
     */
    private String wcContent;
    /**
     * 作品id
     */
    private Integer woId;
    /**
     * 创建时间
     */
    private Date wcCreateTime;
    /**
     * 章节最后修改时间
     */
    private Date wcUpdateEndtime;
    /**
     * 章节定稿时间
     */
    private Date wcFinalizeTime;
    /**
     * 章节点击量
     */
    private Integer wcChapterHits;
    /**
     * 章节状态 0-删除 1-正常
     */
    private Integer wcState;


    public Integer getWcId() {
        return wcId;
    }

    public void setWcId(Integer wcId) {
        this.wcId = wcId;
    }

    public String getWcTitle() {
        return wcTitle;
    }

    public void setWcTitle(String wcTitle) {
        this.wcTitle = wcTitle;
    }

    public String getWcContent() {
        return wcContent;
    }

    public void setWcContent(String wcContent) {
        this.wcContent = wcContent;
    }

    public Integer getWoId() {
        return woId;
    }

    public void setWoId(Integer woId) {
        this.woId = woId;
    }

    public Date getWcCreateTime() {
        return wcCreateTime;
    }

    public void setWcCreateTime(Date wcCreateTime) {
        this.wcCreateTime = wcCreateTime;
    }

    public Date getWcUpdateEndtime() {
        return wcUpdateEndtime;
    }

    public void setWcUpdateEndtime(Date wcUpdateEndtime) {
        this.wcUpdateEndtime = wcUpdateEndtime;
    }

    public Date getWcFinalizeTime() {
        return wcFinalizeTime;
    }

    public void setWcFinalizeTime(Date wcFinalizeTime) {
        this.wcFinalizeTime = wcFinalizeTime;
    }

    public Integer getWcChapterHits() {
        return wcChapterHits;
    }

    public void setWcChapterHits(Integer wcChapterHits) {
        this.wcChapterHits = wcChapterHits;
    }

    public Integer getWcState() {
        return wcState;
    }

    public void setWcState(Integer wcState) {
        this.wcState = wcState;
    }

}

