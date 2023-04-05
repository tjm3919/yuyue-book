package com.trkj.yuyueremark.service.impl;

import com.trkj.dto.book.WorksDto;
import com.trkj.service.RemarkService;
import com.trkj.service.WorksService;
import com.trkj.util.BeanTools;
import com.trkj.yuyueremark.entity.RemarkEntity;
import com.trkj.dto.remark.RemarkDto;
import com.trkj.yuyueremark.dao.RemarkDao;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * 作品评论表(Remark)表服务实现类
 *
 * @author makejava
 * @since 2023-02-05 16:57:13
 */
@Service("remarkService")
@DubboService
@Slf4j
public class RemarkServiceImpl implements RemarkService {
    @Resource
    private RemarkDao remarkDao;

    @DubboReference
    private WorksService worksService;

    /**
     * 通过ID查询单条数据
     *
     * @param reId 主键
     * @return 实例对象
     */
    @Override
    public RemarkDto queryById(Integer reId) {
		RemarkEntity entity=this.remarkDao.queryById(reId);
		RemarkDto dto=new RemarkDto() ;
		BeanTools.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param remarkDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<RemarkDto> queryByPage(RemarkDto remarkDto, int currentPage, int pageSize) {
		Page<RemarkEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<RemarkEntity> List=this.remarkDao.queryAll(remarkDto);
		PageInfo<RemarkEntity> pageInfo=pageEntity.toPageInfo();
		Page<RemarkDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,RemarkDto.class);
        PageInfo<RemarkDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 新增数据
     *
     * @param remarkDto 实例对象
     * @return 是否成功
     */
    @Override
//	@Transactional
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    public boolean insert(RemarkDto remarkDto) {
        remarkDto.setReTime(new Date());
        // 新增评论
        int count=this.remarkDao.insert(remarkDto);
        // 查询图书信息
        WorksDto worksDto = this.worksService.queryById(remarkDto.getWoId());
        // 修改图书评论数
        WorksDto updateworksDto = new WorksDto();
        updateworksDto.setWoId(remarkDto.getWoId());
        updateworksDto.setAppraiseNum(worksDto.getAppraiseNum()+1);
        return worksService.update(updateworksDto);
    }

    /**
     * 修改数据
     *
     * @param remarkDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(RemarkDto remarkDto) {
        int count=this.remarkDao.update(remarkDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param reId 主键
     * @return 是否成功
     */
    @Override
//	@Transactional
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    public boolean deleteById(Integer reId) {
        // 查询图书信息获取图书id
        RemarkEntity remark = this.remarkDao.queryById(reId);
        WorksDto worksDto = this.worksService.queryById(remark.getWoId());
        // 修改图书评论数量
        if(worksDto.getAppraiseNum()>0){
            WorksDto updateworksDto = new WorksDto();
            updateworksDto.setWoId(worksDto.getWoId());
            updateworksDto.setAppraiseNum(worksDto.getAppraiseNum()-1);
            this.worksService.update(updateworksDto);
        }
        // 删除评论
        return this.remarkDao.deleteById(reId)>0;
    }
}
