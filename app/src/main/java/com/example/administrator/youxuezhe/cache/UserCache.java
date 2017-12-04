package com.example.administrator.youxuezhe.cache;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class UserCache {
    private static String account;

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        UserCache.account = account;
    }
}
