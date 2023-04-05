package com.trkj.service;

import com.trkj.dto.book.WorksIntermediateDto;
import com.github.pagehelper.PageInfo;

/**
 * 作品标签中间表(WorksIntermediate)表服务接口
 *
 * @author makejava
 * @since 2023-02-07 17:02:13
 */
public interface WorksIntermediateService {

    /**
     * 通过ID查询单条数据
     *
     * @param wiId 主键
     * @return 实例对象
     */
    WorksIntermediateDto queryById(Integer wiId);

    /**
     * 分页查询
     *
     * @param worksIntermediateDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<WorksIntermediateDto> queryByPage(WorksIntermediateDto worksIntermediateDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param worksIntermediateDto 实例对象
     * @return 是否成功
     */
    int insert(WorksIntermediateDto worksIntermediateDto);

    /**
     * 修改数据
     *
     * @param worksIntermediateDto 实例对象
     * @return 是否成功
     */
    boolean update(WorksIntermediateDto worksIntermediateDto);

    /**
     * 通过主键删除数据
     *
     * @param wiId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer wiId);

}
