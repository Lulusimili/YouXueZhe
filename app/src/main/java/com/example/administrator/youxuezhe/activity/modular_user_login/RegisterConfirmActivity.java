package com.example.administrator.youxuezhe.activity.modular_user_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.activity.MainPageActivity;
import com.example.administrator.youxuezhe.bean.MyRequestBody;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.youxuezhe.activity.modular_user_login.RegisterActivity.phonenumber;
import static com.example.administrator.youxuezhe.utils.MyUtils.showToast;

public class RegisterConfirmActivity extends AppCompatActivity implements View.OnClickListener{
    public String password;
    private EditText type_password;
    public  String account;
    public  String userSchool;
    private TextView accoun_type;
    private EditText type_school;
    private Button register_button;
    private ListView ls_school;
    private List<String> list=new ArrayList<String>();
    boolean isFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_confirm);
        initView();
//        setData();
//        setListeners();
    }
    private void initView(){
        accoun_type=(TextView)findViewById(R.id.edit_account);
        type_school=(EditText)findViewById(R.id.type_school);
        register_button=(Button)findViewById(R.id.register_confirm);
        register_button.setOnClickListener(this);
//        ls_school=(ListView)findViewById(R.id.list_school);
        type_password=(EditText)findViewById(R.id.type_password);
    }

    //    public void setData(){
//        initData();
//
//        mySchoolAdapter=new MySchoolAdapter(list,this,new FilterListener(){
//            public void getFilterData(List<String> list){
//                Log.d("TAG","回调成功");
//                Log.d("TAG",list.toString());
//                setItemClick(list);
//            }
//        });
//        ls_school.setAdapter(mySchoolAdapter);
//    }
//    private void setItemClick(final List<String> filter_lists){
//        ls_school.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                View v=ls_school.getChildAt(position);
//                TextView v_school=(TextView) v.findViewById(R.id.choose_school);
//                type_school.setText(v_school.getText().toString());
//                ls_school.setVisibility(View.INVISIBLE);
//            }
//        });
//    }
//    private void initData(){
//        list.add("电子科技大学");
//        list.add("西南财经大学");
//        list.add("西华大学");
//        list.add("成都理工大学");
//        list.add("四川大学");
//    }
//    private void setListeners() {
//        // 没有进行搜索的时候，也要添加对listView的item单击监听
//        setItemClick(list);
//
//        /**
//         * 对编辑框添加文本改变监听，搜索的具体功能在这里实现
//         * 很简单，文本该变的时候进行搜索。关键方法是重写的onTextChanged（）方法。
//         */
//        type_school.addTextChangedListener(new TextWatcher() {
//
//            /**
//             *
//             * 编辑框内容改变的时候会执行该方法
//             */
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//                // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
//                ls_school.setVisibility(View.VISIBLE);
//                if(mySchoolAdapter != null){
//                    mySchoolAdapter.getFilter().filter(s);
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//            }
//        });
//    }
    public void onClick(View view) {
        account = accoun_type.getText().toString();
        userSchool = type_school.getText().toString();
        password = type_password.getText().toString();
        if (account.equals("")) {
            showToast("账户不能为空！");
        } else if (password.equals("")) {
            showToast("密码不能为空！");
        } else if(password.equals("")){
            showToast("学校不能为空");
        }
        else{
            login();
        }
    }
    private void login() {
        //网络请求
        String account = accoun_type.getText().toString().trim();
        String password = type_password.getText().toString().trim();
        String school=type_school.getText().toString().trim();
        RequestBody formBody = new FormBody.Builder()
                .add("userName",account)
                .add("userPassword", password)
                .add("userSchool",school)
                .add("userPhone",phonenumber)
                .build();
        HttUtil.RegisterGetCookie(formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyRequestBody myRequestBody;
                myRequestBody = HandleJson.handleRequest(response.body().string());
                handleResponse(myRequestBody.getCode());
                Log.d("555",myRequestBody.getCode());
            }
        });
    }
    private void handleResponse(final String code){
        Log.d("111",code);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (code) {
                    case "50000":
                        Intent intent = new Intent(RegisterConfirmActivity.this, MainPageActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case "50001":
                        Toast.makeText(RegisterConfirmActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}