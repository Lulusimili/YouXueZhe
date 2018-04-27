package com.example.administrator.youxuezhe.activity.modular_user_login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private TextView sendCode;
    private CountTimer countTimer;
    private TextView to_register;
    //    private EventHandler handler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

//        handler = new EventHandler() {
//            public void afterEvent(int event, int result, Object data) {
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    //回调完成
//                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                        //提交验证码成功
//
//                       Intent intent = new Intent(RegisterActivity.this,RegisterConfirm.class);
//                       startActivity(intent);
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//            }
//        };
//        SMSSDK.registerEventHandler(handler);
//    }

    public void initView(){
        to_register=(TextView)findViewById(R.id.to_register_button);
        typeCode=(EditText)findViewById(R.id.type_code);
        typePhonenumber=(EditText)findViewById(R.id.phonenumber);
        countTimer=new CountTimer(60000,1000,sendCode);
        sendCode=(TextView)findViewById(R.id.sendcode);
        sendCode.setOnClickListener(this);
        to_register.setOnClickListener(this);
    }
    public void onClick(View v) {
        phonenumber= typePhonenumber.getText().toString();
        String checkcode = typeCode.getText().toString();
        switch (v.getId()) {
            //发送验证码
            case R.id.sendcode:
                if (TextUtils.isEmpty(phonenumber)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_LONG).show();

                } else {
                    RequestBody formBoday=new FormBody.Builder()
                            .add("phonenumber",phonenumber)
                            .build();
                    register(MyUrlManager.MY_REGISTER_GETCHECKCODE,formBoday);
                    countTimer.start();
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
                register(MyUrlManager.MY_REGISTER_CHECKCODE,formBody);
        }
//        Log.i("ss",phonenumber+","+checkcode);
//        SMSSDK.submitVerificationCode("86",phonenumber,checkcode);
    }

    private void register(String url,RequestBody formBody) {
        HttUtil.postOkHttp(url, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyRequestBody myRequestBody = HandleJson.handleRequest(response.body().string());
                handleReponse(myRequestBody.getCode());
            }
        });
    }
    private void handleReponse(final String code){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch(code){
                    case "0":
                        Toast.makeText(RegisterActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                        break;
                    case "80000":
                        Toast.makeText(RegisterActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,MainPageActivity.class);
                        startActivity(intent);
                        break;
                    case "80001":
                        Toast.makeText(RegisterActivity.this,"请完善注册信息",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(RegisterActivity.this,RegisterConfirmActivity.class);
                        startActivity(intent2);
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
    //实现发送验证码后倒计时的类
    class CountTimer extends CountDownTimer {
        private TextView sendcode;
        public CountTimer(long millisFuture,long countDownInternal,TextView sendcode){
            super(millisFuture,countDownInternal);
            this.sendcode=sendcode;
        }
        public void onTick(long millisFuture){
            sendCode.setText(millisFuture/1000+"s后重新发送");
            sendCode.setClickable(false);
            sendCode.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this,android.R.color.holo_blue_dark));
            sendCode.setTextColor(ContextCompat.getColor(RegisterActivity.this,android.R.color.black));
            sendCode.setTextSize(16);
//            sendcode.setBackgroundResource(R.drawable.shape2);


        }
        public void onFinish(){
            sendCode.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this,android.R.color.holo_blue_light));
            sendCode.setTextColor(ContextCompat.getColor(RegisterActivity.this,android.R.color.white));
            sendCode.setText("重新发送");
            sendCode.setTextSize(16);
            sendCode.setClickable(true);
//            sendcode.setBackgroundResource(R.drawable.shape2);
        }
    }
}

