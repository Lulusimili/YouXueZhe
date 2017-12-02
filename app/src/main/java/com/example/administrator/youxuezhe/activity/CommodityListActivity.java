package com.example.administrator.youxuezhe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;

/**
 * 商品列表页面
 */
public class CommodityListActivity extends AppCompatActivity implements View.OnClickListener{
    private Button backButton;
    private TextView commodityTitle;
    private RecyclerView commodityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_list);
        initView();
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      finish();
    }

    /**
     * 初始化
     */
    private void initView(){
        backButton=(Button)findViewById(R.id.back_button);
        commodityTitle=(TextView)findViewById(R.id.commodity_title_text);
        commodityList=(RecyclerView)findViewById(R.id.commodity_list);
    }
}
