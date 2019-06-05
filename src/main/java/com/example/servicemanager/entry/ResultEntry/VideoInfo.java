package com.example.servicemanager.entry.ResultEntry;


public class VideoInfo {

  private long id;
  private String videoName;
  private String videoDesc;
  private double videoPrice;
  private long videoClassid;
  private String videoPath;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getVideoName() {
    return videoName;
  }

  public void setVideoName(String videoName) {
    this.videoName = videoName;
  }


  public String getVideoDesc() {
    return videoDesc;
  }

  public void setVideoDesc(String videoDesc) {
    this.videoDesc = videoDesc;
  }


  public double getVideoPrice() {
    return videoPrice;
  }

  public void setVideoPrice(double videoPrice) {
    this.videoPrice = videoPrice;
  }


  public long getVideoClassid() {
    return videoClassid;
  }

  public void setVideoClassid(long videoClassid) {
    this.videoClassid = videoClassid;
  }


  public String getVideoPath() {
    return videoPath;
  }

  public void setVideoPath(String videoPath) {
    this.videoPath = videoPath;
  }

}
