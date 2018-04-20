package com.example.administrator.youxuezhe.utils;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static com.example.administrator.youxuezhe.MyApplication.getContext;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 相关工具类
 */

public class MyUtils {

    public static Toast mToast;

    /**
     * 定义便捷Toast工具
     * @param text
     */
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

    /**
     * 判断是否登陆超时
     * @param
     * @return
     */
//    public static boolean isTimeOut(String response){
//        boolean isOutTime=false;
//        try {
//            JSONObject jsonObject=new JSONObject(response);
//            if (String.valueOf(jsonObject).equals("{\"errcode\":\"40002\",\"errmsg\":\"未登录\"}")){
//                isOutTime=true;
//            }
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//        return isOutTime;
 //   }

    /**
     * 时间戳转换为时间
     * @param time
     * @return
     */
    @TargetApi(24)
    public static String getDateToString(int time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return sf.format(d);
    }

    /**
     * MD5 加密
     */
    public static String MD5(String string){
        if(TextUtils.isEmpty(string)){
            return "";
        }
        MessageDigest md5=null;
        try {
            md5=MessageDigest.getInstance("MD5");
            byte [] bytes=md5.digest(string.getBytes());
            String results="";
            for (byte b:bytes){
                String temp=Integer.toHexString(b&0xff);
                if(temp.length()==1){
                    temp="0"+temp;
                }
                results+=temp;
            }
            return results;
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }
}
