package com.example.administrator.youxuezhe.bean;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 发布的订单和我的订单类
 */

public class Order {
    private int pid;
    private String ptitle;
    private int preleasetime;
    private String preleaseName;
    private String ptime;
    private double pprice;
    private String userHeaderURL;

    public double getPprice() {
        return pprice;
    }

    public int getPid() {
        return pid;
    }

    public String getPreleaseName() {
        return preleaseName;
    }

    public int getPreleasetime() {
        return preleasetime;
    }

    public String getPtime() {
        return ptime;
    }

    public String getPtitle() {
        return ptitle;
    }

    public String getUserHeaderURL() {
        return userHeaderURL;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }

    public void setPreleaseName(String preleaseName) {
        this.preleaseName = preleaseName;
    }

    public void setPreleasetime(int preleasetime) {
        this.preleasetime = preleasetime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public void setUserHeaderURL(String userHeaderURL) {
        this.userHeaderURL = userHeaderURL;
    }
}
