package com.example.servicemanager.service.serviceimpl;


import com.alibaba.fastjson.JSON;
import com.example.servicemanager.entry.ResultEntry.BaseResult;
import com.example.servicemanager.entry.ResultEntry.TUser;

import com.example.servicemanager.mapp.UserMapp;
import com.example.servicemanager.service.UserService;
import com.example.servicemanager.utils.Constant;
import com.example.servicemanager.utils.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

import static com.example.servicemanager.utils.Constant.*;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapp userMapp;

    @Override
    public BaseResult login(Map<String, Object> loginRep, HttpServletRequest request) {
        String phone = (String) loginRep.get("phone");
        if (StringUtils.isEmpty(phone)) {

            return new BaseResult(ERROR, "账号空", null);
        }
        if (StringUtils.isEmpty(loginRep.get(Password))) {

            return new BaseResult(ERROR, "密码空", null);
        }


        TUser tUser = userMapp.checkAcc(phone);
        if (tUser == null) {

            return new BaseResult(Constant.PARAM_ERROR, "账号不存在", null);
        }

        String decrypt = EncryptUtil.decrypt(tUser.getUserPassword());

        if (!decrypt.equals(loginRep.get(Password))) {

            return new BaseResult(ERROR, "账号密码错误", null);
        }
//        tUser = userMapp.login(phone,EncryptUtil.encrypt(decrypt));
        String token = UUID.randomUUID().toString();

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(Constant.TokenTime);
        session.setAttribute(token, tUser.getUserId());
        return new BaseResult(SUCCESS, "登录成功", token);
//

    }

    @Override
    public BaseResult register(Map<String, Object> map) {

        TUser tUser = userMapp.checkAcc((String) map.get(Phone));
        if (tUser != null) {

            return new BaseResult(ERROR, "账号已存在,请登录", null);
        }

        String password = EncryptUtil.encrypt((String) map.get(Password));

        int register = userMapp.register((String) map.get(Phone), password);
        if (register == 1) {

            return new BaseResult(SUCCESS, "注册成功请登录", null);
        }
        return new BaseResult(ERROR, "注册失败", null);

    }

    @Override
    public BaseResult getUserInfo(Object user_id, String filds) {
//        String[] split = filds.split(",");
//        StringBuilder fildsBuilder = new StringBuilder(filds);
//        for (String s : split) {
//            fildsBuilder.append(s);
//        }
//        filds = fildsBuilder.toString();
        try {
            Map<String, Object> info = userMapp.getInfo(user_id);
            if (info == null) {

                return new BaseResult(ERROR, "请求失败", null);
            }
            return new BaseResult(SUCCESS, "成功", info);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            String message = cause.getMessage();
            System.out.println(JSON.toJSONString(cause));
            return new BaseResult(ERROR, "失败", message);
        }

    }
}
