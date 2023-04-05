package com.trkj.yuyuebook.controller;

import com.trkj.dto.book.WorksContentDto;
import com.trkj.service.WorksContentService;
import com.trkj.util.AjaxResponse;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 作品内容表(WorksContentController)表控制层
 *
 * @author makejava
 * @since 2023-02-07 16:34:16
 */
@RestController
@RequestMapping("/worksContent")
@Slf4j
public class WorksContentController {
    /**
     * 服务对象
     */
    @Resource
    private WorksContentService worksContentService;

    /**
     * 分页查询
     *
     * @param worksContentDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody WorksContentDto worksContentDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.worksContentService.queryByPage(worksContentDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.worksContentService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param worksContentDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody WorksContentDto worksContentDto) {
        return AjaxResponse.success(this.worksContentService.insert(worksContentDto));
    }

    /**
     * 编辑数据
     *
     * @param worksContentDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody WorksContentDto worksContentDto) {
        return AjaxResponse.success(this.worksContentService.update(worksContentDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
//        true-删除 false-未删除
        return AjaxResponse.success(this.worksContentService.deleteById(id));
    }

}

