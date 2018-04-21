package com.example.administrator.youxuezhe.fragment.main_page_fragments.page_about_play;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportsFragment extends Fragment {


    public static SportsFragment getInstance(){
        Bundle bundle=new Bundle();
        SportsFragment fragment=new SportsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sports, container, false);
    }

}
