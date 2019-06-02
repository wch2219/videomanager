package com.example.servicemanager.service.serviceimpl;


import com.example.servicemanager.entry.ReponceEnt.LoginRep;
import com.example.servicemanager.entry.ResultEntry.BaseResult;
import com.example.servicemanager.entry.ResultEntry.TUser;

import com.example.servicemanager.mapp.UserMapp;
import com.example.servicemanager.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapp userMapp;

    @Override
    public BaseResult login(LoginRep loginRep) {
        if (StringUtils.isEmpty(loginRep.getPhone())) {

            return new BaseResult(204, "账号空", null);
        }
        if (StringUtils.isEmpty(loginRep.getPsaaword())) {

            return new BaseResult(204, "密码空", null);
        }


        TUser tUser = userMapp.checkAcc(loginRep.getPhone());
        if (tUser == null) {

            return new BaseResult(204, "账号不存在", null);
        }

        TUser login = userMapp.login(loginRep.getPhone(), loginRep.getPsaaword());
        if (login == null) {
            return new BaseResult(204, "账号密码错误", null);
        }
        return new BaseResult(200, "登录成功", login);
    }

    @Override
    public BaseResult register(String phone, String password) {

        TUser tUser = userMapp.checkAcc(phone);
        if (tUser != null) {

            return new BaseResult(204, "账号已存在,请登录", null);
        }

        userMapp.register( phone,  password);

        return null;
    }
}
