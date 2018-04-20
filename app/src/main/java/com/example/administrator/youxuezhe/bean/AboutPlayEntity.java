package com.example.administrator.youxuezhe.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/1 0001.
 *
 */

public class AboutPlayEntity implements Serializable {
    public static final long serialVersionUID = 1L;
    /**
     * [{"id":6,       								//活动ID
     "userId":1,									//用户ID
     "pictureUrl":"www.google.com",                 //用户头像
     "title":"学习",								 	//主题
     "memberNum":3,									//参与人数
     "expectedNum":0,								//期望参与人数
     "startTime":null,								//活动起始时间
     "deadline":null,								//活动截止时间
     "publishTime":null,							//发布时间
     "content":"学习",								//内容
     "label":"学习"}]								//标签
     */
    private String id;
    private String userId;
    private String pictureUrl;
    private String title;
    private String memberNum;
    private String expectedNum;
    private String startTime;
    private String deadline;
    private String publishTime;
    private  String content;
    private String label;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getExpectedNum() {
        return expectedNum;
    }

    public String getId() {
        return id;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public String getLabel() {
        return label;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setExpectedNum(String expectedNum) {
        this.expectedNum = expectedNum;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}
