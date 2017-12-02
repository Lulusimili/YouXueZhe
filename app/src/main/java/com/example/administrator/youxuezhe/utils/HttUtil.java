package com.example.administrator.youxuezhe.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.youxuezhe.bean.CommodityOrderInfo;
import com.example.administrator.youxuezhe.bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/12/2 0002.
 */

public class HttUtil {
    public static void getJSon(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean handleShopbeanResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray juser = new JSONArray(response);
                for (int i = 0; i < juser.length(); i++) {
                    JSONObject userObject = juser.getJSONObject(i);
//                    User user = new User();
//                    user.setUserName(userObject.getString("name"));
//                    user.setUserGrade(userObject.getString("grade"));
//                    user.setUserSchool(userObject.getString("school"));
//                    user.setUserMajor(userObject.getString("major"));
//                    user.save();
                }
                return true;
            }
            catch (JSONException e) {
                e.printStackTrace();

            }
        }
        return false;
    }
    public static boolean handleUserbeanResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray jshop=new JSONArray(response);
                for(int i=0;i<jshop.length();i++){
                    JSONObject shopobject=jshop.getJSONObject(i);
                    CommodityOrderInfo shop=new CommodityOrderInfo();
                    shop.setPid(shopobject.getInt("pid"));
                    shop.setPtitle(shopobject.getString("ptitle"));
                    shop.setPcontent(shopobject.getString("content"));
//                    shop.setReleasetime(shopobject.getInt("pretime"));
//                    shop.setReleaseName(shopobject.getString("prename"));
                    shop.setPprice(shopobject.getDouble("pprice"));
//                    shop.setUserHeaderURL(shopobject.getString("HeaderURL"));
                    shop.setPshow(shopobject.getString("pshow"));
                    shop.setPtime(shopobject.getString("ptime"));
                    shop.setpImageUrl(shopobject.getString("pImageUrl"));
//                    shop.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }

    public static void postJson(String url,JSONObject jsonObject) {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(JSONObject,jsonObject);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //发送请求获取响应
        try {
            Response response=okHttpClient.newCall(request).execute();
            //判断请求是否成功
            if(response.isSuccessful()){
                //打印服务端返回结果
//                Log.d(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
