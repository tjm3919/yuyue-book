package com.trkj.yuyuebook.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 作品表(Works)实体类
 *
 * @author makejava
 * @since 2023-01-28 14:43:18
 */

@Data
public class WorksEntity implements Serializable {
    private static final long serialVersionUID = 347724049361969796L;

    /**
     * 图书章节
     */
    private List<WorksContentEntity> worksContentEntities;
    /**
     * 标签对象
     */
    private List<WorksTagEntity> worksTagEntity;
    /**
     * 书架对象
     */
    private BookrackEntity bookrackEntity;

    private Integer woId;
    /**
     * 作品标题
     */
    private String woTitle;
    /**
     * 作品概要
     */
    private String summary;
    /**
     * 作者id
     */
    private Integer suId;
    /**
     * 图书阅读量
     */
    private Integer readNum;
    /**
     * 章节数量
     */
    private Integer sectionNum;
    /**
     * 评价数量
     */
    private Integer appraiseNum;
    /**
     * 点赞数量
     */
    private Integer likesNum;
    /**
     * 主角名（>1以"，"分隔）
     */
    private String protagonistName;
    /**
     * 配角名（>1以"，"分隔）
     */
    private String supportName;
    /**
     * 作品类型
     */
    private String worksType;
    /**
     * 创建时间
     */
    private Date worksCreateTime;
    /**
     * 书架id
     */
    private Integer boId;

    /**
     * 订阅图书需要的积分
     */
    private Integer boIntegral;
    /**
     * 图书状态 0-禁用 1-启用
     */
    private Integer woState;

    public Integer getWoId() {
        return woId;
    }

    public List<WorksTagEntity> getWorksTagEntity() {
        return worksTagEntity;
    }

    public void setWorksTagEntity(List<WorksTagEntity> worksTagEntity) {
        this.worksTagEntity = worksTagEntity;
    }

    public BookrackEntity getBookrackEntity() {
        return bookrackEntity;
    }

    public void setBookrackEntity(BookrackEntity bookrackEntity) {
        this.bookrackEntity = bookrackEntity;
    }

    public Integer getSuId() {
        return suId;
    }

    public void setSuId(Integer suId) {
        this.suId = suId;
    }

    public Integer getBoIntegral() {
        return boIntegral;
    }

    public void setBoIntegral(Integer boIntegral) {
        this.boIntegral = boIntegral;
    }

    public Integer getWoState() {
        return woState;
    }

    public void setWoState(Integer woState) {
        this.woState = woState;
    }

    public void setWoId(Integer woId) {
        this.woId = woId;
    }

    public String getWoTitle() {
        return woTitle;
    }

    public void setWoTitle(String woTitle) {
        this.woTitle = woTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(Integer sectionNum) {
        this.sectionNum = sectionNum;
    }

    public Integer getAppraiseNum() {
        return appraiseNum;
    }

    public void setAppraiseNum(Integer appraiseNum) {
        this.appraiseNum = appraiseNum;
    }

    public Integer getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Integer likesNum) {
        this.likesNum = likesNum;
    }

    public String getProtagonistName() {
        return protagonistName;
    }

    public void setProtagonistName(String protagonistName) {
        this.protagonistName = protagonistName;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public String getWorksType() {
        return worksType;
    }

    public void setWorksType(String worksType) {
        this.worksType = worksType;
    }

    public Date getWorksCreateTime() {
        return worksCreateTime;
    }

    public void setWorksCreateTime(Date worksCreateTime) {
        this.worksCreateTime = worksCreateTime;
    }

    public Integer getBoId() {
        return boId;
    }

    public void setBoId(Integer boId) {
        this.boId = boId;
    }

}

