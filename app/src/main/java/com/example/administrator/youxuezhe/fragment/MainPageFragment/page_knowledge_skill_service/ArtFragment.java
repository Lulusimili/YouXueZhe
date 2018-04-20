package com.example.administrator.youxuezhe.fragment.MainPageFragment.page_knowledge_skill_service;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.R;


public class ArtFragment extends Fragment {
    public static ArtFragment newInstance() {
        Bundle args = new Bundle();
        ArtFragment fragment = new ArtFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_art, container, false);
    }

}
