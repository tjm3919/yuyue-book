package com.trkj.yuyuebook.service.impl;

import com.trkj.dto.book.WorksDto;
import com.trkj.dto.integral.IntegralDto;
import com.trkj.dto.user.SysUserDto;
import com.trkj.service.IntegralService;
import com.trkj.service.SysUserService;
import com.trkj.service.WorksService;
import com.trkj.yuyuebook.entity.SubscriptionEntity;
import com.trkj.dto.book.SubscriptionDto;
import com.trkj.yuyuebook.dao.SubscriptionDao;
import com.trkj.service.SubscriptionService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
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
import org.springframework.util.StringUtils;

/**
 * 图书订阅表(Subscription)表服务实现类
 *
 * @author makejava
 * @since 2023-02-08 15:24:32
 */
@Service("subscriptionService")
@DubboService
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    @Resource
    private SubscriptionDao subscriptionDao;

    @Resource
    private WorksService worksService;

    @DubboReference
    private SysUserService sysUserService;

    @DubboReference
    private IntegralService integralService;


    /**
     * 通过ID查询单条数据
     *
     * @param subId 主键
     * @return 实例对象
     */
    @Override
    public SubscriptionDto queryById(Integer subId) {
		SubscriptionEntity entity=this.subscriptionDao.queryById(subId);
		SubscriptionDto dto=new SubscriptionDto() ;
		BeanTools.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param subscriptionDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<SubscriptionDto> queryByPage(SubscriptionDto subscriptionDto, int currentPage, int pageSize) {
		Page<SubscriptionEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<SubscriptionEntity> List=this.subscriptionDao.queryAll(subscriptionDto);
		PageInfo<SubscriptionEntity> pageInfo=pageEntity.toPageInfo();
		Page<SubscriptionDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,SubscriptionDto.class);
        PageInfo<SubscriptionDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 新增数据
     * 图书订阅
     * @param subscriptionDto 实例对象
     * @return 是否成功
     */
    @Override
//	@Transactional
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    public int insert(SubscriptionDto subscriptionDto) {
        // 查询是否订阅过
        SubscriptionEntity subscriptionEntity = subscriptionDao.queryBysuIdwoId(
                subscriptionDto.getSuId(),subscriptionDto.getWoId());
        // 查询用户和图书信息
        SysUserDto sysUserDto = sysUserService.queryById(subscriptionDto.getSuId());
        WorksDto worksDto = worksService.queryById(subscriptionDto.getWoId());
        if(subscriptionEntity!=null){ // 订阅过改状态
            SubscriptionDto dto=new SubscriptionDto();
            BeanTools.copyProperties(subscriptionEntity,dto);
            dto.setSubTime(new Date());
            dto.setSubState(1);
            subscriptionDao.update(dto);
            // 修改图书订阅数量
            WorksDto wod = new WorksDto();
            wod.setWoId(subscriptionDto.getWoId());
            wod.setReadNum(worksDto.getReadNum()+1);
            this.worksService.update(wod);
            return 1;
        }
        // 判断用户积分是否充足
        boolean isok=false;
        if(sysUserDto.getSumNum() >= worksDto.getBoIntegral()){
            // 订阅减少用户积分 新增积分详情表
            SysUserDto sud = new SysUserDto();
            sud.setSuId(subscriptionDto.getSuId());
            sud.setSumNum(sysUserDto.getSumNum()-worksDto.getBoIntegral());
            isok = this.sysUserService.update(sud);
            IntegralDto integralDto = new IntegralDto();
            integralDto.setSuId(subscriptionDto.getSuId());
            integralDto.setAmType("订阅图书");
            integralDto.setAmNum(worksDto.getBoIntegral());
            integralDto.setWoId(subscriptionDto.getWoId());
            integralDto.setInCreateTime(new Date());
            isok = this.integralService.insert(integralDto)>0;
            // 新增订阅
            subscriptionDto.setSubTime(new Date());
            isok = this.subscriptionDao.insert(subscriptionDto)>0;
            // 修改图书订阅数量
            WorksDto wod = new WorksDto();
            wod.setWoId(subscriptionDto.getWoId());
            wod.setReadNum(worksDto.getReadNum()+1);
            isok = this.worksService.update(wod);
        }else {
            // -1 代表积分不足
            return -1;
        }
        return isok?1:0;
    }

    /**
     * 修改数据
     *
     * @param subscriptionDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(SubscriptionDto subscriptionDto) {
        int count=this.subscriptionDao.update(subscriptionDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param subId 主键
     * @return 是否成功
     */
    @Override
//	@Transactional
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    public boolean deleteById(Integer subId) {
        // 查询订阅信息
        SubscriptionEntity subd = this.subscriptionDao.queryById(subId);
        WorksDto worksDto = this.worksService.queryById(subd.getWoId());
        boolean isok=false;
        if(worksDto.getReadNum()>0){
            // 修改图书订阅量
            worksDto.setReadNum(worksDto.getReadNum()-1);
            isok = worksService.update(worksDto);
        }
        // 删除订阅信息（修改状态）0-取消订阅 1-已订阅
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setSubId(subId);
        subscriptionDto.setSubState(0);
        return subscriptionDao.update(subscriptionDto) > 0;
    }
}
