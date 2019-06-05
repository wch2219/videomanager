package com.example.servicemanager.service;



import com.example.servicemanager.entry.ResultEntry.VideoInfo;

import java.util.List;

public interface VideoService {

    List<VideoInfo> videoList(int pageindex, int pagesize);
}
