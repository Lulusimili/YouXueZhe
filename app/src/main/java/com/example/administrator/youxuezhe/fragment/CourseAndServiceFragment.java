package com.example.administrator.youxuezhe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.R;

public class CourseAndServiceFragment extends BasePublishFragment {

    public static CourseAndServiceFragment newInstance() {
        Bundle args = new Bundle();
        CourseAndServiceFragment fragment = new CourseAndServiceFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_and, container, false);
    }

}
