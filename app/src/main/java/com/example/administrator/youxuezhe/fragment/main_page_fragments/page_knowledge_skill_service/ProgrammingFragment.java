package com.example.administrator.youxuezhe.fragment.main_page_fragments.page_knowledge_skill_service;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgrammingFragment extends Fragment {


    public static ProgrammingFragment getInstance(){
        Bundle bundle=new Bundle();
        ProgrammingFragment fragment=new ProgrammingFragment();
        fragment.setArguments(bundle);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programming, container, false);
    }

}
