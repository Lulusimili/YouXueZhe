package com.example.administrator.youxuezhe.ui.float_edit_text_view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.utils.MyUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloatEditTextActivity extends Activity {

    /**
     * 输入内容:
     */
    private EditText mEditContent;
    private String content;
    /**
     * 确认
     */
    private TextView mTvSubmit;

    private static EditCallBack mEditCallback;

    private boolean isCheck;
    public static String NUM_TYPE="NUM";
    public static String MULTI_TYPE="MULTI";

    /**
     * 数字输入
     */
    private static boolean isNumOnly;
    /**
     * 混合输入
     */
    private static boolean isMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_float_edit_text);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        initView();
        onClick();

    }

    private void initView() {
        mEditContent = (EditText) findViewById(R.id.edit_content);
        mTvSubmit = (TextView) findViewById(R.id.tv_submit);
    }

    /**
     * 点击事件
     */
    private void onClick(){

        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=mEditContent.getText().toString();
                if(isNumOnly){
                    Pattern pattern = Pattern.compile("^[1-9]\\d*$");
                    Matcher matcher = pattern.matcher(content);
                    if (!matcher.matches()) {
                        MyUtils.showToast("只能输入大于0的整数!");
                        return;
                    }else {
                        mEditCallback.onSubmit(content);
                        finish();
                    }
                }
                else if(isMulti){
                    mEditCallback.onSubmit(content);
                    finish();
                }
            }
        });
    }

    /**
     * 弹出输入框
     * @param context
     * @param editCallBack
     */
    public static void openEditor(Context context,String type,EditCallBack editCallBack){
        Intent intent = new Intent(context, FloatEditTextActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        mEditCallback = editCallBack;
        if (type.equals(MULTI_TYPE)){
            isMulti=true;
            isNumOnly=false;
        }else if(type.equals(NUM_TYPE)){
            isNumOnly=true;
            isMulti=false;
        }
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEditCallback = null;
    }
}
