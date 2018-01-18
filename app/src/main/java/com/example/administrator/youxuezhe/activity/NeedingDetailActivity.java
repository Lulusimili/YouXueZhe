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
import com.example.administrator.youxuezhe.bean.needing;
import com.example.administrator.youxuezhe.bean.needingInfo;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.youxuezhe.utils.MyUtils.showToast;

/**
 * 需求详情页面
 */
public class NeedingDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView needing_detail_title;
    private TextView needing_detail_text;
    private TextView needing_detail_price;
    private ImageView needing_picture;
    private Button mBuyButton;
    private Button mBackButton;
    private needingInfo info;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needing_detail);
        initView();
        getDetail(MyUrlManager.MY_NEEDING_DETAIL_URL);
    }
    private void initView(){
        needing_detail_title=(TextView)findViewById(R.id.needing_detail_title);
        needing_detail_text=(TextView)findViewById(R.id.needing_detail_text);
        needing_detail_price=(TextView)findViewById(R.id.needing_detail_price);
        needing_picture=(ImageView)findViewById(R.id.needing_picture);
        mBuyButton=(Button)findViewById(R.id.needing_detail_buybutton);
//        mBackButton=(Button)findViewById(R.id.back_button);
//        mBackButton.setOnClickListener(this);
        mBuyButton.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.needing_detail_buybutton:
                showToast("下单成功！");
                break;
            case R.id.back_button:
                finish();
            default:
                break;
        }
    }
    /**
     * 获取详情
     */
    private void getDetail(final String url){
        Intent intent=getIntent();
        String needId=null;
        needId=intent.getStringExtra("needId");

        RequestBody requestBody=new FormBody.Builder()
                .add("needId",String.valueOf(needId))
                .build();
        HttUtil.postOkHttp(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                info= HandleJson.handleNeedingDetailReponse(response.body().string());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showDetail(info);
                    }
                });

            }
        });
    }


    private void showDetail(needingInfo info){
        needing_detail_text.setText(info.getNeedDetail());
        needing_detail_title.setText("需求名称"+info.getNeedName());
        needing_detail_price.setText("价格"+info.getPrice());
        Glide.with(this)
                .load(info.getImgUrl())
                .into(needing_picture);
    }



}
