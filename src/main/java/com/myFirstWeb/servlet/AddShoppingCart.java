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

public class AddShoppingCart extends HttpServlet {
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
            //获取要加入购物车的商品id和数量
            String num_str = req.getParameter("num");
            String pid_str = req.getParameter("pid");
            if(num_str == null || pid_str == null || num_str.length() == 0 || pid_str.length() == 0) {
                resp.sendRedirect("/index");
                return;
            }
            int num = Integer.parseInt(num_str);
            long pid = Long.parseLong(pid_str);
            //构建并插入购物车记录
            OrderController.InsertShoppingCartRecord(user.getId(), pid, num);
            resp.sendRedirect("/shoppingcart");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

}
