package com.myFirstWeb.controller;

import java.sql.SQLException;
//import java.util.List;
import java.util.ArrayList;
import com.myFirstWeb.bean.*;
import com.myFirstWeb.controller.DatabaseController;

public class ProductController {

    public static void InsertProduct(Product product)throws SQLException,ClassNotFoundException{
        product.setStatus(3);
        DatabaseController.InsertProduct(product);
    }

    public static Product SearchProduct(long pid)throws SQLException,ClassNotFoundException{
        return DatabaseController.SearchProduct(pid);
    }

    public static ArrayList<Product> GetSellerProducts(long uid, int num, int page)throws SQLException,ClassNotFoundException{
        return DatabaseController.GetSellerProducts(num,page,uid);
    }

    public static void Remove(long pid)throws SQLException,ClassNotFoundException{
        ArrayList<Records_shopping_cart> shoppings = DatabaseController.GetShoppingCartRecords(pid);
        for (Records_shopping_cart shopping : shoppings) {
            DatabaseController.UpdateShoppingCartStatus(shopping.getSid(), 1);
        }
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

    public static int OnShelfProduct(long pid)throws SQLException,ClassNotFoundException {
        Product product = DatabaseController.SearchProduct(pid);
        if(product == null || product.getNum() == 0) {
            return 0;
        }
        product.setStatus(3);
        DatabaseController.UpdateProduct(product);
        return 1;
    }

    public static int OffShelfProduct(long pid)throws SQLException, ClassNotFoundException {
        Product product = DatabaseController.SearchProduct(pid);
        if(product == null) {
            return 0;
        }
        ArrayList<Records_shopping_cart> shoppings = DatabaseController.GetShoppingCartRecords(pid);
        for (Records_shopping_cart shopping : shoppings) {
            DatabaseController.UpdateShoppingCartStatus(shopping.getSid(), 1);
        }
        product.setStatus(1);
        DatabaseController.UpdateProduct(product);
        return 1;
    }

    public static int BuyProduct(long pid, int num) throws SQLException,ClassNotFoundException{
        Product product = DatabaseController.GetProduct(pid);
        if(product == null || product.getNum() < num) {
            return 0;
        }

        product.setNum(product.getNum()-num);
        ArrayList<Records_shopping_cart> shoppings = DatabaseController.GetShoppingCartRecords(pid);
        for (Records_shopping_cart shopping : shoppings) {
            if(shopping.getNum() > product.getNum()) {
                DatabaseController.UpdateShoppingCartNum(shopping.getSid(),1);
            }
        }

        if(product.getNum() == 0) {
            product.setStatus(2);
            DatabaseController.UpdateProduct(product);
        }
        return 1;
    }

}
