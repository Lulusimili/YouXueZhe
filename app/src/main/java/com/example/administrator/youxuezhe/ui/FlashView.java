package com.example.administrator.youxuezhe.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.adapter.CarouselPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class FlashView extends RelativeLayout{
    private RelativeLayout relativeLayout;
    private ViewPager viewPager;
    private LinearLayout pointGroup;
    private Context mContext;
    private OnFlashViewClick mOnFlashViewClick;

    /**
     * 测试图片
     */
    private int[] images = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};
    private List<ImageView> imageViews;
    android.os.Handler handler = new android.os.Handler();

    public FlashView(Context context) {
        super(context);
        initView(context);
    }

    public FlashView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        mContext=context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_flash_view,this,true);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        pointGroup=(LinearLayout)findViewById(R.id.point_group);
        relativeLayout=(RelativeLayout)findViewById(R.id.flash_view);
        imageViews=new ArrayList<>();
//        relativeLayout.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnFlashViewClick.onFlashClick();
//            }
//        });
        viewPager.setAdapter(new CarouselPagerAdapter(imageViews));
        addToContent();
        pagerChange();
        handlerSend();
    }


    private void setOnFlashViewClick(OnFlashViewClick onFlashViewClick){
        mOnFlashViewClick=onFlashViewClick;
    }


    interface OnFlashViewClick{
        void onFlashClick();
    }


    private void addToContent() {
        for (int i = 0; i <images.length; i++) {
            ImageView imageView = new ImageView(getContext());
            // 将图片设置到ImageView控件上
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(images[i]);
            // 将ImageView控件添加到集合
            imageViews.add(imageView);
            // 制作底部小圆点
            ImageView pointImage = new ImageView(getContext());
            pointImage.setImageResource(R.drawable.shape_point_selector);
            // 设置小圆点的布局参数
            int PointSize =getResources().getDimensionPixelSize(R.dimen.point_size);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PointSize, PointSize);
            if (i > 0) {
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.point_margin);
                pointImage.setSelected(false);
            } else {
                pointImage.setSelected(true);
            }
            pointImage.setLayoutParams(params);
            // 添加到容器里
            pointGroup.addView(pointImage);
        }
    }

    private void pagerChange() {
        // 对ViewPager设置滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            int lastPosition;
            @Override
            public void onPageSelected(int position) {
                // 页面被选中
                // 修改position
                position = position%imageViews.size();
                // 设置当前页面选中
                pointGroup.getChildAt(position).setSelected(true);
                // 设置前一页不选中
                pointGroup.getChildAt(lastPosition).setSelected(false);
                lastPosition = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void handlerSend() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = viewPager.getCurrentItem();
                if (currentPosition == viewPager.getAdapter().getCount() - 1) {
                    // 最后一页
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(currentPosition + 1);
                }
                // 一直给自己发消息
                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }

}
