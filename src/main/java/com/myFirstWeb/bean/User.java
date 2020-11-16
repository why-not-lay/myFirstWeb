package com.myFirstWeb.bean;
import java.util.Date;

public class User {
    private String name;
    private long uid;
    private String password;
    private String email;
    private Date date_create;
    private Date date_login;

    public User() {
    }


    public User(long id, String name, String password) {
        this.uid = id;
        this.name = name;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return uid;
    }
    public void setId(long id) {
        this.uid = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDate_login() {
        return date_login;
    }
    public void setDate_login(Date date_login) {
        this.date_login = date_login;
    }
    public Date getDate_create() {
        return date_create;
    }
    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        return this.uid == ((User)obj).uid;
    }


}
