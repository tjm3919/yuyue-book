package com.trkj.yuyuebook.controller;

import com.trkj.dto.book.WorksIntermediateDto;
import com.trkj.service.WorksIntermediateService;
import com.trkj.util.AjaxResponse;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 作品标签中间表(WorksIntermediateController)表控制层
 *
 * @author makejava
 * @since 2023-02-07 17:02:13
 */
@RestController
@RequestMapping("/worksIntermediate")
@Slf4j
public class WorksIntermediateController {
    /**
     * 服务对象
     */
    @Resource
    private WorksIntermediateService worksIntermediateService;

    /**
     * 分页查询
     *
     * @param worksIntermediateDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody WorksIntermediateDto worksIntermediateDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.worksIntermediateService.queryByPage(worksIntermediateDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.worksIntermediateService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param worksIntermediateDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody WorksIntermediateDto worksIntermediateDto) {
        return AjaxResponse.success(this.worksIntermediateService.insert(worksIntermediateDto));
    }

    /**
     * 编辑数据
     *
     * @param worksIntermediateDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody WorksIntermediateDto worksIntermediateDto) {
        return AjaxResponse.success(this.worksIntermediateService.update(worksIntermediateDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.worksIntermediateService.deleteById(id));
    }

}

