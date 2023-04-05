package com.trkj.service;

import com.trkj.dto.book.WorksDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 作品表(Works)表服务接口
 *
 * @author makejava
 * @since 2023-01-28 14:43:17
 */
public interface WorksService {

    /**
     * 通过ID查询单条数据
     *
     * @param woId 主键
     * @return 实例对象
     */
    WorksDto queryById(Integer woId);

    /**
     * 分页查询
     *
     * @param worksDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<WorksDto> queryByPage(WorksDto worksDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param worksDto 实例对象
     * @return 是否成功
     */
    boolean insert(WorksDto worksDto);

    /**
     * 修改数据
     *
     * @param worksDto 实例对象
     * @return 是否成功
     */
    boolean update(WorksDto worksDto);

    /**
     * 通过主键删除数据
     *
     * @param woId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer woId);

    List<WorksDto> queryByTopten();

    List<WorksDto> queryByType(WorksDto worksDto);
}
