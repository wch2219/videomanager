package com.example.servicemanager.mapp;


import com.example.servicemanager.entry.ResultEntry.TUser;
import com.example.servicemanager.mapp.SqlUtils.UserSelect;
import com.example.servicemanager.utils.Constant;
import org.apache.ibatis.annotations.*;

import java.util.Map;


@Mapper
public interface UserMapp {
    @Select("select * from t_user where t_user.user_phone = #{phone}")
    TUser checkAcc(String phone);

//    @SelectProvider(type = UserSelect.class,method = "selectUser")
    @Select("select * from t_user where user_id = #{user_id}")
    Map<String,Object> getInfo(Object user_id);

    @Insert("insert into t_user (user_phone,user_password) values (#{phone},#{password})")
//    @Options(useGeneratedKeys = true, keyProperty = "t_user.user_id")
    int register(String phone,String password);

}
