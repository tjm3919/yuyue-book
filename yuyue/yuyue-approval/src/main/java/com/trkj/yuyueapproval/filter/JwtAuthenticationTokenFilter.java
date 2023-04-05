package com.trkj.yuyueapproval.filter;


import com.trkj.exception.CustomError;
import com.trkj.exception.CustomErrorType;
import com.trkj.service.RedisService;
import com.trkj.service.TokenService;
import com.trkj.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private JwtTokenUtil jwtTokenUtil;


    @DubboReference(check = false)
    private RedisService redisService;


    @DubboReference(check = false)
    private TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        String uri=request.getRequestURI();
        log.info("in sys_jwt_service JwtAuthenticationTokenFilter uri={},method={} Header={}",uri,request.getMethod(),jwtTokenUtil.getHeader());
        if(uri.matches(".*/jwt/validateAccount")){
            filterChain.doFilter(request,response);
            return;
        }
//        if(uri.matches(".*/jwt/addacc.*")){
//            filterChain.doFilter(request,response);
//            return;
//        }
        log.info("在sys_jwt_service filter中，jwtToken:{}",jwtToken);
        if(StringUtils.isEmpty(jwtToken))
            jwtToken = request.getParameter("jwtToken");

        // 从 redis 拿 token
        if(StringUtils.isEmpty(jwtToken)){
            jwtToken = redisService.get("token"+request.getParameter("said"));
            log.info("缓存token===-----{}",jwtToken);
        }


        if(StringUtils.isEmpty(jwtToken)){
            resolver.resolveException(request,response,null,new  CustomError(CustomErrorType.TOKEN_ERROR,"Token为空"));
            return;
        }
        log.info("---通过-------111111--------------");
        String username = tokenService.getUsernameFromToken(jwtToken);
        if(StringUtils.isEmpty(username)){
            resolver.resolveException(request,response,null,new  CustomError(CustomErrorType.TOKEN_ERROR,"Token信息有问题"));
            return;
        }
        log.info("{} ---通过---------2222222------------",username);
        if (!tokenService.validateToken(jwtToken,username)){
            resolver.resolveException(request,response,null,new CustomError(CustomErrorType.TOKEN_ERROR,"Token失效"));
            return;
        }

//        if (!tokenService.tokenBoolean(jwtToken)){
//            resolver.resolveException(request,response,null,new CustomError(CustomErrorType.TOKEN_ERROR,"Token失效2"));
//            return;
//        }
        log.info("---通过-------33333--------------");
        RequestContextHolder.currentRequestAttributes().setAttribute("jwtToken",jwtToken , RequestAttributes.SCOPE_REQUEST);
        filterChain.doFilter(request,response);
    }
}
