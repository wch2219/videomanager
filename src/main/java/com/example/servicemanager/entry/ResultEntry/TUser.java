package com.example.servicemanager.entry.ResultEntry;


import java.sql.Date;

public class TUser {

  private long userId;
  private String userRealname;
  private String userNickname;
  private String userPhone;
  private long userRank;
  private String userEmail;
  private Date userCreateTime;
  private Date userUptime;
  private String userPassword;
  private String token;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserRealname() {
    return userRealname;
  }

  public void setUserRealname(String userRealname) {
    this.userRealname = userRealname;
  }


  public String getUserNickname() {
    return userNickname;
  }

  public void setUserNickname(String userNickname) {
    this.userNickname = userNickname;
  }


  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }


  public long getUserRank() {
    return userRank;
  }

  public void setUserRank(long userRank) {
    this.userRank = userRank;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public Date getUserCreateTime() {
    return userCreateTime;
  }

  public void setUserCreateTime(Date userCreateTime) {
    this.userCreateTime = userCreateTime;
  }


  public Date getUserUptime() {
    return userUptime;
  }

  public void setUserUptime(Date userUptime) {
    this.userUptime = userUptime;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
