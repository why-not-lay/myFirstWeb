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

public class UpdateShoppingNum extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        //判断当前用户是否已经登录
        if(user == null) {
            //未登录跳转至登录页面
            resp.sendRedirect("/login");
            return;
        }
        try {
            String sid_str = req.getParameter("sid");
            String num_str = req.getParameter("num");
            if(sid_str == null || num_str == null) {
                resp.sendRedirect("/shoppingcart");
                return;
            }
            long sid = Long.parseLong(sid_str);
            int num = Integer.parseInt(num_str);
            OrderController.UpdateShoppingNum(sid, num);
            resp.sendRedirect("/shoppingcart");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
