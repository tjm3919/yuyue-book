package com.trkj.dto.book;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 作品表(Works)数据传输对象类
 *
 * @author makejava
 * @since 2023-01-28 14:43:19
 */
@Data
public class WorksDto implements Serializable {
    private static final long serialVersionUID = 810012382307154992L;

    /**
     * 章节对象
     */
    private List<WorksContentDto> worksContentDto;

    /**
     * 标签对象
     */
    private List<WorksTagDto> worksTagDtos;

    /**
     * 书架对象
     */
    private BookrackDto bookrackDto;
    
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


    public Integer getWoState() {
        return woState;
    }

    public void setWoState(Integer woState) {
        this.woState = woState;
    }

    public Integer getWoId() {
        return woId;
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

    public List<WorksContentDto> getWorksContentDto() {
        return worksContentDto;
    }

    public void setWorksContentDto(List<WorksContentDto> worksContentDto) {
        this.worksContentDto = worksContentDto;
    }

    public List<WorksTagDto> getWorksTagDtos() {
        return worksTagDtos;
    }

    public void setWorksTagDtos(List<WorksTagDto> worksTagDtos) {
        this.worksTagDtos = worksTagDtos;
    }

    public BookrackDto getBookrackDto() {
        return bookrackDto;
    }

    public void setBookrackDto(BookrackDto bookrackDto) {
        this.bookrackDto = bookrackDto;
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
}

