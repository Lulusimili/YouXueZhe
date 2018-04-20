package com.example.administrator.youxuezhe.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.fragment.MainPageFragment.page_about_play.AboutPlayFragment;
import com.example.administrator.youxuezhe.fragment.MainPageFragment.page_knowledge_skill_service.KnowledgeSkillServiceFragment;
import com.example.administrator.youxuezhe.fragment.MainPageFragment.UserPageFragment;
import com.example.administrator.youxuezhe.fragment.MainPageFragment.SeekHelpFragment;
import com.example.administrator.youxuezhe.ui.BottomBar;
import com.example.administrator.youxuezhe.ui.PopupMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面容器Activity
 */

public class MainPageActivity extends AppCompatActivity {
    private List<Fragment> fragments;
    private Fragment mFragment=null;
    private BottomBar bottomBar;
    private ImageView mCenterImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initView();
        mFragment=fragments.get(0);
        showFragment(mFragment);
        setOnClickFrameLayoutListener();
    }

    private void initView(){
        fragments=new ArrayList<>();
        fragments.add(KnowledgeSkillServiceFragment.newInstance());
        fragments.add(SeekHelpFragment.newInstance());
        fragments.add(AboutPlayFragment.newInstance());
        fragments.add(UserPageFragment.newInstance());
        bottomBar=(BottomBar)findViewById(R.id.bottomBar);
        mCenterImage = (ImageView)findViewById (R.id.center_img);
    }

    /**
     * 设置底部选择按钮监听
     */
    private void setOnClickFrameLayoutListener(){
        bottomBar.setOnBottomBarOnclick(new BottomBar.OnBottomBarClick() {
            @Override
            public void onFirstClick() {
                mFragment=fragments.get(0);
                showFragment(mFragment);
            }

            @Override
            public void onSecondClick() {
                mFragment=fragments.get(1);
                showFragment(mFragment);

            }

            @Override
            public void onThirdClick() {
                mFragment=fragments.get(2);
                showFragment(mFragment);
            }

            @Override
            public void onFourthClick() {
                mFragment=fragments.get(3);
                showFragment(mFragment);
            }

            @Override
            public void onCenterClick() {
                PopupMenu.getInstance().show(MainPageActivity.this, mCenterImage);
            }
        });

    }

    /**
     * 选择显示的fragment
     * @param fragment
     */
    private void showFragment(Fragment fragment) {
        if(fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_container,fragment)
                    .commit();
        }
    }
}
