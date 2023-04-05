package com.trkj.service;

import com.trkj.dto.user.SysUserDto;
import com.github.pagehelper.PageInfo;

/**
 * 管理员表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2023-02-07 14:38:20
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param suId 主键
     * @return 实例对象
     */
    SysUserDto queryById(Integer suId);

    /**
     * 分页查询
     *
     * @param sysUserDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<SysUserDto> queryByPage(SysUserDto sysUserDto, int currentPage, int pageSize);

    PageInfo<SysUserDto> queryByPageX(SysUserDto sysUserDto, int currentPage, int pageSize);
    /**
     * 新增数据
     *
     * @param sysUserDto 实例对象
     * @return 是否成功
     */
    int insert(SysUserDto sysUserDto);

    /**
     * 修改数据
     *
     * @param sysUserDto 实例对象
     * @return 是否成功
     */
    boolean update(SysUserDto sysUserDto);

    /**
     * 通过主键删除数据
     *
     * @param suId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer suId);

}
