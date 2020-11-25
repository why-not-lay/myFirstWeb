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

public class OnShelf extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String pid_str = req.getParameter("pid");
        //判断当前用户是否已经登录
        if(user == null) {
            //未登录跳转至登录页面
            resp.sendRedirect("/login");
            return;
        }
        //如果要上架商品的id为空，则跳转
        if(pid_str == null) {
            req.getSession().setAttribute("error", "参数提交有误");
            resp.sendRedirect("/error");
            return;
        }

        long pid = Long.parseLong(pid_str);
        try {
            Product product = ProductController.SearchProduct(pid);
            //判断是否有该商品
            if(product == null) {
                req.getSession().setAttribute("error", "没有该商品");
                resp.sendRedirect("/error");;
                return;
            }
            //判断用户id和商品所有者id是否匹配
            if(product.getUid() != user.getId()) {
                req.getSession().setAttribute("error", "无上架权限");
                resp.sendRedirect("/error");
                return;
            }
            //上架商品
            ProductController.OnShelfProduct(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/seller");
    }
}
