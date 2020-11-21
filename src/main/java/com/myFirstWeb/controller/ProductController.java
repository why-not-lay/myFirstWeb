package com.myFirstWeb.controller;

import java.sql.SQLException;
//import java.util.List;
import java.util.ArrayList;
import com.myFirstWeb.bean.*;
import com.myFirstWeb.controller.DatabaseController;

public class ProductController {

    public static int InsertProduct(Product product)throws SQLException,ClassNotFoundException{
        product.setStatus(Status.Status_products.OFF_SHELF);
        DatabaseController.InsertProduct(product);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static Product SearchProduct(long pid)throws SQLException,ClassNotFoundException{
        return DatabaseController.SearchProduct(pid);
    }

    public static ArrayList<Product> GetSellerOnShelfProducts(long uid, int num, int page)throws SQLException,ClassNotFoundException{
        return DatabaseController.GetSellerProducts(uid, Status.Status_products.ON_SHELF, num, page);
    }

    public static ArrayList<Product> GetSellerOffShelfProducts(long uid, int num, int page)throws SQLException, ClassNotFoundException {
        return DatabaseController.GetSellerProducts(uid, Status.Status_products.OFF_SHELF, num, page);
    }

    public static ArrayList<Product> GetSellerSoldOutProducts(long uid, int num, int page)throws SQLException, ClassNotFoundException  {
        return DatabaseController.GetSellerProducts(uid, Status.Status_products.SOLD_OUT, num, page);
    }

    public static int Remove(long pid)throws SQLException,ClassNotFoundException{
        DatabaseController.SetShoppingCartRecords(pid, Status.Status_records_shopping_cart.DELETED, Status.Status_records_shopping_cart.USED);
        DatabaseController.SetShoppingCartRecords(pid, Status.Status_records_shopping_cart.DELETED, Status.Status_records_shopping_cart.OFF_SHLEF);
        DatabaseController.SetShoppingCartRecords(pid, Status.Status_records_shopping_cart.DELETED, Status.Status_records_shopping_cart.NOT_ENOUGH);
        DatabaseController.RemoveProduct(pid);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static ArrayList<Product> GetProducts(int num, int page)throws SQLException,ClassNotFoundException{
        return DatabaseController.GetProducts(Status.Status_products.ON_SHELF, num, page);
    }

    public static int CountProducts()throws SQLException,ClassNotFoundException {
        return DatabaseController.CountProducts(Status.Status_products.ON_SHELF);
    }

    public static int CountSellerOnShelfProducts(long uid)throws SQLException, ClassNotFoundException {
        return DatabaseController.CountSellerProducts(uid, Status.Status_products.ON_SHELF) ;
    }

    public static int CountSellerSoldOutProducts(long uid)throws SQLException, ClassNotFoundException {
        return DatabaseController.CountSellerProducts(uid, Status.Status_products.SOLD_OUT) ;
    }

    public static int CountSellerOffShelfProducts(long uid)throws SQLException, ClassNotFoundException {
        return DatabaseController.CountSellerProducts(uid, Status.Status_products.OFF_SHELF) ;
    }

    public static int OnShelfProduct(long pid)throws SQLException,ClassNotFoundException {
        Product product = DatabaseController.SearchProduct(pid);
        if(product == null ) {
            return Status.Status_situation.NOT_EXIST;
        }
        if(product.getNum() == 0) {
            product.setStatus(Status.Status_products.SOLD_OUT);
        } else {
            product.setStatus(Status.Status_products.ON_SHELF);
        }
        DatabaseController.UpdateProduct(product);
        DatabaseController.SetShoppingCartRecords(pid, Status.Status_records_shopping_cart.USED, Status.Status_records_shopping_cart.OFF_SHLEF);
        return 1;
    }

    public static int OffShelfProduct(long pid)throws SQLException, ClassNotFoundException {
        Product product = DatabaseController.SearchProduct(pid);
        if(product == null) {
            return Status.Status_situation.NOT_EXIST;
        }

        DatabaseController.SetShoppingCartRecords(pid, Status.Status_records_shopping_cart.OFF_SHLEF, Status.Status_records_shopping_cart.USED);
        DatabaseController.SetShoppingCartRecords(pid, Status.Status_records_shopping_cart.OFF_SHLEF, Status.Status_records_shopping_cart.NOT_ENOUGH);
        product.setStatus(Status.Status_products.OFF_SHELF);
        DatabaseController.UpdateProduct(product);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static int UpdateProduct(Product product)throws SQLException,ClassNotFoundException {
        if(product.getNum() > 0 && product.getStatus() == Status.Status_products.SOLD_OUT) {
            product.setStatus(Status.Status_products.ON_SHELF);
        }
        DatabaseController.UpdateProduct(product);
        return Status.Status_situation.SUCCESSFUL;
    }

    public static int UpdateProductNum(long pid, int num)throws SQLException, ClassNotFoundException {
        if(num < 0 ) {
            return Status.Status_situation.FAILED;
        }
        Product product = DatabaseController.SearchProduct(pid);
        if(product == null || product.getStatus() == Status.Status_products.DELETED) {
            return Status.Status_situation.NOT_EXIST;
        }
        product.setNum(num);

        if(num == 0) {
            product.setStatus(Status.Status_products.SOLD_OUT);
            UpdateProduct(product);
        } else if(num > 0 && product.getStatus() == Status.Status_products.SOLD_OUT) {
            product.setStatus(Status.Status_products.ON_SHELF);
            UpdateProduct(product);
        }

        DatabaseController.UpdateProduct(product);
        ArrayList<Records_shopping_cart> shoppings = DatabaseController.GetShoppingCartRecordsNot(pid, Status.Status_records_shopping_cart.DELETED);
        for (Records_shopping_cart shopping : shoppings) {

            if(num >= shopping.getNum() && shopping.getStatus() == Status.Status_records_shopping_cart.NOT_ENOUGH) {
                shopping.setStatus(Status.Status_records_shopping_cart.USED);
                DatabaseController.UpdateShoppingCartRecords(shopping);
            }
            if(num < shopping.getNum() && shopping.getStatus() == Status.Status_records_shopping_cart.USED) {
                shopping.setStatus(Status.Status_records_shopping_cart.NOT_ENOUGH);
                DatabaseController.UpdateShoppingCartRecords(shopping);
            }
        }
        return Status.Status_situation.SUCCESSFUL;
    }

}
