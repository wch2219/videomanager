package com.example.servicemanager.mapp;


import com.example.servicemanager.entry.ResultEntry.TUser;
import com.sun.istack.internal.NotNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapp {
    @Select("select * from t_user where t_user.user_phone = #{user_phone}")
    TUser checkAcc(String phone);

    @Select("select * from t_user where user_phone = #{phone} and user_password = #{pass}")
    TUser login( String phone, String pass);

    void register(String phone, String password);
}
