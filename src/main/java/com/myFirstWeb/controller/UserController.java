package com.myFirstWeb.controller;

import java.util.ArrayList;
import java.util.Date;
import com.myFirstWeb.bean.*;
import java.sql.SQLException;


public class UserController {

    public  static User Search(long id)throws SQLException,ClassNotFoundException{
        return DatabaseController.SearchUser(id);
    }

    public static User Search(String name)throws SQLException,ClassNotFoundException{
        return DatabaseController.SearchUser(name);
    }

    public static boolean Match(String name, String password) throws SQLException,ClassNotFoundException{
        if(name == null || password == null) {
            return false;
        }
        User user = Search(name);
        if(user == null) {
            return false;
        }
        UpdateTime(user.getId());
        return user.getPassword().equals(password);
    }

    public  static int Insert(User user)throws SQLException,ClassNotFoundException{
        User old = Search(user.getName());
        if(old != null) {
            return Status.Status_situation.NOT_EXIST;
        }
        Date date = new Date();
        user.setDate_login(date);
        user.setDate_create(date);
        DatabaseController.InsertUser(user);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static int UpdateTime(long uid)throws SQLException, ClassNotFoundException {
        User user = Search(uid);
        if(user == null) {
            return Status.Status_situation.NOT_EXIST;
        }
        user.setDate_login(new Date());
        DatabaseController.UpdateUser(user);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static ArrayList<String> GetSellerViewRecordUsers(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetSellerViewRecordUsers(uid, num, page);
    }


    public static ArrayList<String> GetSellerTradeRecordUsers(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetSellerTradeRecordUsers(uid, num, page);
    }
}
