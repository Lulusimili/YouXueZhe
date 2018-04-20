package com.example.administrator.youxuezhe.pay.ali_pay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.alipay.sdk.app.PayTask;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;

import java.util.Map;


/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class AliPay {
    private String orderInfo;
    private AliPayResultCallBack callBack;
    private PayTask payTask;
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String ERROR_RESULT = "5200";   //支付结果解析错误
    private final String PUBLIC_KEY="";
    private final String RESULT_OK="success";
    private final String RESULT_FAILED="failed";
    private final String ERROR_MSG="error";

    /**
     * 支付结果回调接口
     */
    public interface AliPayResultCallBack {
        void onResult(String result);
        void onError(String errorMsg);
        void onSuccess(SynchronousPayResult payResult);
    }


    public AliPay(Context context, String orderInfo, AliPayResultCallBack callBack){
        payTask=new PayTask((Activity)context);
        this.orderInfo=orderInfo;
        this.callBack=callBack;
    }

    /**
     * 支付
     */
    public void doPay(){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Map<String, String> pay_result = payTask.payV2(orderInfo, true);


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        SynchronousPayResult synchronousPayResult=new SynchronousPayResult(pay_result);
                         String resultInfo = synchronousPayResult.getResult();// 同步返回需要验证的信息
                         ResultInfo info=new ResultInfo(resultInfo);
                        String resultStatus =synchronousPayResult.getResultStatus();
                        if (callBack == null||pay_result == null) {
                            callBack.onError(ERROR_RESULT);
                            return;
                        }
                        if (TextUtils.equals(resultStatus, "9000")) {
                               callBack.onResult(RESULT_OK);
                    } else {
                               callBack.onResult(RESULT_FAILED);
                    }

                    }
                });
            }
        }).start();
    }

    public static boolean verifySign(PublicKey publicKey, String plain_text, byte[] signed) {

        try{
                      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);

                        signature.initVerify(publicKey);

                        signature.update(plain_text.getBytes());

                        return signature.verify(signed);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                 return false;
    }

    public static PublicKey getPublicKey(String key) {
        try {
            PKCS8EncodedKeySpec publicPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
            return keyFactory.generatePublic(publicPKCS8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
