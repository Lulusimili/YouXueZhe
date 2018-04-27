package cn.leancloud.chatkitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;

import static cn.leancloud.chatkitapplication.CustomUserProvider.partUsers;


/**
 * 登陆页面
 */
public class LoginActivity extends AppCompatActivity {

  protected EditText nameView;
  protected Button loginButton;
  public static String userName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    nameView = (EditText) findViewById(R.id.activity_login_et_username);
    loginButton = (Button) findViewById(R.id.activity_login_btn_login);
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onLoginClick();
      }
    });
  }

  public void onLoginClick() {
    userName = nameView.getText().toString();
    if (TextUtils.isEmpty(userName.trim())) {
      Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
      return;
    }
    partUsers.add(new LCChatKitUser(userName, userName, "http://www.avatarsdb.com/avatars/jerry.jpg"));
    Log.d("111",partUsers.toString());

    LCChatKit.getInstance().open(userName, new AVIMClientCallback() {
      @Override
      public void done(AVIMClient avimClient, AVIMException e) {
        if (null == e) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
          Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
      }
    });
  }
}
