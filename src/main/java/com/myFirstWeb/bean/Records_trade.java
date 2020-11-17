package com.myFirstWeb.bean;
import java.util.Date;
public class Records_trade {
    private long tid;
    private long pid;
    private long uid_buyer;
    private long uid_seller;
    private int num;
    private Date date_trade;
    private int cost;
    private int status;

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUid_buyer() {
        return uid_buyer;
    }

    public void setUid_buyer(long uid_buyer) {
        this.uid_buyer = uid_buyer;
    }

    public long getUid_seller() {
        return uid_seller;
    }

    public void setUid_seller(long uid_seller) {
        this.uid_seller = uid_seller;
    }

    public void setDate_trade(Date date_trade) {
        this.date_trade = date_trade;
    }

    public Date getDate_trade() {
        return date_trade;
    }

}
