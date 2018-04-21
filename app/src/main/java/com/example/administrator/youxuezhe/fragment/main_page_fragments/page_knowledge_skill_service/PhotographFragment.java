package com.example.administrator.youxuezhe.fragment.main_page_fragments.page_knowledge_skill_service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.R;


public class PhotographFragment extends Fragment {

    public static PhotographFragment getInstance(){
        Bundle bundle=new Bundle();
        PhotographFragment fragment=new PhotographFragment();
        fragment.setArguments(bundle);
        return fragment;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photograph, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
