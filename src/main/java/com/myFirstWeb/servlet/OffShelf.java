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
public class OffShelf extends HttpServlet {
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
        //判断要下架商品的id是否为空
        if(pid_str == null) {
            resp.sendRedirect("/seller");
            return;
        }

        long pid = Long.parseLong(pid_str);
        try {
            Product product = ProductController.SearchProduct(pid);
            //如果没有该商品，下架失败
            if(product == null) {
                req.getSession().setAttribute("error", "无该商品");
                resp.sendRedirect("/error");;
                return;
            }
            //如果商品的所有者id与用户登录的id不同，则下架失败
            if(product.getUid() != user.getId()) {
                req.getSession().setAttribute("error", "无下架权限");
                resp.sendRedirect("/error");
                return;
            }
            //下架商品
            ProductController.OffShelfProduct(pid);
            resp.sendRedirect("/seller");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
