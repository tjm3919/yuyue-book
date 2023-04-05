package com.trkj.yuyueapproval.controller;

import com.trkj.dto.approval.ApprovalDto;
import com.trkj.service.ApprovalService;
import com.trkj.util.AjaxResponse;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 审批表(ApprovalController)表控制层
 *
 * @author makejava
 * @since 2023-02-08 09:59:28
 */
@RestController
@RequestMapping("/approval")
@Slf4j
public class ApprovalController {
    /**
     * 服务对象
     */
    @Resource
    private ApprovalService approvalService;

    /**
     * 分页查询
     *
     * @param approvalDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody ApprovalDto approvalDto, int currentPage, int pageSize) {
        return AjaxResponse.success(this.approvalService.queryByPage(approvalDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.approvalService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param approvalDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody ApprovalDto approvalDto) {
        return AjaxResponse.success(this.approvalService.insert(approvalDto));
    }

    /**
     * 编辑数据
     *
     * @param approvalDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody ApprovalDto approvalDto) {
        return AjaxResponse.success(this.approvalService.update(approvalDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.approvalService.deleteById(id));
    }

}

