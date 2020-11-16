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

import com.myFirstWeb.bean.*;
import com.myFirstWeb.controller.*;
import java.util.ArrayList;


public class Userpage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User)req.getSession().getAttribute("user");
            String uid_str = req.getParameter("uid");
            String page_str = req.getParameter("page");
            Long uid = null;
            int page = 0;
            if(uid_str != null) {
                uid = Long.parseLong(uid_str);
            }
            if(page_str != null) {
                page = Integer.parseInt(page_str);
            }
            User new_user = null;
            ArrayList<Product> products = null;
            Integer all = null;
            if(uid != null) {
                new_user = UserController.Search(uid);
                products = ProductController.GetSellerProducts(new_user.getId(),10,page);
                all = ProductController.CountSellerProducts(uid);
            }

            if(new_user == null) {
                resp.sendRedirect("/index");
            } else {
                req.setAttribute("user",user);
                req.setAttribute("new_user", new_user);
                req.setAttribute("products",products);
                req.setAttribute("all", all);
                req.getRequestDispatcher("jsp/user.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
