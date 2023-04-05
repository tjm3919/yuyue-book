package com.trkj.yuyuelog.service.impl;

import com.trkj.util.BeanTools;
import com.trkj.yuyuelog.entity.LoginLogEntity;
import com.trkj.dto.log.LoginLogDto;
import com.trkj.yuyuelog.dao.LoginLogDao;
import com.trkj.service.LoginLogService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 登录日志表(LoginLog)表服务实现类
 *
 * @author makejava
 * @since 2023-01-24 11:11:36
 */
@Service("loginLogService")
@DubboService
@Slf4j
public class LoginLogServiceImpl implements LoginLogService {
    @Resource
    private LoginLogDao loginLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param llId 主键
     * @return 实例对象
     */
    @Override
    public LoginLogDto queryById(Integer llId) {
		LoginLogEntity entity=this.loginLogDao.queryById(llId);
		LoginLogDto dto=new LoginLogDto() ;
		BeanTools.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 分页查询
     *
     * @param loginLogDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @Override
    public PageInfo<LoginLogDto> queryByPage(LoginLogDto loginLogDto, int currentPage, int pageSize) {
		Page<LoginLogEntity> pageEntity= PageHelper.startPage(currentPage,pageSize);
        List<LoginLogEntity> List=this.loginLogDao.queryAll(loginLogDto);
		PageInfo<LoginLogEntity> pageInfo=pageEntity.toPageInfo();
		Page<LoginLogDto> pageDto=new Page<>() ;
        BeanTools.copyList(pageEntity,pageDto,LoginLogDto.class);
        PageInfo<LoginLogDto> pageDtoInfo=pageDto.toPageInfo();
		return pageDtoInfo;
    }

    /**
     * 新增数据
     *
     * @param loginLogDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean insert(LoginLogDto loginLogDto) {
        // 查询上一次登录时间修改登出时间
//        LoginLogEntity entity=this.loginLogDao.queryEnd(loginLogDto.getSaId());
//        log.info("LoginLogEntity:{}",entity);
//        if(!StringUtils.isEmpty(entity) && entity.getLlOutTime()==null){
//            LoginLogDto dto=new LoginLogDto() ;
//            BeanTools.copyProperties(entity,dto);
//            dto.setLlOutTime(new Date());
//            this.loginLogDao.update(dto);
//        }
        loginLogDto.setLlUpTime(new Date());
        int count=this.loginLogDao.insert(loginLogDto);
        return count>0;
    }

    /**
     * 修改数据
     *
     * @param loginLogDto 实例对象
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean update(LoginLogDto loginLogDto) {
        int count=this.loginLogDao.update(loginLogDto);
        return count>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param llId 主键
     * @return 是否成功
     */
    @Override
	@Transactional
    public boolean deleteById(Integer llId) {
        return this.loginLogDao.deleteById(llId) > 0;
    }

    /**
     * 查询最后一条数据
     * @return
     */
    @Override
    public LoginLogDto queryEnd(Integer said) {
        LoginLogEntity entity=this.loginLogDao.queryEnd(said);
        LoginLogDto dto=new LoginLogDto() ;
        BeanTools.copyProperties(entity,dto);
        return dto;
    }
}
