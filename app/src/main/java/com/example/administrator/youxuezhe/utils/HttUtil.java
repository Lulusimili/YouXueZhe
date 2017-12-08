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
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class HttUtil {
    private static OkHttpClient okHttpClient;
    public static void loginGetCookie(RequestBody formBody,Callback callback) {
         final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url,cookies);
                        cookieStore.put(HttpUrl.parse(MyUrlManager.BASE_URL),cookies);
                        for(Cookie cookie:cookies){
                            System.out.println("cookie Name:"+cookie.name());
                            System.out.println("cookie Path:"+cookie.path());
                        }
                    }
                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies=cookieStore.get(HttpUrl.parse(MyUrlManager.BASE_URL));

                        return cookies != null ? cookies : new ArrayList<Cookie>();
                        //Log.d("cookie",String.valueOf(cookies.get(0)));
                    }
                }).build();
        final Request request = new Request.Builder()
                .url(MyUrlManager.MY_LOGIN_URL)
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void postOkHttp(String url, RequestBody formBody, okhttp3.Callback callback){
        Request request=new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
