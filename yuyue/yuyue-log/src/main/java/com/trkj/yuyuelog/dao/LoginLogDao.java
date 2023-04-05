package com.trkj.yuyuelog.dao;

import com.trkj.yuyuelog.entity.LoginLogEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.log.LoginLogDto;
import java.util.List;

/**
 * 登录日志表(LoginLog)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-24 11:11:35
 */
 @Mapper
public interface LoginLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param llId 主键
     * @return 实例对象
     */
    LoginLogEntity queryById(Integer llId);

    /**
     * 查询指定行数据
     *
     * @param loginLogDto 查询条件
     * @return 对象列表
     */
    List<LoginLogEntity> queryAll(LoginLogDto loginLogDto);

    /**
     * 统计总行数
     *
     * @param loginLogDto 查询条件
     * @return 总行数
     */
    int count(LoginLogDto loginLogDto);

    /**
     * 新增数据
     *
     * @param loginLogDto 实例对象
     * @return 影响行数
     */
    int insert(LoginLogDto loginLogDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<LoginLogDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<LoginLogDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<LoginLogDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<LoginLogDto> entities);

    /**
     * 修改数据
     *
     * @param loginLogDto 实例对象
     * @return 影响行数
     */
    int update(LoginLogDto loginLogDto);

    /**
     * 通过主键删除数据
     *
     * @param llId 主键
     * @return 影响行数
     */
    int deleteById(Integer llId);

    /**
     * 查询最后一条数据
     * @return
     */
    LoginLogEntity queryEnd(Integer said);
}

