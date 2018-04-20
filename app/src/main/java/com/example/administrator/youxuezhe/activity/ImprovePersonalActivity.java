package com.example.administrator.youxuezhe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

import com.example.administrator.youxuezhe.R;

public class ImprovePersonalActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_personal);
        ratingBar=(RatingBar)findViewById(R.id.rating_bar);
    }
}
