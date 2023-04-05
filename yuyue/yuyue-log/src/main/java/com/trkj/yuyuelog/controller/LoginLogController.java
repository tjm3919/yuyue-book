package com.trkj.yuyuelog.controller;

import com.trkj.dto.log.LoginLogDto;
import com.trkj.service.LoginLogService;
import com.trkj.util.AjaxResponse;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 登录日志表(LoginLogController)表控制层
 *
 * @author makejava
 * @since 2023-01-24 11:11:37
 */
@RestController
@RequestMapping("/loginLog")
@Slf4j
public class LoginLogController {
    /**
     * 服务对象
     */
    @Resource
    private LoginLogService loginLogService;

    /**
     * 分页查询
     *
     * @param loginLogDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody LoginLogDto loginLogDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.loginLogService.queryByPage(loginLogDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.loginLogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param loginLogDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody LoginLogDto loginLogDto) {
        return AjaxResponse.success(this.loginLogService.insert(loginLogDto));
    }

    /**
     * 编辑数据
     *
     * @param loginLogDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody LoginLogDto loginLogDto) {
        return AjaxResponse.success(this.loginLogService.update(loginLogDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.loginLogService.deleteById(id));
    }

}

