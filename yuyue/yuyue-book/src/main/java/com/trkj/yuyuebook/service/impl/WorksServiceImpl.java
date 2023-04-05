package com.trkj.yuyuebook.service.impl;

import com.trkj.dto.book.*;
import com.trkj.service.WorksIntermediateService;
import com.trkj.util.BeanTools;
import com.trkj.yuyuebook.dao.WorksContentDao;
import com.trkj.yuyuebook.entity.WorksEntity;
import com.trkj.yuyuebook.dao.WorksDao;
import com.trkj.service.WorksService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * 作品表(Works)表服务实现类
 *
 * @author makejava
 * @since 2023-01-28 14:43:18
 */
@Service("worksService")
@DubboService
@Slf4j
public class WorksServiceImpl implements WorksService {
    @Resource
    private WorksDao worksDao;

    @Resource
    private WorksContentDao worksContentDao;

    @Resource
    private WorksIntermediateService worksIntermediateService;
    /**
     * 通过ID查询单条数据
     *
     * @param woId 主键
     * @return 实例对象
     */
    @Override
    public WorksDto queryById(Integer woId) {
		WorksEntity entity=this.worksDao.queryById(woId);
		WorksDto dto=new WorksDto() ;
		BeanTools.copyProperties(entity,dto);
        BookrackDto bookrackDto = new BookrackDto();
        BeanTools.copyProperties(entity.getBookrackEntity(),bookrackDto);
        List<WorksTagDto> worksTagDto = new ArrayList<>();
        BeanTools.copyList(entity.getWorksTagEntity(),worksTagDto,WorksTagDto.class);
        List<WorksContentDto> worksContentDtos = new ArrayList<>();
        BeanTools.copyList(entity.getWorksContentEntities(),worksContentDtos,WorksContentDto.class);
        dto.setBookrackDto(bookrackDto);
        dto.setWorksTagDtos(worksTagDto);
        dto.setWorksContentDto(worksContentDtos);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param worksDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<WorksDto> queryByPage(WorksDto worksDto, int currentPage, int pageSize) {
		Page<WorksEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<WorksEntity> List=this.worksDao.queryAll(worksDto);
		PageInfo<WorksEntity> pageInfo=pageEntity.toPageInfo();
		Page<WorksDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,WorksDto.class);
        // 替换书架、章节和标签对象
        for (WorksEntity we:pageEntity) {
            int i = 0;
            for (WorksDto wd:pageDto) {
                if(we.getWoId()==wd.getWoId()){
                    // 书架
                    BookrackDto bookrackDto = new BookrackDto();
                    BeanTools.copyProperties(we.getBookrackEntity(), bookrackDto);
                    pageDto.get(i).setBookrackDto(bookrackDto);
                    // 标签
                    List<WorksTagDto> worksTagDto = new ArrayList<>();
                    BeanTools.copyList(we.getWorksTagEntity(),worksTagDto,WorksTagDto.class);
                    pageDto.get(i).setWorksTagDtos(worksTagDto);
                    // 章节
                    List<WorksContentDto> worksContentDtos = new ArrayList<>();
                    BeanTools.copyList(we.getWorksContentEntities(),worksContentDtos,WorksContentDto.class);
                    pageDto.get(i).setWorksContentDto(worksContentDtos);
                    i = 0;
                    break;
                }
                i++;
            }
        }
        PageInfo<WorksDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 新增数据
     *
     * @param worksDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean insert(WorksDto worksDto) {
        worksDto.setSectionNum(worksDto.getWorksContentDto().size()); // 章节数量
        worksDto.setWorksCreateTime(new Date());
        // 新增作品 获取作品id
//        log.info("worksDto:{}",worksDto);
        int count=this.worksDao.insert(worksDto);
//        log.info("WoId:{}",worksDto.getWoId());
        // 新增章节
        if(worksDto.getWorksContentDto().size()>0){
            List<WorksContentDto> worksContentDtos = worksDto.getWorksContentDto();
            // 修改章节集合中的作品id
            for (int i = 0; i < worksContentDtos.size(); i++) {
                worksContentDtos.get(i).setWoId(worksDto.getWoId());
                worksContentDtos.get(i).setWcCreateTime(new Date());
                worksContentDtos.get(i).setWcUpdateEndtime(new Date());
                worksContentDtos.get(i).setWcFinalizeTime(new Date());
            }
            this.worksContentDao.insertBatch(worksContentDtos);
        }
        // 新增作品标签主中间表
        if(worksDto.getWorksTagDtos().size()>0){
            List<WorksTagDto> worksTagDtos = worksDto.getWorksTagDtos();
            // 中间表对象
            WorksIntermediateDto wid = new WorksIntermediateDto();
            for (WorksTagDto worksTagDto : worksTagDtos) {
                wid.setWoId(worksDto.getWoId()); // 作品id
                wid.setWtId(worksTagDto.getWtId()); // 标签id
                this.worksIntermediateService.insert(wid);
            }
        }
        return count>0;
    }

    /**
     * 修改数据
     *
     * @param worksDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(WorksDto worksDto) {
        int count=this.worksDao.update(worksDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param woId 主键
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean deleteById(Integer woId) {
        int isok = this.worksDao.deleteById(woId);
        if(isok>0){
            isok = this.worksDao.deleteTagById(woId);
        }else {
            return false;
        }
        return true;
    }

    @Override
    public List<WorksDto> queryByTopten() {
        List<WorksEntity> entity=this.worksDao.queryByTopten();
        log.info("WE1:{}",entity);
        List<WorksDto> dto=new ArrayList<>();
        BeanTools.copyList(entity,dto,WorksDto.class);
        return dto;
    }

    @Override
    public List<WorksDto> queryByType(WorksDto worksDto) {
        List<WorksEntity> entity=this.worksDao.queryByType(worksDto);
        log.info("WE2:{}",entity);
        List<WorksDto> dto=new ArrayList<>();
        BeanTools.copyList(entity,dto,WorksDto.class);
        return dto;
    }
}
