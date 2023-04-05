package com.trkj.yuyueuser.config;

import com.trkj.util.JwtTokenUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }
}

