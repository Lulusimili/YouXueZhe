package com.example.administrator.youxuezhe.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.youxuezhe.activity.CommodityListActivity;
import com.example.administrator.youxuezhe.bean.CommodityOrderInfo;
import com.example.administrator.youxuezhe.bean.MyRequestBody;
import com.example.administrator.youxuezhe.bean.Order;
import com.example.administrator.youxuezhe.bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
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


    /**
     * 解析从服务器获得的User数据
     *
     * @param response
     * @return
     */
    public static List<User> handleShopbeanResponse(String response) {
        List<User> users = new ArrayList<>();
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取json字符串
     *
     * @param url
     * @return
     */
    public static String getJSon(String url) {
        String responseText = null;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            responseText = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseText;
    }

    /**
     * 获取并解析商品详情
     *
     * @param response
     * @return
     */
    public static List<CommodityOrderInfo> handleUserResponse(String response) {
        List<CommodityOrderInfo> commodityOrderInfos = null;
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray jshop = new JSONArray(response);
                for (int i = 0; i < jshop.length(); i++) {
                    JSONObject shopObject = jshop.getJSONObject(i);
                    CommodityOrderInfo shop = new CommodityOrderInfo();
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
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static List<Order> handleOrderResponse(String response) {
        List<Order> orders = new ArrayList<>();
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray jOrder = new JSONArray(response);
                Log.d("9999",String.valueOf(jOrder));
                for (int i = 0; i < jOrder.length(); i++) {
                    JSONObject orderObject = jOrder.getJSONObject(i);
                    Order order = new Order();
                    order.setPid(orderObject.getInt("pid"));
                    Log.d("0000",String.valueOf(orderObject.getInt("pid")));
                    order.setPprice(orderObject.getDouble("pprice"));
                    Log.d("0000",String.valueOf(orderObject.getDouble("pprice")));
                    order.setPreleaseName(orderObject.getString("releaserName"));
                    order.setPreleasetime(orderObject.getString("preleasetime"));
                    order.setPtitle(orderObject.getString("ptitle"));
                    order.setUserHeaderURL(orderObject.getString("userHeaderURL"));
                    order.setPtime("ptime");
                    orders.add(order);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }
    public static MyRequestBody handleRequest(String response){
       MyRequestBody myRequestBody=new MyRequestBody();
        if(!TextUtils.isEmpty(response)){
            try{
              JSONObject jsonObject=new JSONObject(response);
                myRequestBody.setCode(jsonObject.getInt("errcode"));
                myRequestBody.setMessage(jsonObject.getString("errmsg"));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return myRequestBody;
    }
}
