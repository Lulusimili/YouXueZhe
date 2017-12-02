package com.example.administrator.youxuezhe.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 轮播图片pager适配器
 */
public class CarouselPagerAdapter extends PagerAdapter {
    private List<ViewPager> viewPagers;
    private List<ImageView> imageViews;
    public CarouselPagerAdapter(List<ImageView>imageViews) {
        this.imageViews=imageViews;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // return super.instantiateItem(container, position);
        // 修改position
        position = position % imageViews.size();
        // 将图片控件添加到容器
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
