package com.trkj.yuyuebook.controller;

import com.trkj.dto.book.WorksDto;
import com.trkj.service.WorksService;
import com.trkj.util.AjaxResponse;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 作品表(WorksController)表控制层
 *
 * @author makejava
 * @since 2023-01-28 14:43:18
 */
@RestController
@RequestMapping("/works")
@Slf4j
public class WorksController {
    /**
     * 服务对象
     */
    @Resource
    private WorksService worksService;

    /**
     * 查询订阅前十的图书
     * @return
     */
    @GetMapping("/queryByTopten")
    public AjaxResponse queryByTopten() {
        return AjaxResponse.success(this.worksService.queryByTopten());
    }

    /**
     * 按标题、主角名、配角名、主角与配角名混合 模糊查询
     * @param worksDto
     * @return 图书
     */
    @GetMapping("/queryByType")
    public AjaxResponse queryByType(@RequestBody WorksDto worksDto) {
        return AjaxResponse.success(this.worksService.queryByType(worksDto));
    }

    /**
     * 分页查询
     *
     * @param worksDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody WorksDto worksDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.worksService.queryByPage(worksDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.worksService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param worksDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody WorksDto worksDto) {
        return AjaxResponse.success(this.worksService.insert(worksDto));
    }

    /**
     * 编辑数据
     *
     * @param worksDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody WorksDto worksDto) {
        return AjaxResponse.success(this.worksService.update(worksDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.worksService.deleteById(id));
    }

}

