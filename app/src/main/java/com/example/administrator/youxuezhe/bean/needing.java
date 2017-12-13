package com.example.administrator.youxuezhe.bean;

/**
 * needingBean 需求类
 * Created by Administrator on 2017/12/13 0013.
 */

public class needing {
    private String iconUrl;
    private String typeText;
    private int price;
    private String publishName;

    public int getPrice() {
        return price;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getPublishName() {
        return publishName;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }
}
