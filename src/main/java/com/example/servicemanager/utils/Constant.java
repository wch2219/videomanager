package com.example.servicemanager.utils;

public class Constant {
    public static final int SUCCESS = 200;//请求成功
    public static final int ERROR = 204;//请求成功
    public static final int UN_AUTHORIZED = 401;//Token 过期
    public static final int PARAM_ERROR = 402;//入参错误
    public static final int FILE_ERROR = 403;//文件传输异常



    public static final String Phone = "phone";
    public static final String Password = "password";
    public static final String SmsCode = "smscode";
    public static final String Token = "token";

    public static final int TokenTime = 60*60*24;//token 缓存时长 秒
    public static final String Fields = "fields";
    public static final String User_ID = "user_id";
}
