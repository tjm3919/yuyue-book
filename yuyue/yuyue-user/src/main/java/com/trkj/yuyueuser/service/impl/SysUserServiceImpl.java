package com.trkj.yuyueuser.service.impl;

import com.trkj.util.BeanTools;
import com.trkj.yuyueuser.entity.SysUserEntity;
import com.trkj.dto.user.SysUserDto;
import com.trkj.yuyueuser.dao.SysUserDao;
import com.trkj.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;


/**
 * 管理员表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2023-02-07 14:38:21
 */
@Service("sysUserService")
@DubboService
@Slf4j
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param suId 主键
     * @return 实例对象
     */
    @Override
    public SysUserDto queryById(Integer suId) {
		SysUserEntity entity=this.sysUserDao.queryById(suId);
		SysUserDto dto=new SysUserDto() ;
        if(entity!=null){
            BeanTools.copyProperties(entity,dto);
        }
        return dto;
    }

    /**
     * 分页查询
     *
     * @param sysUserDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<SysUserDto> queryByPage(SysUserDto sysUserDto, int currentPage, int pageSize) {
        Page<SysUserEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<SysUserEntity> List=this.sysUserDao.queryAll(sysUserDto);
		PageInfo<SysUserEntity> pageInfo=pageEntity.toPageInfo();
		Page<SysUserDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,SysUserDto.class);
        PageInfo<SysUserDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    @Override
    public PageInfo<SysUserDto> queryByPageX(SysUserDto sysUserDto, int currentPage, int pageSize) {
        Page<SysUserEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<SysUserEntity> List=this.sysUserDao.queryAllX(sysUserDto);
        PageInfo<SysUserEntity> pageInfo=pageEntity.toPageInfo();
        Page<SysUserDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,SysUserDto.class);
        PageInfo<SysUserDto> pageDtoInfo=pageDto.toPageInfo();
        return pageDtoInfo;
    }

    /**
     * 新增数据
     *
     * @param sysUserDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public int insert(SysUserDto sysUserDto) {
        int isok =this.sysUserDao.insert(sysUserDto);
        if(isok>0){
            return sysUserDto.getSuId();
        }
        return isok;
    }

    /**
     * 修改数据
     *
     * @param sysUserDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(SysUserDto sysUserDto) {
        int count=this.sysUserDao.update(sysUserDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param suId 主键
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean deleteById(Integer suId) {
        return this.sysUserDao.deleteById(suId) > 0;
    }
}
