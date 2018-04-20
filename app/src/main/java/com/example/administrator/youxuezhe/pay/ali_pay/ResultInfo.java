package com.example.administrator.youxuezhe.pay.ali_pay;

import android.text.TextUtils;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class ResultInfo {
    private String payResponse;
    private String signType;
    private String sign;
    public ResultInfo(String info){
        if (info==null){
            return;
        }
        try {
            JSONObject jsonObject=new JSONObject(info);
            payResponse=jsonObject.getString("alipay_trade_app_pay_response");
            signType=jsonObject.getString("sign_type");
            sign=jsonObject.getString("sign");
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public String getPayResponse() {
        return payResponse;
    }

    public String getSign() {
        return sign;
    }

    public String getSignType() {
        return signType;
    }
}
