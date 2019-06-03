package com.example.servicemanager.mapp;


import com.example.servicemanager.entry.ResultEntry.TUser;
import com.example.servicemanager.service.serviceimpl.SqlUtils.UserSelect;
import com.sun.istack.internal.NotNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;


@Mapper
public interface UserMapp {
    @Select("select * from t_user where t_user.user_phone = #{user_phone}")
    TUser checkAcc(String phone);

    @Select("select * from t_user where user_phone = #{phone} and user_password = #{pass}")
    @SelectProvider(type = UserSelect.class,method = "selectUser")
    TUser login( String phone, String pass);

    Map<String,Object> register(String phone, String password);
}
