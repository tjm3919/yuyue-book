package com.trkj.service;

public interface RedisService {
    public void set(String key, String value, Long timeOut);
    public String get(String key);
    public void del(String key);
}
