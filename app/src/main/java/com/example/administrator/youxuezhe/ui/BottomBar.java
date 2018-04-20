package com.example.administrator.youxuezhe.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.administrator.youxuezhe.R;


public class BottomBar extends LinearLayout {
    private Context mContext;
    private FrameLayout mFirst_bottom, mSecond_bottom, mThird_bottom, mFouth_bottom, mCenter_bottom;
    private OnBottomBarClick mOnBottomBarClick;
    public BottomBar(Context context){
        super(context);
        init(context);
    }
    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_bottom, this, true);
        //获取控件id
        initId();
        onBottomBarClick();
    }
    private void initId() {

        mFirst_bottom =(FrameLayout)findViewById(R.id.knowledge);
        mSecond_bottom =(FrameLayout)findViewById(R.id.seek_help);
        mThird_bottom =  (FrameLayout)findViewById(R.id.about_play);
        mFouth_bottom = (FrameLayout)findViewById(R.id.user_information);
        mCenter_bottom = (FrameLayout) findViewById(R.id.center);

    }

    private void onBottomBarClick() {

        mFirst_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onFirstClick();
                }
            }
        });
        mSecond_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onSecondClick();
                }
            }
        });
        mThird_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onThirdClick();
                }
            }
        });
        mFouth_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onFourthClick();
                }
            }
        });
        mCenter_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onCenterClick();
                }
            }
        });
    }
    public void setOnBottomBarOnclick(OnBottomBarClick onBottomBarClick) {

        mOnBottomBarClick = onBottomBarClick;
    }
    public interface OnBottomBarClick {
        void onFirstClick();

        void onSecondClick();

        void onThirdClick();

        void onFourthClick();

        void onCenterClick();
    }
}
