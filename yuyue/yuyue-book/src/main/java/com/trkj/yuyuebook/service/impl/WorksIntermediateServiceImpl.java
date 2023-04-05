package com.trkj.yuyuebook.service.impl;

import com.trkj.yuyuebook.entity.WorksIntermediateEntity;
import com.trkj.dto.book.WorksIntermediateDto;
import com.trkj.yuyuebook.dao.WorksIntermediateDao;
import com.trkj.service.WorksIntermediateService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trkj.util.BeanTools;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * 作品标签中间表(WorksIntermediate)表服务实现类
 *
 * @author makejava
 * @since 2023-02-07 17:02:13
 */
@Service("worksIntermediateService")
@DubboService
@Slf4j
public class WorksIntermediateServiceImpl implements WorksIntermediateService {
    @Resource
    private WorksIntermediateDao worksIntermediateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param wiId 主键
     * @return 实例对象
     */
    @Override
    public WorksIntermediateDto queryById(Integer wiId) {
		WorksIntermediateEntity entity=this.worksIntermediateDao.queryById(wiId);
		WorksIntermediateDto dto=new WorksIntermediateDto() ;
		BeanTools.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param worksIntermediateDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<WorksIntermediateDto> queryByPage(WorksIntermediateDto worksIntermediateDto, int currentPage, int pageSize) {
		Page<WorksIntermediateEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<WorksIntermediateEntity> List=this.worksIntermediateDao.queryAll(worksIntermediateDto);
		PageInfo<WorksIntermediateEntity> pageInfo=pageEntity.toPageInfo();
		Page<WorksIntermediateDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,WorksIntermediateDto.class);
        PageInfo<WorksIntermediateDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 新增数据
     *
     * @param worksIntermediateDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public int insert(WorksIntermediateDto worksIntermediateDto) {
        return this.worksIntermediateDao.insert(worksIntermediateDto);
    }

    /**
     * 修改数据
     *
     * @param worksIntermediateDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(WorksIntermediateDto worksIntermediateDto) {
        int count=this.worksIntermediateDao.update(worksIntermediateDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param wiId 主键
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean deleteById(Integer wiId) {
        return this.worksIntermediateDao.deleteById(wiId) > 0;
    }
}
