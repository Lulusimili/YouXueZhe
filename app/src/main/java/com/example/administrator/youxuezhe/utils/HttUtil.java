package com.example.administrator.youxuezhe.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.youxuezhe.bean.CommodityOrderInfo;
import com.example.administrator.youxuezhe.bean.MyRequestBody;
import com.example.administrator.youxuezhe.bean.User;
import com.example.administrator.youxuezhe.cache.UserCache;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.youxuezhe.cache.UserCache.account;
import static com.example.administrator.youxuezhe.cache.UserCache.password;
import static com.example.administrator.youxuezhe.utils.MyUtils.showToast;
import static java.lang.String.valueOf;


public class HttUtil {
    private static OkHttpClient okHttpClient;

    /**
     * 获取登陆cookie
     * @param formBody
     * @param callback
     */
    public static void loginGetCookie(RequestBody formBody,Callback callback) {
         final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url,cookies);
                        cookieStore.put(HttpUrl.parse(MyUrlManager.MY_LOGIN_URL),cookies);
                        if(cookies!=null) {
                            for (Cookie cookie : cookies) {
                                System.out.println("111cookie:" + cookie);
                                System.out.println("111cookie Path:" + cookie.path());
                                System.out.println("111cookie name:" + cookie.name());
                            }
                        }
                    }
                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies=cookieStore.get(HttpUrl.parse(MyUrlManager.MY_LOGIN_URL));
                        if(cookies!=null) {
                            for (Cookie cookie : cookies) {
                                System.out.println("cookie:" + cookie);
                                System.out.println("cookie Path:" + cookie.path());
                                System.out.println("cookie name:" + cookie.name());
                            }
                        }
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).build();
        final Request request = new Request.Builder()
                .url(MyUrlManager.USER_LOGIN)
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 重新登录获取cookie
     */
    public static void reLogin(String account,String password){
        //网络请求

        RequestBody formBody = new FormBody.Builder()
                .add("userName",account)
                .add("userPassword", password)
                .build();
        HttUtil.loginGetCookie(formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     * 发起一个带参数post请求
     * @param url
     * @param formBody
     * @param callback
     */
    public static void postOkHttp(String url, RequestBody formBody, okhttp3.Callback callback){
        Request request=new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        if(okHttpClient==null){
            okHttpClient=new OkHttpClient();
        }
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * post 请求不带参数
     * @param url
     * @param callback
     */
    public static void postOkHttp(String url, okhttp3.Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 刷新cookie
     */
    public static void refrashCookie(){
        RequestBody formBody = new FormBody.Builder()
                .add("userName", UserCache.getAccount())
                .add("userPassword",UserCache.getPassword())
                .build();
        loginGetCookie(formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }


}
