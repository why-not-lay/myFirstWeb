package com.myFirstWeb.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import com.myFirstWeb.controller.DatabaseController;
import com.myFirstWeb.bean.*;
import java.util.Date;

public class OrderController {

    public static int InsertViewRecord(long uid, long pid)throws SQLException,ClassNotFoundException {
        Records_view view = new Records_view();
        view.setUid(uid);
        view.setPid(pid);
        view.setDate(new Date());
        view.setStatus(1);
        DatabaseController.InsertViewRecord(view);

        return 1;
    }

    public static void RemoveViewRecord(long vid)throws SQLException,ClassNotFoundException {
        DatabaseController.RemoveViewRecord(vid);
    }

    public static int InsertShoppingCartRecord(long uid, long pid,int num)throws SQLException, ClassNotFoundException {
        Records_shopping_cart shopping = new Records_shopping_cart();
        Product product = ProductController.SearchProduct(pid);
        if(product == null || product.getNum() < num) {
            return 0;
        }
        shopping.setNum(num);
        shopping.setUid(uid);
        shopping.setPid(pid);
        shopping.setStatus(2);
        DatabaseController.InsertShoppingCartRecord(shopping);
        return 1;
    }

    public static int UpdateShoppingNum(long sid, int num)throws SQLException,ClassNotFoundException {
        Records_shopping_cart shopping = DatabaseController.SearchShoppingCartRecord(sid);
        if(shopping == null) {
            return -1;
        }
        Product product = DatabaseController.SearchProduct(shopping.getPid());
        if(product == null || product.getNum() < num) {
            return 0;
        }
        DatabaseController.UpdateShoppingCartNum(sid, num);
        return 1;
    }

    public static void RemoveShoppingCartRecord(long sid)throws SQLException,ClassNotFoundException {
        DatabaseController.RemoveShoppingCartRecord(sid);
    }

    public static int InsertTradeRecord(long uid, long pid, int num)throws SQLException,ClassNotFoundException {
        Product product = DatabaseController.SearchProduct(pid);
        if(product == null || product.getNum() < num) {
            return 0;
        }
        Records_trade trade = new Records_trade();
        trade.setPid(pid);
        trade.setUid_buyer(uid);
        trade.setUid_seller(product.getUid());
        trade.setStatus(1);
        trade.setCost(num * product.getPrice());
        trade.setDate_trade(new Date());
        trade.setNum(num);
        DatabaseController.InsertTradeRecord(trade);
        return 1;
    }


    public static void RemoveTradeRecord(long tid)throws SQLException, ClassNotFoundException {
        DatabaseController.RemoveTradeRecord(tid);
    }

    public static ArrayList<Records_trade> GetTradeRecords(long uid, int num, int page) throws SQLException, ClassNotFoundException {
        return DatabaseController.GetTradeRecords(uid, num, page);
    }

    public static ArrayList<Records_view> GetViewRecords(long uid, int num, int page)throws SQLException, ClassNotFoundException  {
        return DatabaseController.GetViewRecords(uid, num, page);
    }



}
