package com.example.administrator.youxuezhe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.fragment.CourseAndServiceFragment;
import com.example.administrator.youxuezhe.fragment.MyAboutPlayFragment;
import com.example.administrator.youxuezhe.fragment.MySeekHelpFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/23 0023.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<String> titles;
    private List<Fragment> fragments;
    private Fragment mCurrentFragment;
    public FragmentAdapter(FragmentManager fm,List<Fragment> fragments,List<String> titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
