package cn.leancloud.chatkitapplication;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.avos.avoscloud.*;
import com.avos.avoscloud.im.v2.*;
import com.avos.avoscloud.im.v2.callback.*;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.im.v2.AVIMOptions;
import com.avos.avoscloud.AVOSCloud.*;

import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by wli on 16/2/24.
 */
public class App extends Application {

  private final String APP_ID = "D2bLczSifWBDqRYqXvY6Tn2i-gzGzoHsz";
  private final String APP_KEY = "rAUesdqr9DbkoRplzrMmMFOm";

  @Override
  public void onCreate() {
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
