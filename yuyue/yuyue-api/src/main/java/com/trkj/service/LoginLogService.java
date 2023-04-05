package com.trkj.service;

import com.trkj.dto.log.LoginLogDto;
import com.github.pagehelper.PageInfo;

/**
 * 登录日志表(LoginLog)表服务接口
 *
 * @author makejava
 * @since 2023-01-24 11:11:36
 */
public interface LoginLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param llId 主键
     * @return 实例对象
     */
    LoginLogDto queryById(Integer llId);

    /**
     * 分页查询
     *
     * @param loginLogDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<LoginLogDto> queryByPage(LoginLogDto loginLogDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param loginLogDto 实例对象
     * @return 是否成功
     */
    boolean insert(LoginLogDto loginLogDto);

    /**
     * 修改数据
     *
     * @param loginLogDto 实例对象
     * @return 是否成功
     */
    boolean update(LoginLogDto loginLogDto);

    /**
     * 通过主键删除数据
     *
     * @param llId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer llId);

    /**
     * 查询最后一条数据
     * @return
     */
    LoginLogDto queryEnd(Integer said);

}
