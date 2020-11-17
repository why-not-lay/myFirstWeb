package com.myFirstWeb.controller;

import java.util.HashMap;
import java.util.Date;
import com.myFirstWeb.bean.User;
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

        return user.getPassword().equals(password);
    }

    public  static void Insert(User user)throws SQLException,ClassNotFoundException{
        User old = Search(user.getName());
        if(old != null) {
            return;
        }
        Date date = new Date();
        user.setDate_login(date);
        user.setDate_create(date);
        DatabaseController.InsertUser(user);
    }

    public static void UpdateTime(long uid)throws SQLException, ClassNotFoundException {
        User user = Search(uid);
        if(user == null) {
            return;
        }
        user.setDate_login(new Date());
        DatabaseController.UpdateUser(user);
    }

}
