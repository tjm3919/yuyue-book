package com.trkj.service;

import com.trkj.dto.book.SubscriptionDto;
import com.github.pagehelper.PageInfo;

/**
 * 图书订阅表(Subscription)表服务接口
 *
 * @author makejava
 * @since 2023-02-08 15:24:32
 */
public interface SubscriptionService {

    /**
     * 通过ID查询单条数据
     *
     * @param subId 主键
     * @return 实例对象
     */
    SubscriptionDto queryById(Integer subId);

    /**
     * 分页查询
     *
     * @param subscriptionDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<SubscriptionDto> queryByPage(SubscriptionDto subscriptionDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param subscriptionDto 实例对象
     * @return 是否成功
     */
    int insert(SubscriptionDto subscriptionDto);

    /**
     * 修改数据
     *
     * @param subscriptionDto 实例对象
     * @return 是否成功
     */
    boolean update(SubscriptionDto subscriptionDto);

    /**
     * 通过主键删除数据
     *
     * @param subId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer subId);

}
