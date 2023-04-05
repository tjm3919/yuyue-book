package com.trkj.yuyueremark.controller;

import com.trkj.dto.remark.RemarkDto;
import com.trkj.service.RemarkService;
import com.trkj.util.AjaxResponse;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 作品评论表(RemarkController)表控制层
 *
 * @author makejava
 * @since 2023-02-05 16:57:14
 */
@RestController
@RequestMapping("/remark")
@Slf4j
public class RemarkController {
    /**
     * 服务对象
     */
    @Resource
    private RemarkService remarkService;

    /**
     * 分页查询
     *
     * @param remarkDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody RemarkDto remarkDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.remarkService.queryByPage(remarkDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.remarkService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param remarkDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody RemarkDto remarkDto) {
        return AjaxResponse.success(this.remarkService.insert(remarkDto));
    }

    /**
     * 编辑数据
     *
     * @param remarkDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody RemarkDto remarkDto) {
        return AjaxResponse.success(this.remarkService.update(remarkDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.remarkService.deleteById(id));
    }

}

