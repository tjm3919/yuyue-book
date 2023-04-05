package com.trkj.service;

import com.github.pagehelper.PageInfo;
import com.trkj.dto.account.SysAccountDto;

/**
 * (SysAccount)表服务接口
 *
 * @author makejava
 * @since 2023-01-25 10:33:12
 */
public interface SysAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param saId 主键
     * @return 实例对象
     */
    SysAccountDto queryById(Integer saId);

    /**
     * 分页查询
     *
     * @param sysAccountDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<SysAccountDto> queryByPage(SysAccountDto sysAccountDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param sysAccountDto 实例对象
     * @return 是否成功
     */
    boolean insert(SysAccountDto sysAccountDto);

    /**
     * 新增作者、管理员
     *
     * @param sysAccountDto 实例对象
     * @return 是否成功
     */
    public boolean insertSA(SysAccountDto sysAccountDto);

    /**
     * 修改数据
     *
     * @param sysAccountDto 实例对象
     * @return 是否成功
     */
    boolean update(SysAccountDto sysAccountDto);

    /**
     * 通过主键删除数据
     *
     * @param saId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer saId);

    SysAccountDto findAllByUsername(String accountName);

}
