package com.trkj.yuyueintegral.dao;

import com.trkj.yuyueintegral.entity.IntegralEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.integral.IntegralDto;
import java.util.List;

/**
 * 积分详情表(Integral)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-07 14:30:03
 */
 @Mapper
public interface IntegralDao {

    /**
     * 通过ID查询单条数据
     *
     * @param inId 主键
     * @return 实例对象
     */
    IntegralEntity queryById(Integer inId);

    /**
     * 查询指定行数据
     *
     * @param integralDto 查询条件
     * @return 对象列表
     */
    List<IntegralEntity> queryAll(IntegralDto integralDto);

    /**
     * 统计总行数
     *
     * @param integralDto 查询条件
     * @return 总行数
     */
    int count(IntegralDto integralDto);

    /**
     * 新增数据
     *
     * @param integralDto 实例对象
     * @return 影响行数
     */
    int insert(IntegralDto integralDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IntegralDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IntegralDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IntegralDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IntegralDto> entities);

    /**
     * 修改数据
     *
     * @param integralDto 实例对象
     * @return 影响行数
     */
    int update(IntegralDto integralDto);

    /**
     * 通过主键删除数据
     *
     * @param inId 主键
     * @return 影响行数
     */
    int deleteById(Integer inId);

}

