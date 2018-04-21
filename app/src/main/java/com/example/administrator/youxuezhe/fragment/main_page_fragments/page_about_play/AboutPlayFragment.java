package com.example.administrator.youxuezhe.fragment.main_page_fragments.page_about_play;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.adapter.AboutPlayAdapter;
import com.example.administrator.youxuezhe.adapter.FragmentAdapter;
import com.example.administrator.youxuezhe.bean.AboutPlayEntity;
import com.example.administrator.youxuezhe.fragment.BasePublishFragment;
import com.example.administrator.youxuezhe.ui.FlashView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutPlayFragment extends BasePublishFragment implements View.OnClickListener{

    private FlashView flashView;
    private Button btnTablePlay;
    private Button btnTrip;
    private Button btnSports;
    private RecyclerView rvAboutPlay;
    private AboutPlayAdapter aboutPlayAdapter;
    private List<AboutPlayEntity> aboutPlayEntities;
    private List<AboutPlayEntity> aboutPlayEntityListFromResponse;


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<String> aboutPlayTitles;
    private List<Fragment> aboutFragments;
    private FragmentAdapter fragmentAdapter;

    public static AboutPlayFragment newInstance() {
        Bundle args = new Bundle();
        AboutPlayFragment fragment = new AboutPlayFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_play, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setAboutPlayTitles();
        setAboutFragments();
        fragmentAdapter=new FragmentAdapter(getChildFragmentManager(),aboutFragments,aboutPlayTitles);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);


//        initView(view);
//        getAboutPlayList(MyUrlManager.SHOW_ALL_ACTIVITY_URL);
//        aboutPlayAdapter=new AboutPlayAdapter(aboutPlayEntities,getContext());
//        rvAboutPlay.setAdapter(aboutPlayAdapter);
//        goToDetail();
    }

    private void initView(View view){
        viewPager=(ViewPager)view.findViewById(R.id.about_play_view_pager);
        tabLayout=(TabLayout)view.findViewById(R.id.tab_about_play);
        aboutFragments=new ArrayList<>();
        aboutPlayTitles=new ArrayList<>();
    }

    private void setAboutPlayTitles(){
        aboutPlayTitles.add("综合");
        aboutPlayTitles.add("旅行");
        aboutPlayTitles.add("运动");
        aboutPlayTitles.add("桌游");
    }

    private void setAboutFragments(){
        aboutFragments.add(CompreFragment.getInstance());
        aboutFragments.add(TripFragment.getInstance());
        aboutFragments.add(SportsFragment.getInstance());
        aboutFragments.add(TablePlayFragment.getInstance());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.button_table_play:
//                intent.putExtra("from","桌游");
//                startActivity(intent);
//                break;
//            case R.id.button_trip:
//                intent.putExtra("from","旅行");
//                startActivity(intent);
//                break;
//            case R.id.button_sports:
//                intent.putExtra("from","运动");
//                startActivity(intent);
//                break;
//            default:
//                break;
        }

    }

}
