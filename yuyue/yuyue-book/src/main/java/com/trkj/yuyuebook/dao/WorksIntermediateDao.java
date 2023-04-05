package com.trkj.yuyuebook.dao;

import com.trkj.yuyuebook.entity.WorksIntermediateEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.book.WorksIntermediateDto;
import java.util.List;

/**
 * 作品标签中间表(WorksIntermediate)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-07 17:02:13
 */
 @Mapper
public interface WorksIntermediateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param wiId 主键
     * @return 实例对象
     */
    WorksIntermediateEntity queryById(Integer wiId);

    /**
     * 查询指定行数据
     *
     * @param worksIntermediateDto 查询条件
     * @return 对象列表
     */
    List<WorksIntermediateEntity> queryAll(WorksIntermediateDto worksIntermediateDto);

    /**
     * 统计总行数
     *
     * @param worksIntermediateDto 查询条件
     * @return 总行数
     */
    int count(WorksIntermediateDto worksIntermediateDto);

    /**
     * 新增数据
     *
     * @param worksIntermediateDto 实例对象
     * @return 影响行数
     */
    int insert(WorksIntermediateDto worksIntermediateDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorksIntermediateDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WorksIntermediateDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorksIntermediateDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WorksIntermediateDto> entities);

    /**
     * 修改数据
     *
     * @param worksIntermediateDto 实例对象
     * @return 影响行数
     */
    int update(WorksIntermediateDto worksIntermediateDto);

    /**
     * 通过主键删除数据
     *
     * @param wiId 主键
     * @return 影响行数
     */
    int deleteById(Integer wiId);

}

