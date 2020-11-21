package com.myFirstWeb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.RequestDispatcher;

import com.myFirstWeb.bean.*;
import com.myFirstWeb.controller.*;

public class Item extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect("/login");
            return;
        }
        try {
            String pid_str  = req.getParameter("pid");
            if(pid_str == null) {
                resp.sendRedirect("/index");
                return;
            }
            long pid = Long.parseLong(pid_str);
            Product product = ProductController.SearchProduct(pid);
            if(product != null) {
                OrderController.InsertViewRecord(user.getId(), pid);
            }
            req.setAttribute("product", product);
            req.getRequestDispatcher("/jsp/item.jsp").forward(req, resp);;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
