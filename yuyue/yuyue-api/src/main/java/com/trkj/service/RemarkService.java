package com.trkj.service;

import com.github.pagehelper.PageInfo;
import com.trkj.dto.remark.RemarkDto;

/**
 * 作品评论表(Remark)表服务接口
 *
 * @author makejava
 * @since 2023-02-05 16:57:13
 */
public interface RemarkService {

    /**
     * 通过ID查询单条数据
     *
     * @param reId 主键
     * @return 实例对象
     */
    RemarkDto queryById(Integer reId);

    /**
     * 分页查询
     *
     * @param remarkDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<RemarkDto> queryByPage(RemarkDto remarkDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param remarkDto 实例对象
     * @return 是否成功
     */
    boolean insert(RemarkDto remarkDto);

    /**
     * 修改数据
     *
     * @param remarkDto 实例对象
     * @return 是否成功
     */
    boolean update(RemarkDto remarkDto);

    /**
     * 通过主键删除数据
     *
     * @param reId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer reId);

}
