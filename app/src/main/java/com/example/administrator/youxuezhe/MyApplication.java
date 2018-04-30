package com.example.administrator.youxuezhe;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMClient;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkitapplication.CustomUserProvider;
import cn.leancloud.chatkitapplication.MainActivity;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 获取全局Context
 */

public class MyApplication extends Application {
    private static Context context;
    private final String APP_ID = "D2bLczSifWBDqRYqXvY6Tn2i-gzGzoHsz";
    private final String APP_KEY = "rAUesdqr9DbkoRplzrMmMFOm";

    public static Context getContext(){
        return context;
    }

    //初始化LeanCloud SDK
    @Override
    public void onCreate() {
        context=getApplicationContext();
        AVOSCloud.setDebugLogEnabled(true);
        super.onCreate();
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        AVOSCloud.setDebugLogEnabled(true);
        LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
        AVIMClient.setAutoOpen(true);
        PushService.setDefaultPushCallback(this, MainActivity.class);
        PushService.setAutoWakeUp(true);
        PushService.setDefaultChannelId(this, "default");

        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // 保存成功
                    String installationId = AVInstallation.getCurrentInstallation().getInstallationId();
                    System.out.println("---  " + installationId);
                } else {
                    // 保存失败，输出错误信息
                    System.out.println("failed to save installation.");
                }
            }
        });
    }
}
