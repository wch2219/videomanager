package com.example.servicemanager.service.serviceimpl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.servicemanager.entry.ResultEntry.BaseResult;
import com.example.servicemanager.entry.ResultEntry.TUser;

import com.example.servicemanager.mapp.UserMapp;
import com.example.servicemanager.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapp userMapp;

    @Override
    public BaseResult login(Map<String,Object> loginRep) {
        String phone = (String) loginRep.get("phone");
        if (StringUtils.isEmpty(phone)) {

            return new BaseResult(204, "账号空", null);
        }
        if (StringUtils.isEmpty(loginRep.get("password"))) {

            return new BaseResult(204, "密码空", null);
        }


        TUser tUser = userMapp.checkAcc(phone);
        if (tUser == null) {

            return new BaseResult(204, "账号不存在", null);
        }

       Map<String,Object> map = userMapp.login(loginRep);
        if (map == null) {
            return new BaseResult(204, "账号密码错误", null);
        }
        return new BaseResult(200, "登录成功", JSON.toJSON(map));
    }

    @Override
    public BaseResult register(Map<String,Object> map) {

        TUser tUser = userMapp.checkAcc((String) map.get("phone"));
        if (tUser != null) {

            return new BaseResult(204, "账号已存在,请登录", null);
        }
//
//        userMapp.register( phone,  password);

        return null;
    }
}
