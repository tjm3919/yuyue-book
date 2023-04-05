package com.trkj.yuyuejwt.controller;

import cn.hutool.http.Header;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.SystemUtil;
import com.trkj.dto.account.SysAccountDto;
import com.trkj.exception.CustomError;
import com.trkj.exception.CustomErrorType;
import com.trkj.service.RedisService;
import com.trkj.service.SysAccountService;
import com.trkj.util.AjaxResponse;
import com.trkj.util.JwtTokenUtil;
import com.trkj.yuyuejwt.aop.LoginLog;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * (SysAccountController)表控制层
 *
 * @author makejava
 * @since 2023-01-25 10:33:12
 */
@RestController
@RequestMapping("/jwt")
@Slf4j
//@CrossOrigin // 解决跨域问题
public class SysAccountController {
    /**
     * 服务对象
     */
    @Resource
    private SysAccountService sysAccountService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @DubboReference(check = false)
    private RedisService redisService;

    // 登录验证账户
    @LoginLog(value = "开始登录",key = "test",type = "登录") // log日志，特殊日志可以使用注解进行记录
    @PostMapping("/validateAccount")
    public AjaxResponse tologin(@RequestBody SysAccountDto sysAccountDto,HttpServletRequest request) {
        // log.info("SysAccountDto:{}",sysAccountDto);
        SysAccountDto dto = sysAccountService.findAllByUsername(sysAccountDto.getSaName());
        // log.info("Dto:{}",dto);
        // 判断账户密码是否正确
        if (!sysAccountDto.getSaPassword().equals(dto.getSaPassword())){
            throw new CustomError(CustomErrorType.ACCOUNT_ERROR,"密码错误");
        }
        String redisToken = redisService.get("token"+dto.getSaId());
        // log.info("单点登录-----------redisToken:{} isEmpty:{}",redisToken,StringUtils.isEmpty(redisToken));
        if (!StringUtils.isEmpty(redisToken)){ // 不为空刷新token
            // log.info("-----------token:{}","token"+dto.getSaId());
            // 生成新的token
            String refreshToken = jwtTokenUtil.refreshToken(redisToken);
            // 覆盖token
            redisService.set("token"+dto.getSaId(), refreshToken, jwtTokenUtil.getExpire() / 1000);
            throw new CustomError(CustomErrorType.TOKEN_REFRESH,refreshToken);
        }
        // 生成token令牌
        String token=jwtTokenUtil.generateToken(dto.getSaName(),null);
        dto.setToken(token);
        // token以账号id为key存储到redis中
        redisService.set("token"+dto.getSaId(), token, jwtTokenUtil.getExpire() / 1000);
        return AjaxResponse.success(dto);
    }

    /**
     * 退出登录
     * @param said 账号id
     * @return
     */
    @LoginLog(value = "退出登录",key = "test",type = "登录")
    @PostMapping("/loginout")
    public AjaxResponse loginout(int said,String saname){
        boolean isok = false;
        String redisToken = redisService.get("token"+said);
        if(!StringUtils.isEmpty(redisToken)){
            // 生成新的token
            String refreshToken = jwtTokenUtil.refreshToken(redisToken);
            // 覆盖token
            redisService.set("token"+said, refreshToken, jwtTokenUtil.getExpire() / 1000);
            // 删除redis中存储的token
            redisService.del("token"+said);
            isok = true;
        }
        return AjaxResponse.success(isok);
    }

    /**
     * 获取系统参数
     * @param request
     */
    private void toPrint(HttpServletRequest request){
        System.out.println("--------------------------服务端信息-----------------------------");
        SystemUtil.dumpSystemInfo();
        System.out.println("--------------------------客户端信息-----------------------------");
        UserAgent ua = UserAgentUtil.parse(request.getHeader(Header.USER_AGENT.toString()));
        System.out.println(ua.getPlatform().toString());
        System.out.println(JSONUtil.toJsonStr(ua));
    }
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        String ip=map.get("x-forwarded-for");
        String clientside=map.get("sec-ch-ua-platform");
        String[] browsers=map.get("user-agent").split("/");
        String browser = browsers[2]+"/"+browsers[5];

        Map<String, String> header = new HashMap<String, String>();
        header.put("ip",ip); // IP
        header.put("clientside",clientside); // 客户端类型
        header.put("browser",browser);// 浏览器类型
        return header;
    }

    /**
     * 分页查询
     * @param sysAccountDto 筛选条件
     * @param currentPage  当前页码
	 * @param pageSize     每页大小（条数）
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public AjaxResponse queryByPage(@RequestBody SysAccountDto sysAccountDto,int currentPage, int pageSize) {
        return AjaxResponse.success(this.sysAccountService.queryByPage(sysAccountDto,currentPage,pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public AjaxResponse  queryById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.sysAccountService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysAccountDto 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResponse add(@RequestBody SysAccountDto sysAccountDto) {
        boolean isok = false;
        // 新增普通读者
        if(sysAccountDto.getSaType()==2 || sysAccountDto.getSysUserDto().getSuType().equals("读者")){
            isok = this.sysAccountService.insert(sysAccountDto);
        }else {  // 新增管理员、作者
            isok = this.sysAccountService.insertSA(sysAccountDto);
        }
        return AjaxResponse.success(isok);
    }

    /**
     * 编辑数据
     *
     * @param sysAccountDto 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public AjaxResponse edit(@RequestBody SysAccountDto sysAccountDto) {
        return AjaxResponse.success(this.sysAccountService.update(sysAccountDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResponse deleteById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(this.sysAccountService.deleteById(id));
    }

}

