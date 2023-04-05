package com.trkj.yuyuebook.dao;

import com.trkj.yuyuebook.entity.SubscriptionEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.trkj.dto.book.SubscriptionDto;
import java.util.List;

/**
 * 图书订阅表(Subscription)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-08 15:24:32
 */
 @Mapper
public interface SubscriptionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param subId 主键
     * @return 实例对象
     */
    SubscriptionEntity queryById(Integer subId);

    /**
     * 查询指定行数据
     *
     * @param subscriptionDto 查询条件
     * @return 对象列表
     */
    List<SubscriptionEntity> queryAll(SubscriptionDto subscriptionDto);

    /**
     * 统计总行数
     *
     * @param subscriptionDto 查询条件
     * @return 总行数
     */
    int count(SubscriptionDto subscriptionDto);

    /**
     * 新增数据
     *
     * @param subscriptionDto 实例对象
     * @return 影响行数
     */
    int insert(SubscriptionDto subscriptionDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubscriptionDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubscriptionDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubscriptionDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubscriptionDto> entities);

    /**
     * 修改数据
     *
     * @param subscriptionDto 实例对象
     * @return 影响行数
     */
    int update(SubscriptionDto subscriptionDto);

    /**
     * 通过主键删除数据
     *
     * @param subId 主键
     * @return 影响行数
     */
    int deleteById(Integer subId);

    /**
     * 根据用户id和作品id查询订阅表
     * @param suId 用户id
     * @param woId 作品id
     * @return 是否订阅
     */
    public SubscriptionEntity queryBysuIdwoId(@Param("suId") Integer suId,@Param("woId") Integer woId);
}

