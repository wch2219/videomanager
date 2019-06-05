package com.example.servicemanager.mapp;


import com.example.servicemanager.entry.ResultEntry.VideoInfo;
import com.example.servicemanager.mapp.SqlUtils.VideoSql;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface VideoMapp {

    @Select("select * from video_info")
    Page<VideoInfo> getVideoList(int pageindex, int pagesize);
}
