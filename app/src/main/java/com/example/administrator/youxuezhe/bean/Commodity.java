package com.example.administrator.youxuezhe.bean;

/**
 * Created by Administrator on 2017/12/9 0009.
 */

public class Commodity {
    private int pid;
    private String ptitle;
    private String preleasetime;
    private String preleaseName;
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

    public String getPreleasetime() {
        return preleasetime;
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

    public void setPreleasetime(String preleasetime) {
        this.preleasetime = preleasetime;
    }


    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public void setUserHeaderURL(String userHeaderURL) {
        this.userHeaderURL = userHeaderURL;
    }
}

