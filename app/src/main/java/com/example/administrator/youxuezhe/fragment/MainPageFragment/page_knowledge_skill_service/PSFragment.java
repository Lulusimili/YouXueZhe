package com.example.administrator.youxuezhe.fragment.MainPageFragment.page_knowledge_skill_service;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PSFragment extends Fragment {


    public static PSFragment getInstance(){
        Bundle bundle=new Bundle();
        PSFragment fragment=new PSFragment();
        fragment.setArguments(bundle);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ps, container, false);
    }

}
