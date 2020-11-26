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
        ArrayList<Records_shopping_cart> shoppings = DatabaseController.GetShoppingCartRecords(uid, pid, Status.Status_records_shopping_cart.USED);
        //在插入购物车记录前，如果已经存在就在原购物车里修改数量
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
        //如果插入的数量比商品售卖的数量还多就设置为最大值
        if(product.getNum() < num) {
            num = product.getNum();
        }
        shopping.setNum(num);
        shopping.setUid(uid);
        shopping.setPid(pid);
        shopping.setStatus(Status.Status_records_shopping_cart.USED);
        DatabaseController.InsertShoppingCartRecord(shopping);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static int UpdateShoppingNum(long sid, int num)throws SQLException,ClassNotFoundException {
        Records_shopping_cart shopping = DatabaseController.SearchShoppingCartRecord(sid);
        if(shopping == null) {
            return Status.Status_situation.NOT_EXIST;
        }
        Product product = DatabaseController.SearchProduct(shopping.getPid());
        if(product == null) {
            return Status.Status_situation.FAILED;
        }

        if(product.getNum() < num) {
            num = product.getNum();
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
            return Status.Status_situation.NOT_EXIST;
        }
        Records_trade trade = new Records_trade();
        ProductController.UpdateProductNum(pid, product.getNum()-num);
        trade.setPid(pid);
        trade.setUid_buyer(uid);
        trade.setUid_seller(product.getUid());
        trade.setStatus(Status.Status_records_trade.USED);
        trade.setCost(num * product.getPrice());
        trade.setDate_trade(new Date());
        trade.setNum(num);
        DatabaseController.InsertTradeRecord(trade);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static void RemoveTradeRecord(long tid)throws SQLException, ClassNotFoundException {
        DatabaseController.RemoveTradeRecord(tid);
    }

    public static ArrayList<Records_trade> GetTradeRecords(long uid, int num, int page) throws SQLException, ClassNotFoundException {
        return DatabaseController.GetTradeRecords(uid, Status.Status_records_trade.USED, num, page);
    }

    public static ArrayList<Records_trade> GetTradeRecordsSeller(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetTradeRecordsSeller(uid, num, page);
    }

    public static ArrayList<Records_trade> GetDateTradeRecords(Date date,long uid) throws SQLException, ClassNotFoundException {
        return DatabaseController.GetDateTradeRecords(date,  uid);
    }

    public static ArrayList<Product> GetTradeRecordProducts(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetTradeRecordProducts(uid, Status.Status_records_trade.USED, num, page);
    }

    public static ArrayList<Product> GetSellerTradeRecordProducts(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetSellerTradeRecordProducts(uid, num, page);
    }

    public static ArrayList<Records_view> GetViewRecords(long uid, int num, int page)throws SQLException, ClassNotFoundException  {
        return DatabaseController.GetViewRecords(uid, Status.Status_records_view.USED,num, page);
    }

    public static ArrayList<Records_view> GetViewRecordsSeller(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetViewRecordsSeller(uid, num, page);
    }

    public static ArrayList<Product> GetViewRecordProducts(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetViewRecordProducts(uid, Status.Status_records_view.USED, num, page);
    }

    public static ArrayList<Product> GetSellerViewRecordProducts(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetSellerViewRecordProducts(uid, num, page);
    }

    public static ArrayList<Records_shopping_cart> GetShoppingCartRecords(long uid, int status,int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetShoppingCartRecords(uid,status,num,page);
    }

    public static ArrayList<Product> GetShoppingCartProducts(long uid, int status, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetShoppingCartRecordProducts(uid, status, num, page);
    }

    public static int CountShoppingCartRecords(long uid, int status)throws SQLException, ClassNotFoundException {
        return DatabaseController.CountShoppingCartRecords(uid, status);
    }

    public static int CountViewRecords(long uid)throws SQLException, ClassNotFoundException {
        return DatabaseController.CountViewRecords(uid, Status.Status_records_view.USED);
    }

    public static int CountViewRecordsSeller(long uid)throws SQLException, ClassNotFoundException {
        return DatabaseController.CountViewRecordsSeller(uid);
    }

    public static int CountTradeRecords(long uid)throws SQLException,ClassNotFoundException {
        return DatabaseController.CountTradeRecords(uid, Status.Status_records_trade.USED);
    }

    public static int CountTradeRecordsSeller(long uid)throws SQLException,ClassNotFoundException {
        return DatabaseController.CountTradeRecordsSeller(uid);
    }

    public static int CountTradeRecordsP(long pid)throws SQLException,ClassNotFoundException {
        return DatabaseController.CountTradeRecordsP(pid, Status.Status_records_trade.USED) + DatabaseController.CountTradeRecordsP(pid, Status.Status_records_trade.DELETED);
    }

    public static ArrayList<Records_shopping_cart> GetSelected(long uid) throws SQLException,ClassNotFoundException  {
        return DatabaseController.GetShoppingCartRecordsU(uid, Status.Status_records_shopping_cart.USED);
    }

    public static Records_shopping_cart SearchShoppingCartRecord(long sid, int status)throws SQLException, ClassNotFoundException {
        Records_shopping_cart shopping =  DatabaseController.SearchShoppingCartRecord(sid) ;
        if(shopping != null && shopping.getStatus() == status) {
            return shopping;
        }
        return null;
    }
}
