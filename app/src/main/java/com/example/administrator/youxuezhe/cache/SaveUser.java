package com.example.administrator.youxuezhe.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class SaveUser {
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public SaveUser(Context context){
        this.context=context;
        sp=context.getSharedPreferences("save_user",Context.MODE_PRIVATE);
        editor=sp.edit();
    }

    public void saveUser(String account,String password){
        editor.putString("password",password);
        editor.putString("account",account);
        editor.putBoolean("isLogin",true);
        editor.apply();
    }

    public String getAccount(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("save_user",Context.MODE_PRIVATE);
        return sharedPreferences.getString("account","null");
    }

    public String getPassword(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("save_user",Context.MODE_PRIVATE);
        return sharedPreferences.getString("password","null");
    }

    public boolean getState(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("save_user",Context.MODE_PRIVATE);
        boolean b=sharedPreferences.getBoolean("isLogin",false);
        return b;
    }
}
