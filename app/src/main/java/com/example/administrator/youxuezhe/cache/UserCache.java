package com.example.administrator.youxuezhe.cache;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class UserCache {
    public static String account;
    public static String password;

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        UserCache.account = account;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserCache.password = password;
    }
}
