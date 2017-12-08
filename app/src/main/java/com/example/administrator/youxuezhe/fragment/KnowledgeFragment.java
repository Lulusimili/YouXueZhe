package com.example.administrator.youxuezhe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.activity.CommodityListActivity;
import com.example.administrator.youxuezhe.adapter.CarouselPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识技能页
 */
public class KnowledgeFragment extends Fragment implements View.OnClickListener {
    /**
     * 测试图片
     */
    private int[] images = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};

    private List<ImageView> imageViews;
    private ViewPager viewPager;
    private LinearLayout pointGroup;
    private Button musicButton;
    Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageViews=new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_knowledge, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.viewpager);
        pointGroup = view.findViewById(R.id.point_group);
        musicButton=view.findViewById(R.id.music_button);
        viewPager.setAdapter(new CarouselPagerAdapter(imageViews));
        musicButton.setOnClickListener(this);
        addToContent();
        pagerChange();
        handlerSend();
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getContext(), CommodityListActivity.class);
        switch (view.getId()){
            case R.id.music_button:
                intent.putExtra("from","音乐");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void addToContent() {
        for (int i = 0; i <images.length; i++) {
            ImageView imageView = new ImageView(getContext());
            // 将图片设置到ImageView控件上
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
