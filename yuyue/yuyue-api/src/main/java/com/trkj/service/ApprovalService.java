package com.trkj.service;

import com.github.pagehelper.PageInfo;
import com.trkj.dto.approval.ApprovalDto;

/**
 * 审批表(Approval)表服务接口
 *
 * @author makejava
 * @since 2023-02-08 09:59:27
 */
public interface ApprovalService {

    /**
     * 通过ID查询单条数据
     *
     * @param apId 主键
     * @return 实例对象
     */
    ApprovalDto queryById(Integer apId);

    /**
     * 分页查询
     *
     * @param approvalDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<ApprovalDto> queryByPage(ApprovalDto approvalDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param approvalDto 实例对象
     * @return 是否成功
     */
    int insert(ApprovalDto approvalDto);

    /**
     * 修改数据
     *
     * @param approvalDto 实例对象
     * @return 是否成功
     */
    boolean update(ApprovalDto approvalDto);

    /**
     * 通过主键删除数据
     *
     * @param apId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer apId);

}
