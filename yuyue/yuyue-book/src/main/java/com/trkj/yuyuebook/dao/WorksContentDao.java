package com.trkj.yuyuebook.dao;

import com.trkj.yuyuebook.entity.WorksContentEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.book.WorksContentDto;
import java.util.List;

/**
 * 作品内容表(WorksContent)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-07 16:34:16
 */
 @Mapper
public interface WorksContentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param wcId 主键
     * @return 实例对象
     */
    WorksContentEntity queryById(Integer wcId);

    /**
     * 查询指定行数据
     *
     * @param worksContentDto 查询条件
     * @return 对象列表
     */
    List<WorksContentEntity> queryAll(WorksContentDto worksContentDto);

    /**
     * 统计总行数
     *
     * @param worksContentDto 查询条件
     * @return 总行数
     */
    int count(WorksContentDto worksContentDto);

    /**
     * 新增数据
     *
     * @param worksContentDto 实例对象
     * @return 影响行数
     */
    int insert(WorksContentDto worksContentDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorksContentDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WorksContentDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorksContentDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WorksContentDto> entities);

    /**
     * 修改数据
     *
     * @param worksContentDto 实例对象
     * @return 影响行数
     */
    int update(WorksContentDto worksContentDto);

    /**
     * 通过主键删除数据
     *
     * @param wcId 主键
     * @return 影响行数
     */
    int deleteById(Integer wcId);

}

