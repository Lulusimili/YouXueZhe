package com.example.administrator.youxuezhe.utils;

import android.text.TextUtils;
import android.widget.Toast;

import static com.example.administrator.youxuezhe.MyApplication.getContext;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 相关工具类
 */

public class MyUtils {
    public static Toast mToast;
    public static void showToast(String text){
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(getContext(), text,
                        Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }
}
