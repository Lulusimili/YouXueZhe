package com.example.administrator.youxuezhe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;

/**
 * needing 列表页面
 */
public class NeedingActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mBackButton;
    private TextView mNeedingTitleText;
    private RecyclerView mNeedingList;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needing);
        initView();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(this);
        mNeedingTitleText = (TextView) findViewById(R.id.needing_title_text);
        mNeedingList = (RecyclerView) findViewById(R.id.needing_list);
        setTitle();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 设置标题
     */
    private void setTitle(){
        intent=getIntent();
        mNeedingTitleText.setText(intent.getStringExtra("from"));
    }
}
