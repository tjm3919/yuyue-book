package com.trkj.yuyuebook.service.impl;

import com.trkj.yuyuebook.entity.WorksContentEntity;
import com.trkj.dto.book.WorksContentDto;
import com.trkj.yuyuebook.dao.WorksContentDao;
import com.trkj.service.WorksContentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trkj.util.BeanTools;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * 作品内容表(WorksContent)表服务实现类
 *
 * @author makejava
 * @since 2023-02-07 16:34:16
 */
@Service("worksContentService")
@DubboService
@Slf4j
public class WorksContentServiceImpl implements WorksContentService {
    @Resource
    private WorksContentDao worksContentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param wcId 主键
     * @return 实例对象
     */
    @Override
    public WorksContentDto queryById(Integer wcId) {
		WorksContentEntity entity=this.worksContentDao.queryById(wcId);
		WorksContentDto dto=new WorksContentDto() ;
		BeanTools.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param worksContentDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<WorksContentDto> queryByPage(WorksContentDto worksContentDto, int currentPage, int pageSize) {
		Page<WorksContentEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<WorksContentEntity> List=this.worksContentDao.queryAll(worksContentDto);
		PageInfo<WorksContentEntity> pageInfo=pageEntity.toPageInfo();
		Page<WorksContentDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,WorksContentDto.class);
        PageInfo<WorksContentDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 新增数据
     *
     * @param worksContentDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public int insert(WorksContentDto worksContentDto) {
        worksContentDto.setWcFinalizeTime(new Date());
        worksContentDto.setWcUpdateEndtime(new Date());
        worksContentDto.setWcCreateTime(new Date());
        // 更新后要通过消息提醒订阅的用户
        return this.worksContentDao.insert(worksContentDto);
    }

    /**
     * 修改数据
     *
     * @param worksContentDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(WorksContentDto worksContentDto) {
        worksContentDto.setWcUpdateEndtime(new Date());
        int count=this.worksContentDao.update(worksContentDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param wcId 主键
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean deleteById(Integer wcId) {
        // 0-删除 1-正常（已发布）2-未发布
        int isupdate = worksContentDao.queryById(wcId).getWcState();
        if(isupdate==2){
            return false;
        }
        return this.worksContentDao.deleteById(wcId) > 0;
    }
}
