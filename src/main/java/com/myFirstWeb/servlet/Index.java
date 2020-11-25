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

import com.myFirstWeb.bean.User;
import com.myFirstWeb.bean.Product;
import com.myFirstWeb.controller.ProductController;

import java.util.ArrayList;
public class Index extends HttpServlet {
    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        try {
//            User user = (User)req.getSession().getAttribute("user");
            String str_page = req.getParameter("page"); //用于获取用户的页数

            int page = 0;
            Integer all = ProductController.CountProducts();
            if(str_page != null) {
                page = Integer.parseInt(str_page);
            }

            //根据页数来获取指定数量的商品
            ArrayList<Product> products = ProductController.GetProducts(10,page * 10);
            req.setAttribute("products",products);
            req.setAttribute("all",all);
            req.getRequestDispatcher("/jsp/index.jsp").forward(req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.index(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.index(req, resp);
    }
}
