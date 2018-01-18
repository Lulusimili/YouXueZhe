package com.example.administrator.youxuezhe.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.youxuezhe.activity.CommodityListActivity;
import com.example.administrator.youxuezhe.bean.CommodityOrderInfo;
import com.example.administrator.youxuezhe.bean.MyRequestBody;
import com.example.administrator.youxuezhe.bean.Order;
import com.example.administrator.youxuezhe.bean.User;
import com.example.administrator.youxuezhe.bean.needing;
import com.example.administrator.youxuezhe.bean.needingInfo;

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
    public static needingInfo handleNeedingDetailReponse(String response){
        needingInfo needingInfo=new needingInfo();
        if(!response.isEmpty()){
            try{
                JSONObject needObject=new JSONObject(response);
                Log.d("11",String.valueOf(needObject));
                needingInfo.setNeedDetail(needObject.getString("needDetail"));
                needingInfo.setNeedName((needObject.getString("needName")));
                needingInfo.setPrice(needObject.getInt("price"));
                needingInfo.setImgUrl(needObject.getString("imageUrl"));
                needingInfo.setPid(needObject.getInt("needId"));
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }return needingInfo;
    }

    /**
     * 获取并解析商品详情
     *
     * @param response
     * @return
     */
    public static CommodityOrderInfo handleDetailResponse(String response) {
        CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject shopObject=new JSONObject(response);
                Log.d("0000",String.valueOf(shopObject));
                    commodityOrderInfo.setPid(shopObject.getInt("pid"));
                    commodityOrderInfo.setPtitle(shopObject.getString("ptitle"));
                    commodityOrderInfo.setPcontent(shopObject.getString("pcontent"));
                    commodityOrderInfo.setPprice(shopObject.getDouble("pprice"));
                    commodityOrderInfo.setPshow(shopObject.getString("pshow"));
                    commodityOrderInfo.setPtime(shopObject.getString("ptime"));
                    commodityOrderInfo.setpImageUrl(shopObject.getString("pImageUrl"));
                    commodityOrderInfo.setPaddress(shopObject.getString("paddress"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return commodityOrderInfo;
    }

    /**
     * 获取needing列表详情
     * @param response
     * @return
     */
    public static List<needing> handleNeedingResponse(String response){
        List<needing>needings=new ArrayList<>();
        needing needing = new needing();
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray jneeding = new JSONArray(response);
                Log.d("111", String.valueOf(jneeding));
                for (int i = 0; i < jneeding.length(); i++) {
                    JSONObject needingObject = jneeding.getJSONObject(i);
                    needing.setNeedName(needingObject.getString("needName"));
                    needing.setImgUrl(needingObject.getString("imageUrl"));
                    needing.setPrice(needingObject.getInt("price"));
                    needing.setNeedId(needingObject.getString("needId"));
                    needing.setUserName(needingObject.getString("userName"));
                    needings.add(needing);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return needings;
    }

    public static List<Order> handleOrderResponse(String response,String type) {
        List<Order> orders = new ArrayList<>();
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray jOrder = new JSONArray(response);
                Log.d("12345",String.valueOf(jOrder));
                for (int i = 0; i < jOrder.length(); i++) {
                    JSONObject orderObject = jOrder.getJSONObject(i);
                    Order order = new Order();
                    order.setPid(orderObject.getInt("pid"));
                    order.setPprice(orderObject.getDouble("pprice"));
                    order.setPreleaseName(orderObject.getString("releaserName"));
                    order.setPreleasetime(orderObject.getInt("preleasetime"));
                    order.setPtitle(orderObject.getString("ptitle"));
                    order.setUserHeaderURL(orderObject.getString("userHeaderURL"));
                    try {
                        if (type.equals(MyConstant.NO_PTIME)){
                            order.setPtime("null");
                        }else {
                            order.setPtime(orderObject.getString("ptime"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    orders.add(order);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    /**
     * 处理服务器响应码
     * @param response
     * @return
     */
    public static MyRequestBody handleRequest(String response){
       MyRequestBody myRequestBody=new MyRequestBody();
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject jsonObject=new JSONObject(response);
                Log.d("json",String.valueOf(jsonObject));
                myRequestBody.setCode(jsonObject.getString("errcode"));
                myRequestBody.setMessage(jsonObject.getString("errmsg"));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return myRequestBody;
    }

}
