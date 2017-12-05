package com.example.administrator.youxuezhe.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;

import static com.example.administrator.youxuezhe.utils.MyUtils.showToast;

/**
 * 订单或商品详情页
 */
public class OrderOrPublishActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mPublishTitleText;
    private TextView mPublishPriceText;
    private TextView mPublishContentText;
    private TextView mTimeText;
    private TextView mPublishAddressText;
    private ImageView mPictureShow;
    private TextView mTextView;
    /**
     * 删除此条信息
     */
    private Button mDeleteButton;
    /**
     * 确认购买
     */
    private Button mBuyButton;
    private Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_or_publish);
        initView();
    }

    private void initView() {
        mPublishTitleText = (TextView) findViewById(R.id.publish_title_text);
        mPublishPriceText = (TextView) findViewById(R.id.publish_price_text);
        mPublishContentText = (TextView) findViewById(R.id.publish_content_text);
        mTimeText = (TextView) findViewById(R.id.time_text);
        mPublishAddressText = (TextView) findViewById(R.id.publish_address_text);
        mPictureShow = (ImageView) findViewById(R.id.picture_show);
        mTextView = (TextView) findViewById(R.id.textView);
        mDeleteButton = (Button) findViewById(R.id.delete_button);
        mBackButton=(Button)findViewById(R.id.back_button);
        mBuyButton = (Button) findViewById(R.id.buy_button);
        mBuyButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);
        fromActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_button:
                showToast("删除成功！");
                break;
            case R.id.buy_button:
                showToast("下单成功！");
                break;
            case R.id.back_button:
                finish();
            default:
                break;
        }
    }

    /**
     * 根据上一活动决定显示的界面
     */
    private void fromActivity(){
        Intent intent=getIntent();
        String from=intent.getStringExtra("from");
        if(from.equals("MyPublishActivity")){
            mBuyButton.setVisibility(View.GONE);
        }else if(from.equals("OrderManagementActivity")||from.equals("CommodityListActivity")){
            mDeleteButton.setVisibility(View.GONE);
        }
    }
}
