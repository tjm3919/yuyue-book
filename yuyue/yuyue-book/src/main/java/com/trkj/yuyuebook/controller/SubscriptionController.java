package com.trkj.yuyuebook.controller;

import com.trkj.dto.book.SubscriptionDto;
import com.trkj.service.SubscriptionService;
import com.trkj.util.AjaxResponse;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 图书订阅表(SubscriptionController)表控制层
 *
 * @author makejava
 * @since 2023-02-08 15:24:32
 */
@RestController
@RequestMapping("/subscription")
@Slf4j
public class SubscriptionController {
    /**
     * 服务对象
     */
    @Resource
    private SubscriptionService subscriptionService;

    /**
     * 分页查询
     *
     * @param subscriptionDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody SubscriptionDto subscriptionDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.subscriptionService.queryByPage(subscriptionDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.subscriptionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param subscriptionDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody SubscriptionDto subscriptionDto) {
        int isok = this.subscriptionService.insert(subscriptionDto);
        return AjaxResponse.success(isok);
    }

    /**
     * 编辑数据
     *
     * @param subscriptionDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody SubscriptionDto subscriptionDto) {
        return AjaxResponse.success(this.subscriptionService.update(subscriptionDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.subscriptionService.deleteById(id));
    }

}

