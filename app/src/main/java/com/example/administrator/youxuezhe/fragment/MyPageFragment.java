package com.example.administrator.youxuezhe.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.activity.MyPublishActivity;
import com.example.administrator.youxuezhe.activity.OrderManagementActivity;
import com.example.administrator.youxuezhe.adapter.CarouselPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人信息页
 */

public class MyPageFragment extends Fragment implements View.OnClickListener{
    private TextView nameText;
    private TextView schoolText;
    private TextView gradeText;
    private TextView introduceText;
    private ImageView myHeaderImage;
    private Button myOrderManageButton;
    private Button myPublishButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        myOrderManageButton.setOnClickListener(this);
        myPublishButton.setOnClickListener(this);
        getUsersMessage();
        test();
    }
        @Override
        public void onClick (View view){
            Intent intent;
            switch (view.getId()) {
                case R.id.my_order_manage_button:
                    intent = new Intent(getContext(), OrderManagementActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_publish_button:
                    intent = new Intent(getContext(), MyPublishActivity.class);
                    startActivity(intent);
                    break;
            }
        }

    /**
     * 初始化
     * @param view
     */

    private void initView(View view){
        myHeaderImage=view.findViewById(R.id.my_header);
        nameText=view.findViewById(R.id.name_text);
        schoolText=view.findViewById(R.id.school_text);
        gradeText=view.findViewById(R.id.grade_text);
        introduceText=view.findViewById(R.id.introduce_text);
        myOrderManageButton=view.findViewById(R.id.my_order_manage_button);
        myPublishButton=view.findViewById(R.id.my_publish_button);
    }

    /**
     * 获取个人信息
     */

    private void getUsersMessage(){

    }

    /**
     * 获取个人信息测试
     */
    private void test(){
        nameText.setText("name:码云");
        schoolText.setText("school:UESTC");
        gradeText.setText("grade 大二");
        introduceText.setText("个人介绍：爱学习，爱写代码");
        Glide.with(getContext())
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512313202316&di=8f7622b442d4e6584b2149b5efadcc1c&imgtype=0&src=http%3A%2F%2Fimg06.tooopen.com%2Fimages%2F20160915%2Ftooopen_sy_178926047887.jpg")
                .into(myHeaderImage);
    }

}
