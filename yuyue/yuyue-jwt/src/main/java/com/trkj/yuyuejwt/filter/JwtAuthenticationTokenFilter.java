package com.trkj.yuyuejwt.filter;


import com.trkj.exception.CustomError;
import com.trkj.exception.CustomErrorType;
import com.trkj.service.RedisService;
import com.trkj.service.TokenService;
import com.trkj.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/***
 * @author 大熊
 * @date 2020-07-03
 * @Description:接口访问鉴权过滤器;当用户第一次登陆之后，我们将JWT令牌返回给了客户端，客户端将该令牌保存起来。
 *              在进行接口请求的时候，将令牌带上，放到HTTP的header里面，该过滤器进行令牌的解析
 * 1、拦截接口请求，从请求request获取token，从token中解析得到用户名
 * 根据用户信息和JWT令牌，验证系统用户与用户输入的一致性，并判断JWT是否过期。如果没有过期，至此表明了该用户的确是该系统的用户。
 * @version 1.0
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    // 注入 HandlerExceptionResolver
    /**
     * 将Filter中的异常转移到全局异常可处理的范围内，可以使用 HandlerExceptionResolver 中的
     * resolveException 方法来实现,如果不这样处理，过滤器中的异常将不会被GlobalExceptionHandler捕捉到
     * **/
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Resource
    JwtTokenUtil jwtTokenUtil;

    @DubboReference(check = false)
    private RedisService redisService;

    @Autowired
    private TokenService tokenService;

    @Value("${spring.redis.expire}")
    private Long expire;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        String uri=request.getRequestURI();
        log.info("in sys_jwt_service JwtAuthenticationTokenFilter uri={},method={}",uri,request.getMethod());
        if(uri.matches(".*/jwt/validateAccount")){
            //RequestContextHolder.currentRequestAttributes().setAttribute("jwtToken",jwtToken , RequestAttributes.SCOPE_REQUEST);
            filterChain.doFilter(request,response);
            return;
        }
        if(uri.matches(".*/sysUser/add.*")){
            filterChain.doFilter(request,response);
            return;
        }
        if(uri.matches(".*/jwt/add.*")){
            filterChain.doFilter(request,response);
            return;
        }
        log.info("jwt_demo filter中，jwtToken:{}",jwtToken);
        // 从参数中拿 token
        if(StringUtils.isEmpty(jwtToken)) {
            jwtToken = request.getParameter("jwtToken");
        }
        // 从 redis 拿 token
        if(StringUtils.isEmpty(jwtToken)){
            jwtToken = redisService.get("token"+request.getParameter("said"));
            log.info("缓存token===-----{}",jwtToken);
        }

        if(StringUtils.isEmpty(jwtToken)){
            resolver.resolveException(request,response,null,new  CustomError(CustomErrorType.TOKEN_ERROR,"Token为空"));
            return;
        }
        String username = tokenService.getUsernameFromToken(jwtToken);
        if(StringUtils.isEmpty(username)){
            resolver.resolveException(request,response,null,new  CustomError(CustomErrorType.TOKEN_ERROR,"Token信息有问题"));
            return;
        }

        // 续签
        Date date = jwtTokenUtil.expirationDate(jwtToken);
        // 计算剩余时间
        Long ResidualTime = date.getTime()-new Date().getTime();
        log.info("TokenTime------- date:{} ResidualTime:{} Expire:{}",date,ResidualTime,jwtTokenUtil.getExpire()/1000);
        if(ResidualTime < 120000){
            // 刷新jwt
            String refreshToken = jwtTokenUtil.refreshToken(jwtToken);
            // 覆盖token
            redisService.set("token"+jwtTokenUtil.getUserIdFromToken(jwtToken),refreshToken,jwtTokenUtil.getExpire()/1000);
            resolver.resolveException(request,response,null,new CustomError(CustomErrorType.TOKEN_ERROR,refreshToken));
        }

        log.info("jwtfw通过--------------");
        RequestContextHolder.currentRequestAttributes().setAttribute("jwtToken",jwtToken , RequestAttributes.SCOPE_REQUEST);
        filterChain.doFilter(request,response);
    }
}
