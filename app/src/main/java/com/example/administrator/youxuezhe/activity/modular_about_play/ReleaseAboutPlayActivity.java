package com.example.administrator.youxuezhe.activity.modular_about_play;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.ui.RoundImageView;
import com.example.administrator.youxuezhe.ui.date_picker.CustomDatePicker;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;
import com.example.administrator.youxuezhe.utils.MyUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.youxuezhe.R.id.currentDate;

public class ReleaseAboutPlayActivity extends AppCompatActivity {

    private Button mBackButton;
    /**
     * 详细内容
     */
    private TextView mCommodityTitleText;
    private Button mButtonPublish;
    private RoundImageView mImgAboutPlay;
    /**
     * 需求名称：
     */
    private TextView mNeedName;
    private EditText mEdtNeedName;

    /**
     * 开始    时间：
     */
    private TextView mBegin;
    private TextView mBeginTime;
    /**
     * 有效时间至：
     */
    private TextView mEnd;
    private TextView mEndTime;
    private EditText mEdtDetail;

    private CustomDatePicker datePicker;
    private String dateNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_about_play);
        initView();
        setOnClick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void initView() {
        mBackButton = (Button) findViewById(R.id.back_button);
        mCommodityTitleText = (TextView) findViewById(R.id.commodity_title_text);
        mButtonPublish = (Button) findViewById(R.id.button_publish);
        mImgAboutPlay = (RoundImageView) findViewById(R.id.img_about_play);
        mNeedName = (TextView) findViewById(R.id.need_title);
        mEdtNeedName = (EditText) findViewById(R.id.edt_need_title);
        mBegin = (TextView) findViewById(R.id.begin);
        mBeginTime = (TextView) findViewById(R.id.begin_time);
        mEnd = (TextView) findViewById(R.id.end);
        mEndTime = (TextView) findViewById(R.id.end_time);
        mEdtDetail = (EditText) findViewById(R.id.edt_detail);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        dateNow = sdf.format(new Date());
    }

    private void setOnClick(){
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mButtonPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File file;
//                release(MyUrlManager.RELEASE_ABOUT_PLAY_URL,);
            }
        });
        mImgAboutPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBeginTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker(mBeginTime);
                if(!mBeginTime.getText().toString().equals("")){
                    datePicker.show(mBeginTime.getText().toString());
                }else {
                    datePicker.show(dateNow);
                }
            }
        });
        mEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker(mEndTime);
                if(!mEndTime.getText().toString().equals("")){
                    datePicker.show(mEndTime.getText().toString());
                }else {
                    datePicker.show(dateNow);
                }

            }
        });
    }
    private void initDatePicker(final TextView textView){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            String now = sdf.format(new Date());

            datePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
               // mBeginTime.setText(time);
                textView.setText(time);
            }
        }, now,  "2030-01-01 00:00");
             datePicker.setIsLoop(true);
             datePicker.showSpecificTime(true);
    }

    private void release(String url, File file, Map<String,String> releaseInfo){
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("headPhotoFile",file.getName(),fileBody)
                .addFormDataPart("content",releaseInfo.get("content"))
                .addFormDataPart("title",releaseInfo.get("title"))
                .addFormDataPart("label",releaseInfo.get("label"))
                .addFormDataPart("expectedNum",releaseInfo.get("expectedNum"))
                .build();
        HttUtil.postOkHttp(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


            }
        });


    }

}
