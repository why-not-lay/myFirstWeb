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

public class UpdateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/seller");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String pid_str = req.getParameter("pid");
        String uid_str = req.getParameter("uid");
        //判断当前用户是否已经登录, 以及传参是否正确，若错误则进行跳转
        if(user == null || uid_str == null) {
            resp.sendRedirect("/login");
            return;
        }

        if(pid_str == null) {
            resp.sendRedirect("/seller");
            return;
        }

        long pid = Long.parseLong(pid_str);
        long uid = Long.parseLong(uid_str);
        try {
            //根据pid参数获取商品
            Product product = ProductController.SearchProduct(pid);
            if(product == null) {
                resp.sendRedirect("/seller");
                return;
            }
            if(product.getUid() != user.getId() || uid != user.getId()) {
                resp.sendRedirect("/index");
                return;
            }

            //获取商品的各项属性
            String product_name = req.getParameter("product_name");
            String product_num = req.getParameter("product_num");
            String product_price = req.getParameter("product_price");
            String product_description = req.getParameter("product_description");
            if(product_name == null||product_price == null || product_num == null) {
                resp.sendRedirect("/seller");
                return;
            }
            //设置商品属性
            product.setNum(Integer.parseInt(product_num));
            product.setPrice(Integer.parseInt(product_price));
            product.setName(product_name);
            product.setDescription(product_description);
            //更新商品
            ProductController.UpdateProduct(product);
            resp.sendRedirect("/seller");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
