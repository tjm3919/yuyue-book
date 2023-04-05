package com.trkj.yuyuejwt.service.impl;

import com.trkj.dto.account.SysAccountDto;
import com.trkj.dto.approval.ApprovalDto;
import com.trkj.dto.integral.IntegralDto;
import com.trkj.dto.user.SysUserDto;
import com.trkj.service.ApprovalService;
import com.trkj.service.IntegralService;
import com.trkj.service.SysAccountService;
import com.trkj.service.SysUserService;
import com.trkj.util.BeanTools;
import com.trkj.yuyuejwt.dao.SysAccountDao;
import com.trkj.yuyuejwt.entity.SysAccountEntity;
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
 * (SysAccount)表服务实现类
 *
 * @author makejava
 * @since 2023-01-25 10:33:12
 */
@Service("sysAccountService")
@DubboService
@Slf4j
public class SysAccountServiceImpl implements SysAccountService {
    @Resource
    private SysAccountDao sysAccountDao;

    @DubboReference
    private SysUserService sysUserService;

    @DubboReference
    private IntegralService integralService;

    @DubboReference
    private ApprovalService approvalService;

    /**
     * 通过ID查询单条数据
     *
     * @param saId 主键
     * @return 实例对象
     */
    @Override
    public SysAccountDto queryById(Integer saId) {
		SysAccountEntity entity=this.sysAccountDao.queryById(saId);
		SysAccountDto dto=new SysAccountDto() ;
		BeanTools.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param sysAccountDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<SysAccountDto> queryByPage(SysAccountDto sysAccountDto, int currentPage, int pageSize) {
		Page<SysAccountEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<SysAccountEntity> List=this.sysAccountDao.queryAll(sysAccountDto);
		PageInfo<SysAccountEntity> pageInfo=pageEntity.toPageInfo();
		Page<SysAccountDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,SysAccountDto.class);
        PageInfo<SysAccountDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 管理员、作者注册
     * @param sysAccountDto 实例对象
     * @return
     */
    @Override
    @Transactional
    public boolean insertSA(SysAccountDto sysAccountDto) {
//        log.info("SysAccountDto:{}",sysAccountDto);
        sysAccountDto.setSaCreateTime(new Date());
        // 新增账号并获取返回的Said
        int count = this.sysAccountDao.insert(sysAccountDto);
        // 修改新增的用户对象中的Said
        sysAccountDto.getSysUserDto().setSaId(sysAccountDto.getSaId());
        // 新增用户 （修改账号的用户id）
        sysAccountDto.getSysUserDto().setSuCreateTime(new Date());
        int userid = this.sysUserService.insert(sysAccountDto.getSysUserDto());
        // 修改账号中的用户id
        sysAccountDto.setSuId(userid);
        // 修改账号中的用户id
        int isok = this.sysAccountDao.update(sysAccountDto);
        if(isok>0){
            // 生成作者注册审批
            ApprovalDto approvalDto = new ApprovalDto();
            approvalDto.setSysId(sysAccountDto.getApprovalDto().getSysId()); // 审批人id
            approvalDto.setSaId(sysAccountDto.getSaId()); // 被审批人账号id
            approvalDto.setSuId(userid); // 被审批人用户id
            approvalDto.setApEvent(sysAccountDto.getApprovalDto().getApEvent()); // 审批事件
            approvalDto.setApState(1); // 审批状态 0-删除 1-未审批 2-通过 3-拒绝
            approvalDto.setApType(sysAccountDto.getApprovalDto().getApType()); // 审批类型
            isok = approvalService.insert(approvalDto);
        }
        return isok > 0;
    }

    /**
     * 新增数据
     *
     * @param sysAccountDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean insert(SysAccountDto sysAccountDto) {
//        log.info("SysAccountDto:{}",sysAccountDto);
        //sysAccountDto.setSaType(pdSA(sysAccountDto.getSysUserDto()));
        sysAccountDto.setSaCreateTime(new Date());
        // 新增账号并获取返回的Said
        int count = this.sysAccountDao.insert(sysAccountDto);
        // 修改新增的用户对象中的Said
        sysAccountDto.getSysUserDto().setSaId(sysAccountDto.getSaId());
        // 新增用户 （修改账号的用户id）
        sysAccountDto.getSysUserDto().setSuCreateTime(new Date());
        int userid = this.sysUserService.insert(sysAccountDto.getSysUserDto());
        // 修改账号中的用户id
        sysAccountDto.setSuId(userid);
        //        SysAccountDto sadto = new SysAccountDto();
        //        sadto.setSaId(sysAccountDto.getSaId());
        //        sadto.setSuId(userid);
        int isok = this.sysAccountDao.update(sysAccountDto);
        // 新增用户积分记录（增减积分类型 用户注册（+10） 作者注册（+100） 阅读图书（-？）
        //                  阅读两分钟图书（+2） 管理员赠送（+？））
        IntegralDto integralDto = new IntegralDto();
        integralDto.setAmType(sysAccountDto.getSysUserDto().getSuType() + "注册");
        integralDto.setAmNum(sysAccountDto.getSysUserDto().getSumNum());
        integralDto.setSuId(userid);
        integralDto.setInCreateTime(new Date());
        isok = this.integralService.insert(integralDto);
        return isok>0;
    }

    private int pdSA(SysUserDto sysUserDto){
        // saType(账号类型 1-管理员账号 2-用户账号 3-作者账号)
        // suType(用户类型 超级管理员 普通管理员 作者 读者)
        int pd = 2;
        if(sysUserDto.getSuType().equals("作者")){
            pd=3;
        }else if(sysUserDto.getSuType().equals("超级管理员") || sysUserDto.getSuType().equals("普通管理员")){
            pd=1;
        }
        return pd;
    }

    /**
     * 修改数据
     *
     * @param sysAccountDto 实例对象
     * @return 是否成功sysUserDto.getSuType().equals("作者")
     */
    @Override
	@Transactional
    public boolean update(SysAccountDto sysAccountDto) {
        int count=this.sysAccountDao.update(sysAccountDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param saId 主键
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean deleteById(Integer saId) {
        return this.sysAccountDao.deleteById(saId) > 0;
    }

    @Override
    public SysAccountDto findAllByUsername(String accountName) {
        SysAccountDto accountDto=new SysAccountDto();
        SysAccountEntity allByUsername = sysAccountDao.findAllByUsername(accountName);
        BeanTools.copyProperties(allByUsername,accountDto);
        return accountDto;
    }
}
