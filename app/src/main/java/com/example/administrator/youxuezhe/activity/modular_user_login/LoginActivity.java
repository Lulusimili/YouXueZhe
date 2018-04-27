package com.example.administrator.youxuezhe.activity.modular_user_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.activity.MainPageActivity;
import com.example.administrator.youxuezhe.bean.MyRequestBody;
import com.example.administrator.youxuezhe.cache.SaveUser;
import com.example.administrator.youxuezhe.cache.UserCache;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;

import java.io.IOException;

import cn.leancloud.chatkit.LCChatKitUser;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static cn.leancloud.chatkitapplication.CustomUserProvider.partUsers;
import static com.example.administrator.youxuezhe.activity.modular_user_login.LoginActivity.account;
import static com.example.administrator.youxuezhe.utils.MyUtils.showToast;

/**
 * 登陆界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button toRegisterButton;
    private Button loginButton;
    private EditText editAccount;
    private EditText editPassword;
    public static String account;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        loginButton.setOnClickListener(this);
        toRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        account = editAccount.getText().toString().trim();
        password = editPassword.getText().toString().trim();
        switch (view.getId()){
            case R.id.login_button:
                if(account.equals("")){
                    showToast("账户不能为空！  ");
                }else if(password.equals("")){
                    showToast("密码不能为空！");
                }else {
                    login();
                }
                break;
            case R.id.to_register_button:
                toRegister();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化控件
     */
    private void initView(){
        toRegisterButton=(Button)findViewById(R.id.to_register_button);
        editAccount=(EditText)findViewById(R.id.edit_account);
        editPassword=(EditText)findViewById(R.id.edit_password);
        loginButton=(Button)findViewById(R.id.login_button);
    }

    /**
     * 登陆请求并获取cookie
     */
    private void login() {
        //网络请求
        String account = editAccount.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        RequestBody formBody = new FormBody.Builder()
                .add("userName",account)
                .add("userPassword", password)
                .build();
        partUsers.add(new LCChatKitUser(account,account, "http://www.avatarsdb.com/avatars/jerry.jpg"));
        HttUtil.loginGetCookie(formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyRequestBody myRequestBody;
                myRequestBody = HandleJson.handleRequest(response.body().string());
                handleResponse(myRequestBody.getCode());
            }
        });
    }

    /**
     * 子线程跳转操作
     * @param code
     */

    private void handleResponse(final String code){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (code) {
                    case "0":
//                        showToast("登陆成功");
                        /**
                         * 缓存用户信息用来刷新cookie
                         */
                        UserCache.setAccount(account);
                        UserCache.setPassword(password);
                        Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case "40000":
                        showToast("账户不存在");
                        break;
                    case "40001":
                        showToast("密码错误");
                    default:
                        break;
                }
            }
        });
    }
    /**
     * 注册——跳转注册页面
     */
    private void toRegister(){
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
