package com.example.servicemanager.controller;

import com.example.servicemanager.entry.ResultEntry.BaseResult;
import com.example.servicemanager.entry.ResultEntry.VideoInfo;
import com.example.servicemanager.service.VideoService;

import com.example.servicemanager.utils.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/video")
public class VideoController extends BaseController {
    @Resource
    VideoService mVideoService;

    @PostMapping("/videolist")
    public BaseResult getVideoList(@RequestParam("pageindex") int pageindex, @RequestParam("pagesize") int pagesize){
        PageHelper.startPage(pageindex, pagesize);
        List<VideoInfo> maps = mVideoService.videoList(pageindex,pagesize);
        PageInfo<VideoInfo> pageInfo = new PageInfo<>(maps);

        return new BaseResult(Constant.SUCCESS,"成功",pageInfo);
    }
}
