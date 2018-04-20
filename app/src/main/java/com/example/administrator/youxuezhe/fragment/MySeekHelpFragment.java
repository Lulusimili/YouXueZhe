package com.example.administrator.youxuezhe.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySeekHelpFragment extends Fragment {

    public static MySeekHelpFragment newInstance() {
        Bundle args = new Bundle();
        MySeekHelpFragment fragment = new MySeekHelpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_seek_help, container, false);
    }

}
