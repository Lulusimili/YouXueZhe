package com.example.administrator.youxuezhe.ui.label_selector;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;

import java.util.ArrayList;
import java.util.List;


public class LabelDialogFragment extends DialogFragment {

    private View rootView;
    private BottomDialogListener mListener;
    private String[] items;
    private List<String> labels;

    public static LabelDialogFragment newInstance(List<String> labels, BottomDialogListener mListener) {
        LabelDialogFragment fragment = new LabelDialogFragment();
        fragment.setBottomDialogListener(mListener);
        fragment.setLabels(labels);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        rootView = inflater.inflate(R.layout.label_select_bottom_layout, container, false);
        AnimationUtil.slideToUp(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        View decorView = window.getDecorView();
        decorView.setPadding(100, 100, 100, 0);
        if (Build.VERSION.SDK_INT>=16) {
            decorView.setBackground(new ColorDrawable(Color.TRANSPARENT));
        }
        decorView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    slideDown();
                }
                return true;
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleView = (TextView) view.findViewById(R.id.tv_title);
        titleView.setText("标签选择");
        ListView listView = (ListView) view.findViewById(R.id.lv_menu);
        listView.setAdapter(new LabelAdapter(getActivity().getApplicationContext(),labels));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onClick(position);
                }
                slideDown();
            }
        });


        Button cancel = (Button) view.findViewById(R.id.btn_cancel);
        cancel.setText("取消");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideDown();
            }
        });
    }


    private void setBottomDialogListener(BottomDialogListener listener) {
        this.mListener = listener;
    }

    private void setLabels(List<String> labels) {
        this.labels=labels;
    }

    private void slideDown() {
        AnimationUtil.slideToDown(rootView, new AnimationUtil.AnimationEndListener() {
            @Override
            public void onFinish() {
                dismiss();
            }
        });
    }

    public interface BottomDialogListener {
        void onClick(int position);
    }
}
