package com.example.administrator.youxuezhe.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.fragment.KnowledgeFragment;
import com.example.administrator.youxuezhe.fragment.MyPageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面容器Activity
 */
public class MainPageActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private List<Fragment> fragments;
    private RadioButton knowledgePageButton;
    private RadioButton myPageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initView();
        setOnRadioGroupChangedListener();
    }

    private void initView(){
        fragments=new ArrayList<>();
        fragments.add(new MyPageFragment());
        fragments.add(new KnowledgeFragment());

        knowledgePageButton=(RadioButton)findViewById(R.id.radio_button_knowledge);
        myPageButton=(RadioButton)findViewById(R.id.radio_button_my_page);
        knowledgePageButton.setChecked(true);
        myPageButton.setChecked(true);

        radioGroup=(RadioGroup)findViewById(R.id.radio_group_button);
    }

    private void setOnRadioGroupChangedListener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment mFragment=null;
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkId) {
                switch (checkId){
                    case R.id.radio_button_knowledge:
                        mFragment=fragments.get(0);
                        break;
                    case R.id.radio_button_my_page:
                        mFragment=fragments.get(1);
                        break;
                    default:
                        break;
                }
                if(mFragment!=null){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.home_container,mFragment)
                            .commit();
                }
            }
        });
    }
}
