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

public class RemoveProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User)req.getSession().getAttribute("user");
            String pid_str= req.getParameter("pid");
            String uid_str= req.getParameter("uid");
            //判断当前用户是否已经登录
            if(user == null) {
                //未登录跳转至登录页面
                resp.sendRedirect("/login");
            } else {
                long pid = -1;
                long uid = -1;

                if(uid_str == null || pid_str == null) {
                    resp.sendRedirect("/index");
                    return;
                }
                pid = Long.parseLong(pid_str);
                uid = Long.parseLong(uid_str);

                //判断用户id和商品所有者id是否匹配
                if(uid != user.getId()) {
                    req.getSession().setAttribute("error", "无权限");
                    resp.sendRedirect("/error");
                    return;
                }
                //删除商品
                ProductController.Remove(pid);
                resp.sendRedirect("/seller");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
