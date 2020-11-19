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
        view.setStatus(Status.Status_records_view.USED);
        DatabaseController.InsertViewRecord(view);

        return Status.Status_situation.SUCCESSFUL;
    }

    public static void RemoveViewRecord(long vid)throws SQLException,ClassNotFoundException {
        DatabaseController.RemoveViewRecord(vid);
    }

    public static int InsertShoppingCartRecord(long uid, long pid,int num)throws SQLException, ClassNotFoundException {
        Records_shopping_cart shopping = null;
        ArrayList<Records_shopping_cart> shoppings = DatabaseController.GetShoppingCartRecordsNot(uid, pid, Status.Status_records_shopping_cart.DELETED);
        if(shoppings.size()==1) {
            shopping = shoppings.get(0);
            int flag = UpdateShoppingNum(shopping.getSid(), num + shopping.getNum());
            return flag;
        }

        shopping = new Records_shopping_cart();
        Product product = ProductController.SearchProduct(pid);
        if(product == null) {
            return Status.Status_situation.NOT_EXIST;
        }
        if(product.getNum() < num) {
            num = product.getNum();
        }
        shopping.setNum(num);
        shopping.setUid(uid);
        shopping.setPid(pid);
        shopping.setStatus(Status.Status_records_shopping_cart.SELECTED);
        DatabaseController.InsertShoppingCartRecord(shopping);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static int UpdateShoppingNum(long sid, int num)throws SQLException,ClassNotFoundException {
        Records_shopping_cart shopping = DatabaseController.SearchShoppingCartRecord(sid);
        if(shopping == null) {
            return Status.Status_situation.NOT_EXIST;
        }
        Product product = DatabaseController.SearchProduct(shopping.getPid());
        if(product == null || product.getNum() < num) {
            return Status.Status_situation.FAILED;
        }
        shopping.setNum(num);
        DatabaseController.UpdateShoppingCartRecords(shopping);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static int UpdateShoppingCartStatus(long sid, int status)throws SQLException, ClassNotFoundException {
        Records_shopping_cart shopping = DatabaseController.SearchShoppingCartRecord(sid);
        if(shopping == null || shopping.getStatus() == Status.Status_records_shopping_cart.DELETED) {
            return Status.Status_situation.NOT_EXIST;
        }
        shopping.setStatus(status);
        int flag = DatabaseController.UpdateShoppingCartRecords(shopping);

        if(flag == Status.Status_Database.CONN_FAILED || flag == Status.Status_Database.SYNAX_ERROR) {
            return Status.Status_situation.FAILED;
        }
        return Status.Status_situation.SUCCESSFUL;
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
        return DatabaseController.GetTradeRecords(uid, Status.Status_records_trade.USED, num, page);
    }

    public static ArrayList<Records_view> GetViewRecords(long uid, int num, int page)throws SQLException, ClassNotFoundException  {
        return DatabaseController.GetViewRecords(uid, Status.Status_records_view.USED,num, page);
    }

    public static ArrayList<Records_shopping_cart> GetShoppingCartRecords(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetShoppingCartRecords(uid, num, page);
    }

    public static int CountShoppingCartRecords(long uid)throws SQLException, ClassNotFoundException {
        return DatabaseController.CountShoppingCartRecords(uid, Status.Status_records_shopping_cart.SELECTED) + DatabaseController.CountShoppingCartRecords(uid, Status.Status_records_shopping_cart.UNSELECTED);
    }

    public static ArrayList<Records_shopping_cart> GetSelected(long uid) throws SQLException,ClassNotFoundException  {
        return DatabaseController.GetShoppingCartRecords(uid, Status.Status_records_shopping_cart.SELECTED);
    }



}
