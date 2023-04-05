package com.trkj.aop;

import com.trkj.dto.log.LoginLogDto;
import com.trkj.service.LoginLogService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import com.trkj.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志记录
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午8:48:30
 */
//@Aspect // @Aspect 就是把一个类定义为切面供容器读取。
//@Component
public class BussinessLogAop {

    @Resource
    JwtTokenUtil jwtTokenUtil;

    @DubboReference(check = false)
    private LoginLogService loginLogService;

    @Value("${server.port}")
    private int serverPort;

    private Long visitTime;

    private Logger log = LoggerFactory.getLogger(this.getClass());

//  Pointcut(切入点)：JoinPoint的集合，是程序中需要注入Advice的位置的集合，
//  指明Advice要在什么样的条件下才能被触发，在程序中主要体现为书写切入点表达式。
    @Pointcut(value = "@annotation(com.trkj.aop.BussinessLog)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        try {
            handle(point);
            System.out.println("进入操作日志拉");
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point) throws NoSuchMethodException {

        visitTime=System.currentTimeMillis();//开始访问的时间
        Date date=new Date(visitTime);
        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();


        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        String type= annotation.type();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }
        System.out.println("拦截的方法参数："+className);
        System.out.println("获取操作名称"+bussinessName);
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();
        String requestMethod =request.getMethod();

        log.info("请求IP："+request.getRemoteAddr());
        log.info("请求地址："+request.getRequestURI());
        log.info("请求方式："+requestMethod);
        log.info("请求类方法："+point.getSignature());
        log.info("请求类方法参数："+ Arrays.toString(point.getArgs()));
        // 保存日志
        String nameAndValue="";
        if(request.getRequestURI().matches(".*/jwt/validateAccount")){
            System.out.println("登录操作");
            nameAndValue = getNameAndValue(point);
        }else {
            nameAndValue = toName();
        }
        //String toName = toName();
        log.info("lmud={} lway={} lname={} ltime={}",bussinessName,type,nameAndValue, new Date());
        LoginLogDto logDto = new LoginLogDto();
//        logDto.setLname(nameAndValue);
//        logDto.setLway(type);
//        logDto.setLmud(bussinessName);
//        logDto.setLtime(new Date());

//        logDto.setSuId(); // 登录人id
//        logDto.setSaId(); // 登录账号id
//        logDto.setLlUpTime(); // 登录时间
//        logDto.setLlOutTime(); // 登出时间
//        logDto.setLlIp(request.getRemoteAddr()); // 登录IP
//        logDto.setLlBrowserType(); // 浏览器类型
//        logDto.setLlClientsideType(); // 客户端类型
        loginLogService.insert(logDto);
    }


    // 获取 参数
    /**
     * 获取某个Method的参数名称及对应的值
     *
     * @param joinPoint
     * @return Map<参数名称, 参数值></参数名称,参数值>
     */
    public String getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        String sysAccountDto = param.get("sysAccountDto").toString();
        String[] strarray=sysAccountDto.split(", ");
        log.info("xm------{}  {}",strarray[1],strarray[1].split("accountName=")[1]);
        return strarray[1].split("accountName=")[1];
    }



    //  获取用户名
    public String toName(){
        String jwtToken=RequestContextHolder.currentRequestAttributes().getAttribute("jwtToken", RequestAttributes.SCOPE_REQUEST).toString();
        //removeAttribute清理工作建议用拦截器来做
        RequestContextHolder.currentRequestAttributes().removeAttribute("jwtToken",RequestAttributes.SCOPE_REQUEST);
        log.debug("in BussinessLogAop jwtToken={}",jwtToken);
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        return username;
    }
}
