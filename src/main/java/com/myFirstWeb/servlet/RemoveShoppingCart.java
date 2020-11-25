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

public class RemoveShoppingCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        //判断当前用户是否已经登录
        if(user == null) {
            //未登录跳转至登录页面
            resp.sendRedirect("/login");
            return;
        }
        try {
            String sid_str = req.getParameter("sid");
            //判断是否有当前购物车记录
            if(sid_str == null) {
                req.getSession().setAttribute("error", "无该条购物车记录");
                resp.sendRedirect("/error");
                return;
            }
            long sid = Long.parseLong(sid_str);
            //删除购物车
            OrderController.RemoveShoppingCartRecord(sid);
            resp.sendRedirect("/shoppingcart");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);

    }

}
