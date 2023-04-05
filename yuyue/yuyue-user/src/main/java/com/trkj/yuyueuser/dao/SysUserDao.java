package com.trkj.yuyueuser.dao;

import com.trkj.yuyueuser.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.user.SysUserDto;
import java.util.List;

/**
 * 管理员表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-07 14:38:20
 */
 @Mapper
public interface SysUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param suId 主键
     * @return 实例对象
     */
    SysUserEntity queryById(Integer suId);

    /**
     * 查询指定行数据
     *
     * @param sysUserDto 查询条件
     * @return 对象列表
     */
    List<SysUserEntity> queryAll(SysUserDto sysUserDto);

    /**
     * 统计总行数
     *
     * @param sysUserDto 查询条件
     * @return 总行数
     */
    int count(SysUserDto sysUserDto);

    /**
     * 新增数据
     *
     * @param sysUserDto 实例对象
     * @return 影响行数
     */
    int insert(SysUserDto sysUserDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserDto> entities);

    /**
     * 修改数据
     *
     * @param sysUserDto 实例对象
     * @return 影响行数
     */
    int update(SysUserDto sysUserDto);

    /**
     * 通过主键删除数据
     *
     * @param suId 主键
     * @return 影响行数
     */
    int deleteById(Integer suId);

    List<SysUserEntity> queryAllX(SysUserDto sysUserDto);
}

