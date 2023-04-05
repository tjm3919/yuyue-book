package com.trkj.yuyueuser.controller;

import com.trkj.dto.user.SysUserDto;
import com.trkj.service.SysUserService;
import com.trkj.util.AjaxResponse;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 管理员表(SysUserController)表控制层
 *
 * @author makejava
 * @since 2023-02-07 14:38:21
 */
@RestController
@RequestMapping("/sysUser")
@Slf4j
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 分页查询
     *
     * @param sysUserDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody SysUserDto sysUserDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.sysUserService.queryByPage(sysUserDto,currentPage,pageSize));
    }


    /**
     * 分页查询详情
     * @param sysUserDto 筛选条件
     * @param currentPage  当前页码
     * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPageX")
    public AjaxResponse queryByPageX(@RequestBody SysUserDto sysUserDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.sysUserService.queryByPageX(sysUserDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.sysUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysUserDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody SysUserDto sysUserDto) {
        return AjaxResponse.success(this.sysUserService.insert(sysUserDto));
    }

    /**
     * 编辑数据
     *
     * @param sysUserDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody SysUserDto sysUserDto) {
        return AjaxResponse.success(this.sysUserService.update(sysUserDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.sysUserService.deleteById(id));
    }

}

