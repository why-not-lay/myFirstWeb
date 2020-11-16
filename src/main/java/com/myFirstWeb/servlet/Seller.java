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
public class Seller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect("/login");
        } else {
            try {
                String str_page = req.getParameter("page");
                int page = 0;
                Integer all = ProductController.CountSellerProducts(user.getId());
                if(str_page != null) {
                    page = Integer.parseInt(str_page);
                }
                ArrayList<Product> products = ProductController.GetSellerProducts(user.getId(),10,page);
                req.setAttribute("products",products);
                req.setAttribute("user",user);
                req.setAttribute("all",all);
                req.getRequestDispatcher("jsp/seller.jsp").forward(req,resp);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
