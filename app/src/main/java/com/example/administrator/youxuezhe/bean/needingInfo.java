package com.example.administrator.youxuezhe.bean;

/**
 * needings需求类详情
 * Created by qingd on 2017/12/13.
 */

public class needingInfo {
    private int pid;
    private String needing_detail_title;
    private String needing_detail_text;
    private int needing_detail_price;
    private String needing_picture_url;
    public String getNeeding_detail_title(){
        return needing_detail_title;
    }
    public void setNeeding_detail_title(String needing_detail_title){
        this.needing_detail_title=needing_detail_title;
    }
    public String getNeeding_detail_text(){
        return needing_detail_text;
    }
    public void setNeeding_detail_text(String needing_detail_text){
        this.needing_detail_text=needing_detail_text;
    }
    public String getNeeding_picture_url(){
        return needing_picture_url;
    }
    public void setNeeding_picture_url(String needing_picture_url){
        this.needing_picture_url=needing_picture_url;
    }
    public int getNeeding_detail_price(){
        return needing_detail_price;
    }
    public void setNeeding_detail_price(int needing_detail_price){
        this.needing_detail_price=needing_detail_price;
    }
    public int getPid(){
        return pid;
    }
    public void setPid(int pid){
        this.pid=pid;
    }
}
