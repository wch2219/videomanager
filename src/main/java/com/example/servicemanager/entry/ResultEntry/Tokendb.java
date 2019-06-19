package com.example.servicemanager.entry.ResultEntry;


public class Tokendb {

  private int id;
  private int userId;
  private String token;
  private String createTime;
  private String updataTime;
  private String expirTime;


  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }


  public String getUpdataTime() {
    return updataTime;
  }

  public void setUpdataTime(String updataTime) {
    this.updataTime = updataTime;
  }


  public String getExpirTime() {
    return expirTime;
  }

  public void setExpirTime(String expirTime) {
    this.expirTime = expirTime;
  }

}
