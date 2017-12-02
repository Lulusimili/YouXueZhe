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

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.activity.MyPublishActivity;
import com.example.administrator.youxuezhe.activity.OrderManagementActivity;
import com.example.administrator.youxuezhe.adapter.CarouselPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人信息栏
 */

public class MyPageFragment extends Fragment implements View.OnClickListener{
    private TextView nameText;
    private TextView schoolText;
    private TextView gradeText;
    private TextView introuceText;
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
        introuceText=view.findViewById(R.id.introduce_text);
        myOrderManageButton=view.findViewById(R.id.my_order_manage_button);
        myPublishButton=view.findViewById(R.id.my_publish_button);
    }

    /**
     * 获取个人信息
     */

    private void getUsersMessage(){

    }
}
