package com.example.administrator.youxuezhe.activity.modular_about_play;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.bean.AboutPlayEntity;
import com.example.administrator.youxuezhe.bean.ApplyResponse;
import com.example.administrator.youxuezhe.ui.RoundImageView;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;
import com.example.administrator.youxuezhe.utils.MyUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

import static com.example.administrator.youxuezhe.utils.MyUtils.showToast;

public class AboutPlayDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBackButton;
    private ImageView mImage;
    /**
     * 标       题：
     */
    private TextView mTitle;
    /**
     * 开始时间:
     */
    private TextView mBeginTime;
    /**
     * 发布时间：
     */
    private TextView mReleaseTime;
    /**
     * 有效时间至：
     */
    private TextView mEndTime;
    private TextView mContentText;
    private RoundImageView mPictureShow;
    private TextView mShowText;
    /**
     * 私信发布者
     */
    private Button mButtonPrivateLetter;
    /**
     * 申请加入
     */
    private Button mButtonJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_play_detail);
        initView();
        test();
    }

    private void initView() {
        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(this);
        mImage = (ImageView) findViewById(R.id.image);
        mTitle = (TextView) findViewById(R.id.title);
        mBeginTime = (TextView) findViewById(R.id.begin_time);
        mReleaseTime = (TextView) findViewById(R.id.release_time);
        mEndTime = (TextView) findViewById(R.id.end_time);
        mContentText = (TextView) findViewById(R.id.content_text);
        mPictureShow = (RoundImageView) findViewById(R.id.picture_show);
        mShowText = (TextView) findViewById(R.id.show_text);
        mButtonPrivateLetter = (Button) findViewById(R.id.button_private_letter);
        mButtonPrivateLetter.setOnClickListener(this);
        mButtonJoin = (Button) findViewById(R.id.button_join);
        mButtonJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.button_private_letter:
                showToast("你好");
                break;
            case R.id.button_join:
//                join("1", MyUrlManager.APPLY_FOR_ACTIVITY_URL);
                showToast("嘿嘿");
                break;
            default:
                break;
        }
    }

    private void join(final String id, String url){
        final RequestBody requestBody=new FormBody.Builder()
                .add("id",id)
                .build();
        HttUtil.postOkHttp(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToast("加入失败了...");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 ApplyResponse applyResponse= HandleJson.handleApplyResponse(response.body().string());
                if(applyResponse.getCode().equals("0")){
                    showToast("加入成功！");
                }else if(applyResponse.getCode().equals("1")){
                    showToast("加入失败了...");
                }

            }
        });
    }
    private void test(){
        /**
         * People people = (People) this.getIntent().getSerializableExtra("people");
         String strData = people.getName() + " " + people.getAge();
         Toast.makeText(getApplication(),strData, Toast.LENGTH_SHORT).show();
         */
        Intent intent=getIntent();
        AboutPlayEntity aboutPlayEntity=(AboutPlayEntity) intent.getSerializableExtra("aboutPlayEntity");
        mBeginTime.setText(aboutPlayEntity.getStartTime());
        mContentText.setText(aboutPlayEntity.getContent());
        mEndTime.setText(aboutPlayEntity.getDeadline());
        mReleaseTime.setText(aboutPlayEntity.getPublishTime());
        mTitle.setText(aboutPlayEntity.getTitle());
        Glide.with(getApplicationContext())
                .load(aboutPlayEntity.getPictureUrl())
                .placeholder(R.drawable.load_error)
                .into(mImage);
    }
}
