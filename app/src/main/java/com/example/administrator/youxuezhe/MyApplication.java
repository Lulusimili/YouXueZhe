package com.example.administrator.youxuezhe;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 获取全局Context
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
