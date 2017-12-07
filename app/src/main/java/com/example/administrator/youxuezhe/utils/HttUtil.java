package com.example.administrator.youxuezhe.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.youxuezhe.bean.CommodityOrderInfo;
import com.example.administrator.youxuezhe.bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/12/2 0002.
 */

public class HttUtil {
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    public static void sendOkHttpRequest(String url,
                                         okhttp3.Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public static void postOkHttp(String url, RequestBody formBody, okhttp3.Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
