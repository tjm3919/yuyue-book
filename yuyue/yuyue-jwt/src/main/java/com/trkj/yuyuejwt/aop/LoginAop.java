package com.trkj.yuyuejwt.aop;

import com.trkj.dto.account.SysAccountDto;
import com.trkj.dto.log.LoginLogDto;
import com.trkj.exception.CustomError;
import com.trkj.exception.CustomErrorType;
import com.trkj.service.LoginLogService;
import com.trkj.service.SysAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Aspect
@Component
@Slf4j
public class LoginAop {

    @DubboReference(check = false)
    LoginLogService loginLogService;

    @DubboReference(check = false)
    SysAccountService accountService;

//    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port}")
    private int serverPort;

    // Pointcut 是指那些方法需要被执行"AOP",是由"Pointcut Expression"来描述的
    // Pointcut 可以有下列方式来定义或者通过&& || 和!的方式进行组合
    @Pointcut(value = "@annotation(com.trkj.yuyuejwt.aop.LoginLog)")
    public void cutService() {
    }

    /**
     * 1.ProceedingJoinPoint只适用于环绕通知,因为只有环绕通知,才能控制目标方法的运行.
     * 2.JoinPoint 适用于其它的四大通知类型,可以用来记录运行的数据.
     * 3.ProceedingJoinPoint 中有特殊的方法proceed();
     * 4.如果使用"JoinPoint" 则必须位于参数的第一位
     * @param point
     * @throws ClassNotFoundException
     */
    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        try {
            Object[] objects = point.getArgs();
            log.info("Args:{}",objects);
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }
        return result;
    }

    private void handle(ProceedingJoinPoint point){
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();
        // 判断是登录还是退出登录
        if(request.getRequestURI().matches(".*/jwt/validateAccount")){
            System.out.println("--------------进入登录日志--------------");
            LoginLogDto logDto=Args(point);
            // 设置IP地址
            if(StringUtils.isEmpty(logDto.getLlIp())){
                logDto.setLlIp(request.getRemoteAddr());
            }
            // 新增日志
            loginLogService.insert(logDto);
        }else if(request.getRequestURI().matches(".*/jwt/loginout")){
            System.out.println("--------------进入登出日志--------------");
            LoginLogDto logDto=Args2(point);
            // 修改日志登出时间
            logDto.setLlOutTime(new Date());
            loginLogService.update(logDto);
        }else {
            throw new CustomError(CustomErrorType.LOG_ERROR,"日志异常");
        }
    }

    /**
     * 获取登录对象的方法
     * @param point
     * @return
     */
    private LoginLogDto Args(ProceedingJoinPoint point){
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();

        // 获取方法中的参数
        Object[] args = point.getArgs();
        SysAccountDto sysAccountDto = (SysAccountDto) args[0];
        LoginLogDto logDto = sysAccountDto.getLogDto();

        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames(); // 获取所有参数
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
//        log.info("Browser:{}",map);
        String ip=map.get("x-forwarded-for"); // IP
        if(!StringUtils.isEmpty(ip)){ // 判断是否是浏览器登录
            String clientside=map.get("sec-ch-ua-platform").split("\"")[1]; // 客户端类型(PC端、安卓端、IOS端)
            if(clientside.equals("Windows")){
                clientside="PC";
            }
            // 去除""
//        char[] ss = clientside.toCharArray();
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < ss.length; i++) {
//            if(i!=0 && i!=ss.length-1 && ss[i]=='\"'){
//                sb.append(ss[i]);
//            }
//        }
//        clientside = sb.toString();
            String pdMap = map.toString();
//            log.info("Mapsss:{}",pdMap);
            String[] browsers=map.get("user-agent").split("/"); // 浏览器类型
            String bro1 = browsers[2].split(" ")[browsers[2].split(" ").length-1]+"/"; // 底层浏览器
            StringBuffer browser = new StringBuffer(bro1);
            if(pdMap.contains("Microsoft Edge")){// 判断是否为Edge浏览器
                browser.append("Edge");
            }else if(pdMap.contains("Google Chrome")){// 判断是否为谷歌浏览器
                browser.append("Chrome");
            }else if(pdMap.contains("QQBrowser")){ // 判断是否为QQ浏览器
                String bro2 = browsers[5].split(" ")[browsers[5].split(" ").length-1];
                browser.append(bro2);
            }

            // 给登录日志对象赋值
            logDto.setLlIp(ip);
            logDto.setLlBrowserType(browser.toString());
            logDto.setLlClientsideType(clientside);
//        log.info("IP:{},clientside:{},browsers:{}",ip,clientside,browser);

            // 查询登录用户的信息
            SysAccountDto dto = accountService.findAllByUsername(sysAccountDto.getSaName());
            logDto.setSuId(dto.getSuId());
            logDto.setSaId(dto.getSaId());
            logDto.setLlLoginType(dto.getSaType());// 登录人类型 1-管理员 2-用户 3-作者
        }
        return logDto;
    }

    /**
     * 获取登出值的方法
     * @param point
     * @return
     */
    private LoginLogDto Args2(ProceedingJoinPoint point){
        Object[] args = point.getArgs();
        // 获取方法中的参数
        Integer said=0;
        try {
            said=Integer.parseInt(args[0]+"");
        }catch (Exception e){
            log.error("日志记录出错!", e);
        }
        LoginLogDto logDto = loginLogService.queryEnd(said);
        return logDto;
    }
}
