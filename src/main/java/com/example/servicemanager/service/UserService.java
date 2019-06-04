package com.example.servicemanager.service;


import com.example.servicemanager.entry.ResultEntry.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {

    BaseResult login(Map<String, Object> loginRep, HttpServletRequest request);

    BaseResult register(Map<String,Object> map);
    BaseResult getUserInfo(Object user_id,String filds);

}
