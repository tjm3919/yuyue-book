package com.trkj.yuyuemessage.controller;

import com.trkj.dto.message.MessageDto;
import com.trkj.service.LoginLogService;
import com.trkj.service.MessageService;
import com.trkj.util.AjaxResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * 系统消息表(MessageController)表控制层
 *
 * @author makejava
 * @since 2023-01-28 19:21:07
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 分页查询
     *
     * @param messageDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody MessageDto messageDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.messageService.queryByPage(messageDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.messageService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param messageDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody MessageDto messageDto) {
        log.info("-------------MqProviderController开始调用远程方法,发送消息------------");
        return AjaxResponse.success(this.messageService.insert(messageDto));
    }

    /**
     * 编辑数据
     *
     * @param messageDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody MessageDto messageDto) {
        return AjaxResponse.success(this.messageService.update(messageDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.messageService.deleteById(id));
    }

}

