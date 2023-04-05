package com.trkj.service;

import com.trkj.dto.book.WorksContentDto;
import com.github.pagehelper.PageInfo;

/**
 * 作品内容表(WorksContent)表服务接口
 *
 * @author makejava
 * @since 2023-02-07 16:34:16
 */
public interface WorksContentService {

    /**
     * 通过ID查询单条数据
     *
     * @param wcId 主键
     * @return 实例对象
     */
    WorksContentDto queryById(Integer wcId);

    /**
     * 分页查询
     *
     * @param worksContentDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    PageInfo<WorksContentDto> queryByPage(WorksContentDto worksContentDto, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param worksContentDto 实例对象
     * @return 是否成功
     */
    int insert(WorksContentDto worksContentDto);

    /**
     * 修改数据
     *
     * @param worksContentDto 实例对象
     * @return 是否成功
     */
    boolean update(WorksContentDto worksContentDto);

    /**
     * 通过主键删除数据
     *
     * @param wcId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer wcId);

}
