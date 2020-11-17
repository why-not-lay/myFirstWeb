package com.myFirstWeb.bean;
public class Records_shopping_cart {

    private long sid;
    private long uid;
    private long pid;
    private int num;
    private int status;

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getSid() {
        return sid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getPid() {
        return pid;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
