package com.trkj.service;

import com.trkj.dto.integral.IntegralDto;
import com.github.pagehelper.PageInfo;

/**
 * 积分详情表(Integral)表服务接口
 *
 * @author makejava
 * @since 2023-02-07 14:30:06
 */
public interface IntegralService {

    /**
     * 通过ID查询单条数据
     *
     * @param inId 主键
     * @return 实例对象
     */
    IntegralDto queryById(Integer inId);

    /**
     * 分页查询
     *
     * @param integralDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<IntegralDto> queryByPage(IntegralDto integralDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param integralDto 实例对象
     * @return 是否成功
     */
    int insert(IntegralDto integralDto);

    /**
     * 修改数据
     *
     * @param integralDto 实例对象
     * @return 是否成功
     */
    boolean update(IntegralDto integralDto);

    /**
     * 通过主键删除数据
     *
     * @param inId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer inId);

}
