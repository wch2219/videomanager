package com.example.servicemanager.mapp;

import com.example.servicemanager.entry.ResultEntry.Tokendb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;
@Mapper
public interface Tokenmapp {

    @Select("select * from tokendb where token = #{token}")
    Tokendb getUserId(String token);

    @Insert("REPLACE INTO tokendb (user_id,token,expir_time) VALUES(#{user_id}, #{token}, #{expir_time})")
    int saveToken( int user_id,  String token,String expir_time);
}
