package com.example.servicemanager.service.serviceimpl;

import com.example.servicemanager.entry.ResultEntry.VideoInfo;
import com.example.servicemanager.mapp.VideoMapp;
import com.example.servicemanager.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    VideoMapp videoMapp;


    @Override
    public List<VideoInfo> videoList(int pageindex, int pagesize) {
        return videoMapp.getVideoList(pageindex,pagesize);

    }
}
