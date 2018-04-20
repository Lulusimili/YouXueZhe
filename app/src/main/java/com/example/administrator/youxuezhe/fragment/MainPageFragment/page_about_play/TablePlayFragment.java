package com.example.administrator.youxuezhe.fragment.MainPageFragment.page_about_play;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablePlayFragment extends Fragment {


    public static TablePlayFragment getInstance(){
        Bundle bundle=new Bundle();
        TablePlayFragment fragment=new TablePlayFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_play, container, false);
    }

}
