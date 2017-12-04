package com.example.administrator.youxuezhe.bean;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class MyRequestBody {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int setCode(int code) {
       return this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
