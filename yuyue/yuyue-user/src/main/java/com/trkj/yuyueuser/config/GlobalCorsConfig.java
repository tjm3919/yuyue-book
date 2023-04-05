package com.trkj.yuyueuser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        List<String> corsAllowedOrigins =new ArrayList<>();
        corsAllowedOrigins.add("*");
        config.setAllowedOrigins(corsAllowedOrigins);
        // 设置你要允许的网站域名
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://127.0.0.1:3000");
        //允许跨域发送cookie
        config.setAllowCredentials(true);
        //放行全部原始头信息
        config.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        List<String> corsAllowedMethods=new ArrayList<>();
        corsAllowedMethods.add("OPTIONS");
        corsAllowedMethods.add("HEAD");
        corsAllowedMethods.add("GET");
        corsAllowedMethods.add("PUT");
        corsAllowedMethods.add("POST");
        corsAllowedMethods.add("DELETE");
        config.setAllowedMethods(corsAllowedMethods);
        config.applyPermitDefaultValues();
        config.setAllowCredentials(true);//这两句不加不能跨域上传文件，
        config.setMaxAge(36000L);//加上去就可
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
