package com.example.administrator.youxuezhe.bean;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 用户信息类
 */

public class User {
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userSchool;
    private String userGrade;
    private String userMajor;
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getUserPassword(){
        return userPassword;
    }
    public void setUserPassword(String userPassword){
        this.userPassword=userPassword;
    }
    public String getUserEmail(){
        return userEmail;
    }
    public void setUserEmail(String userEmail){
        this.userEmail=userEmail;
    }
    public String getUserSchool(){
        return userSchool;
    }
    public void setUserSchool(String userSchool){
        this.userSchool=userSchool;
    }
    public String getUserGrade(){
        return userGrade;
    }
    public void setUserGrade(String userGrade){
        this.userGrade=userGrade;
    }
    public String getUserMajor(){
        return userMajor;
    }
    public void setUserMajor(String userMajor){
        this.userMajor=userMajor;
    }

}
