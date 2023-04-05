package com.trkj.yuyueintegral.service.impl;

import com.trkj.dto.user.SysUserDto;
import com.trkj.service.SysUserService;
import com.trkj.util.BeanTools;
import com.trkj.yuyueintegral.entity.IntegralEntity;
import com.trkj.dto.integral.IntegralDto;
import com.trkj.yuyueintegral.dao.IntegralDao;
import com.trkj.service.IntegralService;
import org.apache.dubbo.config.annotation.DubboReference;
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
 * 积分详情表(Integral)表服务实现类
 *
 * @author makejava
 * @since 2023-02-07 14:30:06
 */
@Service("integralService")
@DubboService
@Slf4j
public class IntegralServiceImpl implements IntegralService {
    @Resource
    private IntegralDao integralDao;

    @DubboReference
    private SysUserService sysUserService;

    /**
     * 通过ID查询单条数据
     *
     * @param inId 主键
     * @return 实例对象
     */
    @Override
    public IntegralDto queryById(Integer inId) {
		IntegralEntity entity=this.integralDao.queryById(inId);
		IntegralDto dto=new IntegralDto() ;
		BeanTools.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param integralDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<IntegralDto> queryByPage(IntegralDto integralDto, int currentPage, int pageSize) {
		Page<IntegralEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<IntegralEntity> List=this.integralDao.queryAll(integralDto);
		PageInfo<IntegralEntity> pageInfo=pageEntity.toPageInfo();
		Page<IntegralDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,IntegralDto.class);
        PageInfo<IntegralDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 新增数据
     *
     * @param integralDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public int insert(IntegralDto integralDto) {
        if(integralDto.getAmType().equals("阅读图书")){
            SysUserDto sysUserDto = this.sysUserService.queryById(integralDto.getSuId());
            sysUserDto.setSuId(integralDto.getSuId());
            sysUserDto.setSumNum(sysUserDto.getSumNum()+integralDto.getAmNum());
            this.sysUserService.update(sysUserDto);
        }
        return this.integralDao.insert(integralDto);
    }

    /**
     * 修改数据
     *
     * @param integralDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(IntegralDto integralDto) {
        int count=this.integralDao.update(integralDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param inId 主键
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean deleteById(Integer inId) {
        return this.integralDao.deleteById(inId) > 0;
    }
}
