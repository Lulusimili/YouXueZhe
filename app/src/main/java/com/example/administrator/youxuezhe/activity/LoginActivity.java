package com.example.administrator.youxuezhe.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.youxuezhe.R;

import java.net.URLEncoder;

/**
 * 登陆界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button toRegisterButton;
    Button loginButton;
    EditText editAccount;
    EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        loginButton.setOnClickListener(this);
        toRegisterButton.setOnClickListener(this);

//        String password = pwd.getText().toString().trim();
//        String nam=name.getText().toString().trim();
//        String sch=school.getText().toString().trim();
//        String gra=grade.getText().toString().trim();
//        String maj=major.getText().toString().trim();
//        String strnam= URLEncoder.encode(nam,"utf-8");
//        String strsch=URLEncoder.encode(sch,"utf-8");
//        String strgra=URLEncoder.encode(gra,"utf-8");
//        String strmaj= URLEncoder.encode(maj,"utf-8");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                login();
                break;
            case R.id.register_button:
                register();
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
     * 登陆请求
     */
    private void login(){
        //网络请求
        Intent intent=new Intent(this,MainPageActivity.class);
        startActivity(intent);
    }
    /**
     * 注册——跳转注册页面
     */
    private void register(){
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
