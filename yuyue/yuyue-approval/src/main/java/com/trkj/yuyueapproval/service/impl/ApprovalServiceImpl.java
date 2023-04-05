package com.trkj.yuyueapproval.service.impl;

import com.trkj.dto.approval.ApprovalDto;
import com.trkj.dto.integral.IntegralDto;
import com.trkj.dto.message.MessageDto;
import com.trkj.service.*;
import com.trkj.yuyueapproval.entity.ApprovalEntity;
import com.trkj.yuyueapproval.dao.ApprovalDao;
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

/**
 * 审批表(Approval)表服务实现类
 *
 * @author makejava
 * @since 2023-02-08 09:59:27
 */
@Service("approvalService")
@DubboService
@Slf4j
public class ApprovalServiceImpl implements ApprovalService {
    @Resource
    private ApprovalDao approvalDao;

    @DubboReference
    private IntegralService integralService;

    @DubboReference
    private SysAccountService sysAccountService;

    @DubboReference
    private SysUserService sysUserService;

    @DubboReference
    private MessageService messageService;
    /**
     * 通过ID查询单条数据
     *
     * @param apId 主键
     * @return 实例对象
     */
    @Override
    public ApprovalDto queryById(Integer apId) {
		ApprovalEntity entity=this.approvalDao.queryById(apId);
		ApprovalDto dto=new ApprovalDto() ;
		BeanTools.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param approvalDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<ApprovalDto> queryByPage(ApprovalDto approvalDto, int currentPage, int pageSize) {
		Page<ApprovalEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<ApprovalEntity> List=this.approvalDao.queryAll(approvalDto);
		PageInfo<ApprovalEntity> pageInfo=pageEntity.toPageInfo();
		Page<ApprovalDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,ApprovalDto.class);
        PageInfo<ApprovalDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 新增数据
     *
     * @param approvalDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public int insert(ApprovalDto approvalDto) {
        return this.approvalDao.insert(approvalDto);
    }

    /**
     * 修改数据
     * @param approvalDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(ApprovalDto approvalDto) {
        int count=this.approvalDao.update(approvalDto);
        // 查询审批数据
        ApprovalEntity appr = this.approvalDao.queryById(approvalDto.getApId());
        log.info("ApprovalDto:{}",appr);
        IntegralDto integralDto = new IntegralDto();
        if(appr.getApState()==2){ // 通过
            if(appr.getApEvent().equals("作者注册")){
                // 新增用户积分记录（增减积分类型 用户注册（+10） 作者注册（+100） 阅读图书（-？）
                //                  阅读两分钟图书（+2） 管理员赠送（+？））
                integralDto.setAmType("作者注册");
                integralDto.setAmNum(100);
                integralDto.setSuId(appr.getSuId());
                integralDto.setInCreateTime(new Date());
                count = this.integralService.insert(integralDto);
            }else if(appr.getApEvent().equals("管理员注册")){
                integralDto.setAmType("管理员注册");
                integralDto.setAmNum(1000);
                integralDto.setSuId(appr.getSuId());
                integralDto.setInCreateTime(new Date());
                count = this.integralService.insert(integralDto);
            }
            MessageDto messageDto = new MessageDto();
            messageDto.setSysId(approvalDto.getSysId());
            messageDto.setMeContent("账号申请成功");
            messageDto.setSendTime(new Date());
            messageDto.setMeState(1);
            messageService.insert(messageDto);
        }else if(appr.getApState()==3){ // 拒绝
            // 删除账号和用户
            if(appr.getApEvent().equals("作者注册") || appr.getApEvent().equals("管理员注册")){
                this.sysAccountService.deleteById(appr.getSaId());
                this.sysUserService.deleteById(appr.getSuId());
            }
        }
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param apId 主键
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean deleteById(Integer apId) {
        return this.approvalDao.deleteById(apId) > 0;
    }
}
