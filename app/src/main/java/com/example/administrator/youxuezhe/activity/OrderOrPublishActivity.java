package com.example.administrator.youxuezhe.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.bean.CommodityOrderInfo;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private TextView mShowText;
    private CommodityOrderInfo info;
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
        getDetail(MyUrlManager.MY_ORDER_INFO_URL);
    }

    private void initView() {
        mPublishTitleText = (TextView) findViewById(R.id.publish_title_text);
        mPublishPriceText = (TextView) findViewById(R.id.publish_price_text);
        mPublishContentText = (TextView) findViewById(R.id.publish_content_text);
        mTimeText = (TextView) findViewById(R.id.time_text);
        mPublishAddressText = (TextView) findViewById(R.id.publish_address_text);
        mPictureShow = (ImageView) findViewById(R.id.picture_show);
        mShowText = (TextView) findViewById(R.id.show_text);
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
     * 根据上一活动决定显示的标题
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

    /**
     * 获取详情
     * @param url
     */
    private void getDetail(final String url){
        Intent intent=getIntent();
        int pid=intent.getIntExtra("pid",0);
        RequestBody requestBody=new FormBody.Builder()
                .add("pid",String.valueOf(pid))
                .build();
        HttUtil.postOkHttp(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                if(isTimeOut(response.body().string())){
//                    HttUtil.refrashCookie();
//                    getDetail(url);
//                }
                info= HandleJson.handleDetailResponse(response.body().string());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       showDetail(info);
                    }
                });
            }
        });
    }

    /**
     * 显示详情
     * @param info
     */
    private void showDetail(CommodityOrderInfo info){
        mPublishAddressText.setText("地点："+info.getPaddress());
        Glide
                .with(this)
                .load(info.getpImageUrl())
                .into(mPictureShow);
        mTimeText.setText("时间："+info.getPtime());
        mPublishContentText.setText("内容："+info.getPcontent());
        mPublishPriceText.setText("价格："+String.valueOf(info.getPprice())+"元/小时");
        mPublishTitleText.setText("标题："+info.getPtitle());
        mShowText.setText(info.getPshow());
    }
}
