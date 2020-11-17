package com.myFirstWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.myFirstWeb.controller.UserController;
import com.myFirstWeb.controller.ProductController;
import com.myFirstWeb.bean.User;
import com.myFirstWeb.bean.Product;

public class AddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/seller");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect("/login");
        } else {
            String product_name = (String)req.getParameter("product_name");
            String product_num = (String)req.getParameter("product_num");
            String product_price = (String)req.getParameter("product_price");
            String product_description = (String)req.getParameter("product_description");
            if(product_name == null||product_price == null || product_num == null) {
                resp.sendRedirect("/seller");
            } else {
                try {
                    Product product = new Product();
                    product.setNum(Integer.parseInt(product_num));
                    product.setPrice(Integer.parseInt(product_price));
                    product.setName(product_name);
                    product.setDescription(product_description);
                    product.setUid(user.getId());
                    ProductController.InsertProduct(product);
                    resp.sendRedirect("/seller");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
