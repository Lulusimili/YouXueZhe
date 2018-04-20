package com.example.administrator.youxuezhe.fragment.MainPageFragment.page_knowledge_skill_service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.adapter.FragmentAdapter;
import com.example.administrator.youxuezhe.ui.FlashView;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识技能页
 */
public class KnowledgeSkillServiceFragment extends Fragment {
    private TabLayout mTabSkill;
    private ImageButton mIvEdit;
    private FlashView mFlashView;
    private ViewPager mViewPager;
    private List<Fragment> fragments;
    private List<String> tabTitles;
    private FragmentAdapter fragmentAdapter;

    /**
     * 单例
     *
     * @return
     */
    public static KnowledgeSkillServiceFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeSkillServiceFragment fragment = new KnowledgeSkillServiceFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_knowledge_skill_service, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setTitles();
        setFragments();
        fragmentAdapter=new FragmentAdapter(getChildFragmentManager(),fragments,tabTitles);
        mViewPager.setAdapter(fragmentAdapter);
        mTabSkill.setupWithViewPager(mViewPager);

    }

    private void initView(View view) {
        mViewPager=(ViewPager)view.findViewById(R.id.view_pager);
        mTabSkill = (TabLayout) view.findViewById(R.id.tab_skill);
        mFlashView = (FlashView)view.findViewById(R.id.flash_view);
    }

    private void setTitles(){
        if (tabTitles==null){
            tabTitles=new ArrayList<>();
        }
        tabTitles.add("音乐");
        tabTitles.add("外语");
        tabTitles.add("艺术");
        tabTitles.add("体育");
        tabTitles.add("编程");
        tabTitles.add("摄影");
        tabTitles.add("课设");
        tabTitles.add("PS");
        tabTitles.add("其它");
    }
    private void setFragments(){
        if (fragments==null)
            fragments=new ArrayList<>();
        fragments.add(MusicFragment.getInstance());
        fragments.add(ForeignLanguagesFragment.getInstance());
        fragments.add(ArtFragment.newInstance());
        fragments.add(PEFragment.getInstance());
        fragments.add(ProgrammingFragment.getInstance());
        fragments.add(PhotographFragment.getInstance());
        fragments.add(CurricumDesignFragment.getInstance());
        fragments.add(PSFragment.getInstance());
        fragments.add(OtherFragment.getInstance());
    }



}
