package com.example.administrator.youxuezhe.utils;

import android.text.TextUtils;

import com.example.administrator.youxuezhe.activity.CommodityListActivity;
import com.example.administrator.youxuezhe.bean.CommodityOrderInfo;
import com.example.administrator.youxuezhe.bean.Order;
import com.example.administrator.youxuezhe.bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/12/2 0002.
 */

public class HandleJson {
    /**
     * JSON 类型
     */
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");


    /**
     * 解析从服务器获得的User数据
     * @param response
     * @return
     */
    public static List<User> handleShopbeanResponse(String response) {
        List<User> users=new ArrayList<>();
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray jUser = new JSONArray(response);
                for (int i = 0; i < jUser.length(); i++) {
                    JSONObject userObject = jUser.getJSONObject(i);
                    User user = new User();
                    user.setUserName(userObject.getString("name"));
                    user.setUserGrade(userObject.getString("grade"));
                    user.setUserSchool(userObject.getString("school"));
                    user.setUserMajor(userObject.getString("major"));
                    users.add(user);
                }
                return users;
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取json字符串
     * @param url
     * @return
     */
    public static String getJSon(String url){
        String responseText=null;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            responseText=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseText;
    }

    /**
     * 获取并解析商品详情
     * @param response
     * @return
     */
    public static List<CommodityOrderInfo> handleUserResponse(String response){
        List<CommodityOrderInfo> commodityOrderInfos=null;
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray jshop=new JSONArray(response);
                for(int i=0;i<jshop.length();i++){
                    JSONObject shopObject=jshop.getJSONObject(i);
                    CommodityOrderInfo shop=new CommodityOrderInfo();
                    shop.setPid(shopObject.getInt("pid"));
                    shop.setPtitle(shopObject.getString("ptitle"));
                    shop.setPcontent(shopObject.getString("content"));
                    shop.setPprice(shopObject.getDouble("pprice"));
                    shop.setPshow(shopObject.getString("pshow"));
                    shop.setPtime(shopObject.getString("ptime"));
                    shop.setpImageUrl(shopObject.getString("pImageUrl"));
                    shop.setPaddress(shopObject.getString("paddress"));
                    commodityOrderInfos.add(shop);
                }
                return commodityOrderInfos;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return null;
    }

    public static List<Order> handleOrederResponse(String response){
        List<Order> orders=null;
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray jOrder=new JSONArray(response);
                for (int i=0;i<jOrder.length();i++){
                    JSONObject orderObject=jOrder.getJSONObject(i);
                    Order order=new Order();
                    order.setPid(orderObject.getInt("pid"));
                    order.setPprice(orderObject.getDouble("pprice"));
                    order.setPreleaseName(orderObject.getString("preleaseName"));
                    order.setPreleasetime(orderObject.getString("preleasetime"));
                    order.setPtitle(orderObject.getString("ptitle"));
                    order.setUserHeaderURL(orderObject.getString("userHeaderUrl"));
                    order.setPtime(orderObject.getString("ptime"));
                    orders.add(order);
                }
                return orders;

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 向服务器发送一个json字符串
     * @param url
     * @param content
     */
    public static void postJson(String url,String content) {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(JSON,content);
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
