package com.trkj.yuyueredis.service;

import com.trkj.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@DubboService
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //存缓存
    @Override
    public void set(String key, String value, Long timeOut) {
        log.info("村缓存 {} {} {}",key,value,timeOut);
        redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.SECONDS);
    }

    //取缓存
    @Override
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    //清除缓存
    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }
}