package com.example.administrator.youxuezhe.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.bean.MyRequestBody;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.BatchUpdateException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.administrator.youxuezhe.utils.MyUtils.showToast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    Button registerButton;
    EditText editAccount;
    EditText editPassword;
    EditText editName;
    EditText editMajor;
    EditText editSchool;
    EditText editGrade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_register);
         initView();
         registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.register_button){
            //注册请求
            register(MyUrlManager.MY_REGISTER_URL);
        }
    }

    /**
     * 提交注册请求
     */
    private void register(String url) {
        String email = editAccount.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String name = editName.getText().toString().trim();
        String school = editSchool.getText().toString().trim();
        String grade = editGrade.getText().toString().trim();
        String major = editMajor.getText().toString().trim();
        if (email.equals("")){
            showToast("邮箱不能为空！");
        } else if(password.equals("")){
            showToast("密码不能为空！");
        }else if(name.equals("")){
            showToast("名字不能为空！");
        }else if(school.equals("")){
            showToast("学校不能为空！");
        }else if(grade.equals("")){
            showToast("年级不能为空！");
        } else if(major.equals("")){
            showToast("专业不能为空！");
        }
        JSONObject userObject = new JSONObject();
        JSONObject userJson = new JSONObject();
        try {
            userObject.put("userEmail", email);
            userObject.put("userName", name);
            userObject.put("userPassword", password);
            userObject.put("userSchool", school);
            userObject.put("userGrade", grade);
            userObject.put("userMajor", major);
            userJson.put("User", userObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content = String.valueOf(userJson);
        HttUtil.postOkhttp(url, content, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyRequestBody myRequestBody= HandleJson.handleReuest(response.body().string());
                handleResponse(myRequestBody.getCode());
            }
        });

    }

    private void handleResponse(final String code){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (code) {
                    case "0":
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        showToast("注册成功");
                        finish();
                        startActivity(intent);
                        break;
                    case "50000":
                        showToast("用户名已存在");
                        break;
                    case "50001":
                        showToast("用户名格式不正确（不超过15各字符）");
                        break;
                    case "50002":
                        showToast("邮箱已注册");
                        break;
                    case "50003":
                        showToast("邮箱格式不正确");
                }
            }
        });
    }

    /**
     * 初始化
     */
    private void initView(){
        registerButton=(Button)findViewById(R.id.register_button);
        editAccount=(EditText)findViewById(R.id.edit_account);
        editPassword=(EditText)findViewById(R.id.edit_password);
        editName=(EditText)findViewById(R.id.edit_name);
        editSchool=(EditText)findViewById(R.id.edit_school);
        editMajor=(EditText)findViewById(R.id.edit_major);
        editGrade=(EditText)findViewById(R.id.edit_grade);
    }
}
