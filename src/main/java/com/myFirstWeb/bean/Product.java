package com.myFirstWeb.bean;

public class Product {
    private long pid;
    private long uid;
    private String name;
    private String description;
    private int price;
    private int num;
    private int status;

    public void setPid(long pid) {
        this.pid = pid;
    }
    public long getId() {
        return pid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public int getNum() {
        return num;
    }
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
