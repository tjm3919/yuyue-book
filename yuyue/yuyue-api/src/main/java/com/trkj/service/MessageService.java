package com.trkj.service;

import com.github.pagehelper.PageInfo;
import com.trkj.dto.message.MessageDto;

/**
 * 系统消息表(Message)表服务接口
 *
 * @author makejava
 * @since 2023-01-28 19:21:07
 */
public interface MessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param meId 主键
     * @return 实例对象
     */
    MessageDto queryById(Integer meId);

    /**
     * 分页查询
     *
     * @param messageDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<MessageDto> queryByPage(MessageDto messageDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param messageDto 实例对象
     * @return 是否成功
     */
    boolean insert(MessageDto messageDto);

    /**
     * 修改数据
     *
     * @param messageDto 实例对象
     * @return 是否成功
     */
    boolean update(MessageDto messageDto);

    /**
     * 通过主键删除数据
     *
     * @param meId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer meId);

}
