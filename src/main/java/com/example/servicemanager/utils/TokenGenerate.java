package com.example.servicemanager.utils;

import org.jose4j.jwt.consumer.InvalidJwtException;

public interface TokenGenerate {

    //根据用户名来创建Token
    String create(String username);
    /*
     *验证Token
     *验证不和法的情况jose4j会抛出一个异常
     */
    boolean valid(String token) throws InvalidJwtException;
}
