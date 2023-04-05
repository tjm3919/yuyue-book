package com.trkj.yuyueremark.dao;

import com.trkj.yuyueremark.entity.RemarkEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.remark.RemarkDto;
import java.util.List;

/**
 * 作品评论表(Remark)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-05 16:57:11
 */
 @Mapper
public interface RemarkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param reId 主键
     * @return 实例对象
     */
    RemarkEntity queryById(Integer reId);

    /**
     * 查询指定行数据
     *
     * @param remarkDto 查询条件
     * @return 对象列表
     */
    List<RemarkEntity> queryAll(RemarkDto remarkDto);

    /**
     * 统计总行数
     *
     * @param remarkDto 查询条件
     * @return 总行数
     */
    int count(RemarkDto remarkDto);

    /**
     * 新增数据
     *
     * @param remarkDto 实例对象
     * @return 影响行数
     */
    int insert(RemarkDto remarkDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RemarkDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RemarkDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RemarkDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RemarkDto> entities);

    /**
     * 修改数据
     *
     * @param remarkDto 实例对象
     * @return 影响行数
     */
    int update(RemarkDto remarkDto);

    /**
     * 通过主键删除数据
     *
     * @param reId 主键
     * @return 影响行数
     */
    int deleteById(Integer reId);

}

