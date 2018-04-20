package com.example.administrator.youxuezhe.bean;

/**
 * needingBean 需求类
 * Created by Administrator on 2017/12/13 0013.
 */

public class needing {
    private String imgUrl;
    private String  UserName;
    private int price;
    private String  needName;
    private String needId;

    public int getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getNeedName() {
        return needName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl=imgUrl;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public void setNeedName(String needName) {
        this.needName=needName;
    }

    public void setUserName(String userName)
    {
        this.UserName=userName;
    }
    public String getNeedId(){
        return needId;
    }
    public void setNeedId(String needId){
        this.needId=needId;
    }
}
