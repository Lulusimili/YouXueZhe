package com.example.administrator.youxuezhe.bean;

/**
 * needings需求类详情
 * Created by qingd on 2017/12/13.
 */

public class needingInfo {
    private int needId;
    private int userId;
    private String needName;
    private String needDetail;
    private int price;
    private String imgUrl;
    private int workTime;
    public String getNeedName(){
        return needName;
    }
    public void setNeedName(String needName){
        this.needName=needName;
    }
    public String getNeedDetail(){
        return needDetail;
    }
    public void setNeedDetail(String needDetail){
        this.needDetail=needDetail;
    }
    public String getImgUrl(){
        return imgUrl;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl=imgUrl;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price=price;
    }
    public int getNeedId(){
        return needId;
    }
    public void setPid(int needId){
        this.needId=needId;
    }
    public int getUserId(){
        return getNeedId();
    }
    public void setUserId(int userId){
        this.userId=userId;
    }
    public int getWorkTime(){
        return workTime;
    }

    public void setNeedId(int needId) {
        this.needId = needId;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

}
