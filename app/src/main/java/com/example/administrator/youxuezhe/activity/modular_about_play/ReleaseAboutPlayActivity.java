package com.example.administrator.youxuezhe.activity.modular_about_play;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.ui.date_picker.CustomDatePicker;
import com.example.administrator.youxuezhe.ui.float_edit_text_view.EditCallBack;
import com.example.administrator.youxuezhe.ui.float_edit_text_view.FloatEditTextActivity;
import com.example.administrator.youxuezhe.ui.label_selector.LabelDialogFragment;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import imagepicker.ImagePicker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.youxuezhe.utils.MyUtils.showToast;

public class ReleaseAboutPlayActivity extends AppCompatActivity implements LabelDialogFragment.BottomDialogListener{

    private String selectLabel="";
    private CustomDatePicker datePicker;
    private String dateNow;
    private List<String> images;
    /**
     * 返回
     */
    private TextView mTvBack;
    /**
     * 发布
     */
    private TextView mTvRelease;
    /**
     * 输入点什么吧...
     */
    private EditText mEdtDetail;
    private ImageView mImgAboutPlay;
    /**
     * 活动标签
     */
    private TextView mTvReleaseLabel;
    private RelativeLayout mRvReleaseLabel;
    /**
     * 活动标题
     */
    private TextView mTvReleaseTitle;
    private RelativeLayout mRvTitle;
    /**
     * 期望参与人数
     */
    private TextView mTvExpectNum;
    private RelativeLayout mRvExpectNum;
    /**
     * 开始时间
     */
    private TextView mTvBeginTime;
    private RelativeLayout mRvBeginTime;
    /**
     * 结束时间
     */
    private TextView mTvEndTime;
    private RelativeLayout mRvEndTime;

    /**
     * 标签选择
     *
     */
    private List<String> labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        initLabels();
        initView();
        setOnClick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001) {
            if (data == null) return;
            ArrayList<String> filePath = data.getStringArrayListExtra("filePath");
            for (String str : filePath) {
                displayImage(str);
                images.clear();//将旧的图片删除
                images.add(str);
            }
        }
    }

    private void initView() {
        images = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        dateNow = sdf.format(new Date());
        mTvBack = (TextView) findViewById(R.id.tv_back);
        mTvRelease = (TextView) findViewById(R.id.tv_release);
        mEdtDetail = (EditText) findViewById(R.id.edt_detail);
        mImgAboutPlay = (ImageView) findViewById(R.id.img_about_play);
        mTvReleaseLabel = (TextView) findViewById(R.id.tv_release_label);
        mRvReleaseLabel = (RelativeLayout) findViewById(R.id.rv_release_label);
        mTvReleaseTitle = (TextView) findViewById(R.id.tv_release_title);
        mRvTitle = (RelativeLayout) findViewById(R.id.rv_title);
        mTvExpectNum = (TextView) findViewById(R.id.tv_expect_num);
        mRvExpectNum = (RelativeLayout) findViewById(R.id.rv_expect_num);
        mTvBeginTime = (TextView) findViewById(R.id.tv_begin_time);
        mRvBeginTime = (RelativeLayout) findViewById(R.id.rv_begin_time);
        mTvEndTime = (TextView) findViewById(R.id.tv_end_time);
        mRvEndTime = (RelativeLayout) findViewById(R.id.rv_end_time);
        replace();
    }

    private void initLabels(){
        labels=new ArrayList<>();
        labels.add("旅游");
        labels.add("运动");
        labels.add("桌游");
        labels.add("其它");
    }

    private void setOnClick() {
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                release(MyUrlManager.RELEASE_ABOUT_PLAY_URL, images.get(0), getReleaseInfo());
            }
        });
        mImgAboutPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        mRvBeginTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker(mTvBeginTime);
                if (!mTvBeginTime.getText().toString().equals("")) {
                    datePicker.show(mTvBeginTime.getText().toString());
                } else {
                    datePicker.show(dateNow);
                }
            }
        });
        mRvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker(mTvEndTime);
                if (!mTvEndTime.getText().toString().equals("")) {
                    datePicker.show(mTvEndTime.getText().toString());
                } else {
                    datePicker.show(dateNow);
                }

            }
        });

        mRvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatEditTextActivity
                        .openEditor(getApplicationContext(), FloatEditTextActivity.MULTI_TYPE,new EditCallBack() {
                    @Override
                    public void onSubmit(String content) {
                        mTvReleaseTitle.setText(content);
                    }
                        });

            }
        });

        mRvExpectNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatEditTextActivity.openEditor(ReleaseAboutPlayActivity.this, FloatEditTextActivity.NUM_TYPE, new EditCallBack() {
                    @Override
                    public void onSubmit(String content) {
                        mTvExpectNum.setText(content);
                    }
                });
            }
        });
        mTvReleaseLabel.setOnClickListener(new View.OnClickListener() {
            final LabelDialogFragment fragment=LabelDialogFragment.newInstance(labels,ReleaseAboutPlayActivity.this);
            @Override
            public void onClick(View v) {
                if (!fragment.isAdded()){
                    fragment.show(getFragmentManager(),"ReleaseActivity");
                }
            }
        });
    }


    /**
     * 选择标签
     * @param position
     */
    @Override
    public void onClick(int position) {
        mTvReleaseLabel.setText(labels.get(position));
    }

    /**
     * 初始化时间选择控件
     *
     * @param textView
     */
    private void initDatePicker(final TextView textView) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());

        datePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                // mBeginTime.setText(time);
                textView.setText(time);
            }
        }, now, "2030-01-01 00:00");
        datePicker.setIsLoop(true);
        datePicker.showSpecificTime(true);
    }

    /**
     * 获取发布信息
     *
     * @return
     */
    private Map<String, String> getReleaseInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("headPhotoFile", images.get(0));
        map.put("content", mEdtDetail.getText().toString());
        map.put("title", mTvReleaseTitle.getText().toString());
        map.put("label", selectLabel);
        map.put("expectedNum", mTvExpectNum.getText().toString());
        map.put("startTime", mTvBeginTime.getText().toString());
        map.put("endTime", mTvEndTime.getText().toString());
        return map;
    }

    /**
     * 发布
     *
     * @param url
     * @param filePath
     * @param releaseInfo
     */
    private void release(String url, String filePath, Map<String, String> releaseInfo) {
        File file = new File(filePath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("headPhotoFile", file.getName(), fileBody)
                .addFormDataPart("content", releaseInfo.get("content"))
                .addFormDataPart("title", releaseInfo.get("title"))
                .addFormDataPart("label", releaseInfo.get("label"))
                .addFormDataPart("expectedNum", releaseInfo.get("expectedNum"))
                .addFormDataPart("startTime", releaseInfo.get("startTime"))
                .addFormDataPart("endTime", releaseInfo.get("endTime"))
                .build();
        HttUtil.postOkHttp(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String code = HandleJson.handleApplyResponse(response.body().string()).getCode();
                if(code!=null) {
                    actionByCode(code);
                }
            }
        });

    }

    /**
     * 显示图片
     *
     * @param path
     */
    private void displayImage(String path) {
        if (path != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            mImgAboutPlay.setImageBitmap(bitmap);
        }
    }

    private void replace(){
        if (mImgAboutPlay.getDrawable()==null){
            mImgAboutPlay.setImageResource(R.drawable.mine_btn_plus);
        }
    }

    /**
     * 打开相册选择图片
     */
    private void selectImage() {
        ImagePicker.create()
                .single()
                .start(ReleaseAboutPlayActivity.this);
    }

    /**
     * 根据返回码执行相关操作
     *
     * @param code
     */
    private void actionByCode(final String code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (code) {
                    case "0":
                        showToast("发布成功！");
                        finish();
                        break;
                    default:
                        showToast("发布失败!");
                        break;
                }
            }
        });
    }

}
