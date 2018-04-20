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
public class CurricumDesignFragment extends Fragment {


    public static CurricumDesignFragment getInstance(){
        Bundle bundle=new Bundle();
        CurricumDesignFragment curricumDesignFragment=new CurricumDesignFragment();
        curricumDesignFragment.setArguments(bundle);
        return curricumDesignFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curricum_design, container, false);
    }

}
