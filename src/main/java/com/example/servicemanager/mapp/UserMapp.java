package com.example.servicemanager.mapp;


import com.example.servicemanager.entry.ResultEntry.TUser;
import com.example.servicemanager.mapp.SqlUtils.UserSelect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;


@Mapper
public interface UserMapp {
    @Select("select * from t_user where t_user.user_phone = #{user_phone}")
    TUser checkAcc(String phone);

    @SelectProvider(type = UserSelect.class,method = "selectUser")
    Map<String,Object> login(Map<String,Object> map);


    Map<String,Object> register(Map<String,Object> map);
}
