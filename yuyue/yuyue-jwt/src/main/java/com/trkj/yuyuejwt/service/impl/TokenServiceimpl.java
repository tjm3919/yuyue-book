package com.trkj.yuyuejwt.service.impl;

import com.trkj.service.TokenService;
import com.trkj.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService//(version = "1.0.0", timeout = 6000)
@Slf4j
public class TokenServiceimpl implements TokenService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // 验证token 是否有效
    @Override
    public boolean tokenBoolean(String token) {
        Boolean tokenExpired = jwtTokenUtil.isTokenExpired(token);
        log.info("{} 验证token-------------{}",tokenExpired,token);
        return tokenExpired;
    }

    // token 是否有问题
    @Override
    public Boolean validateToken(String token, String userName) {
        Boolean validateToken = jwtTokenUtil.validateToken(token, userName);
        log.info("{} 验证token--2222222--{} ",validateToken,token);
        return validateToken;
    }

    @Override
    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.getUsernameFromToken(token);
    }


}
