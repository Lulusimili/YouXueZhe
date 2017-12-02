package com.example.administrator.youxuezhe.bean;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 订单详情类
 */

    public class CommodityOrderInfo {
        private int pid;
        private String ptitle;
        private String pcontent;
        private String ptime;
        private double pprice;
        private String paddress;
        private String pshow;
        private String pImageUrl;

        public int getPid(){
            return pid;
        }
        public void setPid(int pid){
            this.pid=pid;
        }
        public String getPtitle(){
            return ptitle;
        }
        public void setPtitle(String ptitle){
            this.ptitle=ptitle;
        }
        public String getPcontent(){
            return pcontent;
        }
        public String getPshow(){
            return pshow;
        }

        public String getPaddress() {
            return paddress;
        }

        public void setPshow(String pshow){
            this.pshow=pshow;
        }
        public String getPtime(){
            return ptime;
        }
        public void setPtime(String ptime) {
            this.ptime = ptime;
        }
        public String getpImageUrl(){
            return pImageUrl;
        }
        public void setpImageUrl(String pImageUrl){
            this.pImageUrl=pImageUrl;
        }
        public double getPprice(){
            return pprice;
        }
        public void setPprice(double pprice){
            this.pprice=pprice;
        }

        public void setPcontent(String pcontent) {
            this.pcontent = pcontent;
        }

        public void setPaddress(String paddress) {
            this.paddress = paddress;
        }
    }

