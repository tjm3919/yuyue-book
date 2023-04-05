package com.trkj.yuyuebook.dao;

import com.trkj.yuyuebook.entity.WorksEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.book.WorksDto;
import java.util.List;

/**
 * 作品表(Works)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-28 14:43:16
 */
 @Mapper
public interface WorksDao {

    /**
     * 通过ID查询单条数据
     *
     * @param woId 主键
     * @return 实例对象
     */
    WorksEntity queryById(Integer woId);

    /**
     * 查询指定行数据
     *
     * @param worksDto 查询条件
     * @return 对象列表
     */
    List<WorksEntity> queryAll(WorksDto worksDto);

    /**
     * 统计总行数
     *
     * @param worksDto 查询条件
     * @return 总行数
     */
    int count(WorksDto worksDto);

    /**
     * 新增数据
     *
     * @param worksDto 实例对象
     * @return 影响行数
     */
    int insert(WorksDto worksDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorksDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WorksDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorksDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WorksDto> entities);

    /**
     * 修改数据
     *
     * @param worksDto 实例对象
     * @return 影响行数
     */
    int update(WorksDto worksDto);

    /**
     * 通过主键删除数据
     *
     * @param woId 主键
     * @return 影响行数
     */
    int deleteById(Integer woId);
    int deleteTagById(Integer woId);

    List<WorksEntity> queryByTopten();

    List<WorksEntity> queryByType(WorksDto worksDto);
}

