package com.trkj.yuyuejwt.dao;

import com.trkj.yuyuejwt.entity.SysAccountEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.account.SysAccountDto;
import java.util.List;

/**
 * (SysAccount)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-25 10:33:11
 */
 @Mapper
public interface SysAccountDao {

    /**
     * 通过ID查询单条数据
     *
     * @param saId 主键
     * @return 实例对象
     */
    SysAccountEntity queryById(Integer saId);

    /**
     * 查询指定行数据
     *
     * @param sysAccountDto 查询条件
     * @return 对象列表
     */
    List<SysAccountEntity> queryAll(SysAccountDto sysAccountDto);

    /**
     * 统计总行数
     *
     * @param sysAccountDto 查询条件
     * @return 总行数
     */
    int count(SysAccountDto sysAccountDto);

    /**
     * 新增数据
     *
     * @param sysAccountDto 实例对象
     * @return 影响行数
     */
    int insert(SysAccountDto sysAccountDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysAccountDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysAccountDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysAccountDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysAccountDto> entities);

    /**
     * 修改数据
     *
     * @param sysAccountDto 实例对象
     * @return 影响行数
     */
    int update(SysAccountDto sysAccountDto);

    /**
     * 通过主键删除数据
     *
     * @param saId 主键
     * @return 影响行数
     */
    int deleteById(Integer saId);

    SysAccountEntity findAllByUsername(String account_name);
}

