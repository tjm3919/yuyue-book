package com.trkj.service;


public interface TokenService {
    // 是否有效
    public boolean tokenBoolean(String token);
    // token 是否有问题
    public Boolean validateToken(String token, String userName);

    public String getUsernameFromToken(String token);
}
