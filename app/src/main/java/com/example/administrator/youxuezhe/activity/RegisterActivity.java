package com.example.administrator.youxuezhe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.youxuezhe.R;

import java.net.URLEncoder;
import java.sql.BatchUpdateException;

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
//        String password = pwd.getText().toString().trim();
//        String nam=name.getText().toString().trim();
//        String sch=school.getText().toString().trim();
//        String gra=grade.getText().toString().trim();
//        String maj=major.getText().toString().trim();
//        String strnam= URLEncoder.encode(nam,"utf-8");
//        String strsch=URLEncoder.encode(sch,"utf-8");
//        String strgra=URLEncoder.encode(gra,"utf-8");
//        String strmaj=URLEncoder.encode(maj,"utf-8");

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.register_button){
            //注册请求
            register();
            finish();
        }
    }

    /**
     * 提交注册请求
     */
    private void register(){

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
