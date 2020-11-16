package com.myFirstWeb.controller;

import java.util.HashMap;
import java.sql.SQLException;
import java.util.Date;
//import java.util.List;
import java.util.ArrayList;
import com.myFirstWeb.bean.Product;
import com.myFirstWeb.bean.User;
import com.myFirstWeb.controller.DatabaseController;

public class ProductController {

    public static void Insert(Product product)throws SQLException,ClassNotFoundException{
        product.setStatus(3);
        DatabaseController.InsertProduct(product);
    }

    public static Product Search(long pid)throws SQLException,ClassNotFoundException{
        return DatabaseController.SearchProduct(pid);
    }

    public static ArrayList<Product> GetSellerProducts(long uid, int num, int page)throws SQLException,ClassNotFoundException{
        return DatabaseController.GetSellerProducts(num,page,uid);
    }

    public static void Remove(long pid)throws SQLException,ClassNotFoundException{
        DatabaseController.RemoveProduct(pid);
    }

    public static ArrayList<Product> GetProducts(int num, int page)throws SQLException,ClassNotFoundException{
        return DatabaseController.GetProducts(num,page);
    }

    public static int CountProducts()throws SQLException,ClassNotFoundException {
        return DatabaseController.CountProducts();
    }

    public static int CountSellerProducts(long uid)throws SQLException, ClassNotFoundException {
        return DatabaseController.CountSellerProducts(uid);
    }

    public static int BuyProduct(long pid)throws SQLException,ClassNotFoundException {
        Product product = DatabaseController.SearchProduct(pid);
        if(product==null ) {
            return -2;
        }
        if(product.getNum() == 0) {
            return -1;
        }
        product.setNum(product.getNum()-1);
        DatabaseController.UpdateProduct(product);
        return product.getPrice();
    }

    public static void BuyProducts(ArrayList<Long> products)throws SQLException, ClassNotFoundException {

        // dozens of product in :  <16-11-20, yourname> //

    }

    public static void OffShelfProduct(long pid)throws SQLException, ClassNotFoundException {
        Product product = DatabaseController.SearchProduct(pid);
        if(product == null) {
            return;
        }
        product.setStatus(1);
        DatabaseController.UpdateProduct(product);
    }

    public static void View()throws SQLException, ClassNotFoundException {

    }
}
