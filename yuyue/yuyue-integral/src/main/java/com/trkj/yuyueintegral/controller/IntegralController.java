package com.trkj.yuyueintegral.controller;

import com.trkj.dto.integral.IntegralDto;
import com.trkj.dto.user.SysUserDto;
import com.trkj.service.IntegralService;
import com.trkj.service.SysUserService;
import com.trkj.util.AjaxResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 积分详情表(IntegralController)表控制层
 *
 * @author makejava
 * @since 2023-02-07 14:30:06
 */
@RestController
@RequestMapping("/integral")
@Slf4j
public class IntegralController {
    /**
     * 服务对象
     */
    @Resource
    private IntegralService integralService;

    @DubboReference
    private SysUserService sysUserService;

    /**
     * 分页查询
     *
     * @param integralDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody IntegralDto integralDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.integralService.queryByPage(integralDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.integralService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param integralDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody IntegralDto integralDto) {
        // 判断是否是管理员赠送积分
        if(integralDto.getAmType().equals("管理员赠送")){
            SysUserDto sysUserDto =sysUserService.queryById(integralDto.getSuId());
            SysUserDto updatesysUserDto = new SysUserDto();
            sysUserDto.setSuId(integralDto.getSuId());
            sysUserDto.setSumNum(sysUserDto.getSumNum()+integralDto.getAmNum());
            sysUserService.update(sysUserDto);
        }else if(integralDto.getAmType().equals("阅读图书")){ // 阅读图书赠送积分
            SysUserDto sysUserDto =sysUserService.queryById(integralDto.getSuId());
            SysUserDto updatesysUserDto = new SysUserDto();
            sysUserDto.setSuId(integralDto.getSuId());
            sysUserDto.setSumNum(sysUserDto.getSumNum()+integralDto.getAmNum());
            sysUserService.update(sysUserDto);
        }
        return AjaxResponse.success(this.integralService.insert(integralDto));
    }

    /**
     * 编辑数据
     *
     * @param integralDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody IntegralDto integralDto) {
        return AjaxResponse.success(this.integralService.update(integralDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.integralService.deleteById(id));
    }

}

