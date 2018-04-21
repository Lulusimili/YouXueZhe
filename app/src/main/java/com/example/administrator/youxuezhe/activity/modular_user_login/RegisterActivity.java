package com.example.administrator.youxuezhe.activity.modular_user_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.activity.MainPageActivity;
import com.example.administrator.youxuezhe.activity.modular_user_login.LoginActivity;
import com.example.administrator.youxuezhe.bean.MyRequestBody;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.cacheColorHint;
import static android.R.attr.data;
import static com.example.administrator.youxuezhe.utils.MyUtils.showToast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    public static String phonenumber="";
    private EditText typePhonenumber;
    private EditText typeCode;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }


    public void initView(){
        findViewById(R.id.sendcode).setOnClickListener(this);
        findViewById(R.id.to_register_button).setOnClickListener(this);
        typeCode=(EditText)findViewById(R.id.type_code);
        typePhonenumber=(EditText)findViewById(R.id.phonenumber);
    }
    public void onClick(View v) {
         phonenumber= typePhonenumber.getText().toString();
        String checkcode = typeCode.getText().toString();
        switch (v.getId()) {
            //发送验证码
            case R.id.sendcode:
                if (TextUtils.isEmpty(phonenumber)) {
                    Toast.makeText(this, "phone can't be null", Toast.LENGTH_LONG).show();

                } else {
                    RequestBody formBoday=new FormBody.Builder()
                            .add("phonenumber",phonenumber)
                            .build();
                    register(MyUrlManager.MY_REGISTER_CHECKCODE,formBoday);
//                    SMSSDK.getVerificationCode("86", phonenumber, null);
                }
                break;
            //注册
            case R.id.to_register_button:
                if (TextUtils.isEmpty(checkcode)) {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_LONG).show();
                }
                RequestBody formBody=new FormBody.Builder()
                        .add("phonenumber",phonenumber)
                        .add("checkcode",checkcode)
                        .build();
                register(MyUrlManager.MY_REGISTER_PHONE_URL,formBody);
               // Intent intent = new Intent(RegisterActivity.this,RegisterConfirm.class);
                //startActivity(intent);
        }

    }

    private void register(String url,RequestBody formBody){
        HttUtil.postOkHttp(url, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyRequestBody myRequestBody=HandleJson.handleRequest(response.body().string());
                handleReponse(myRequestBody.getCode());
            }
        });

    }
    private void handleReponse(final String code){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch(code){
                    case "80000":
                        showToast("验证成功");
                        Intent intent = new Intent(RegisterActivity.this,MainPageActivity.class);
                        startActivity(intent);
                        break;
                    case "80001":
                        showToast("请完善注册信息");
                       // Intent intent2 = new Intent(RegisterActivity.this,RegisterConfirm.class);
                       // startActivity(intent2);
                        break;
                    case "80002":
                        showToast("发送失败");
                        break;
                    case "80003":
                        showToast("每小时最多发送五条信息");
                        break;
                    case "80004":
                        showToast("每天最多发送十条信息");
                    case "80005":
                        showToast("验证码输入有误");
                        break;
                }
            }
        });
    }
    }

