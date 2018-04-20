package com.example.administrator.youxuezhe.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.astuetz.PagerSlidingTabStrip;
import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.adapter.FragmentAdapter;
import com.example.administrator.youxuezhe.fragment.CourseAndServiceFragment;
import com.example.administrator.youxuezhe.fragment.MyAboutPlayFragment;
import com.example.administrator.youxuezhe.fragment.MySeekHelpFragment;

import java.util.ArrayList;
import java.util.List;

public class MyPublishManageActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager;
    private TabLayout tab;
    private FragmentAdapter fragmentAdapter ;
    private Button backButton;
    private List<String> titles;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_publish_manage);
        initView();
        viewPager.setAdapter(fragmentAdapter);
        tab.setupWithViewPager(viewPager);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       finish();
    }

    private void initView(){
        backButton=(Button)findViewById(R.id.back_button);
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        tab=(TabLayout) findViewById(R.id.tab);
        titles=new ArrayList<>();
        fragments=new ArrayList<>();
        titles.add("课程/服务");
        titles.add("求助");
        titles.add("约玩");
        fragments.add(CourseAndServiceFragment.newInstance());
        fragments.add(MyAboutPlayFragment.newInstance());
        fragments.add(MySeekHelpFragment.newInstance());
        fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),fragments,titles);
    }
}
