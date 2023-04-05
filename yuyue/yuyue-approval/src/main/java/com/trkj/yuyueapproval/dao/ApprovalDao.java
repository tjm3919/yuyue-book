package com.trkj.yuyueapproval.dao;

import com.trkj.dto.approval.ApprovalDto;
import com.trkj.yuyueapproval.entity.ApprovalEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 审批表(Approval)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-08 09:59:26
 */
 @Mapper
public interface ApprovalDao {

    /**
     * 通过ID查询单条数据
     *
     * @param apId 主键
     * @return 实例对象
     */
    ApprovalEntity queryById(Integer apId);

    /**
     * 查询指定行数据
     *
     * @param approvalDto 查询条件
     * @return 对象列表
     */
    List<ApprovalEntity> queryAll(ApprovalDto approvalDto);

    /**
     * 统计总行数
     *
     * @param approvalDto 查询条件
     * @return 总行数
     */
    int count(ApprovalDto approvalDto);

    /**
     * 新增数据
     *
     * @param approvalDto 实例对象
     * @return 影响行数
     */
    int insert(ApprovalDto approvalDto);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ApprovalDto> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ApprovalDto> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ApprovalDto> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ApprovalDto> entities);

    /**
     * 修改数据
     *
     * @param approvalDto 实例对象
     * @return 影响行数
     */
    int update(ApprovalDto approvalDto);

    /**
     * 通过主键删除数据
     *
     * @param apId 主键
     * @return 影响行数
     */
    int deleteById(Integer apId);

}

