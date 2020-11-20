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
        if(user == null) {
            resp.sendRedirect("/login");
            return;
        }

        if(pid_str == null) {
            resp.sendRedirect("/seller");
            return;
        }
        // 上架无反应, 更新商品问题:  <20-11-20, yourname> //

        long pid = Long.parseLong(pid_str);
        try {
            Product product = ProductController.SearchProduct(pid);
            if(product == null) {
                resp.sendRedirect("/index");;
            }
            if(product.getUid() != user.getId()) {
                resp.sendRedirect("/index");
            }
            ProductController.OnShelfProduct(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/seller");
    }
}
