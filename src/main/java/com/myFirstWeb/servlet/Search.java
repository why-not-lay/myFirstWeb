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

import com.myFirstWeb.controller.ProductController;
import com.myFirstWeb.bean.*;
import java.util.ArrayList;

public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content_search = req.getParameter("content_search");
        //判断搜索信息是否为空
        if(content_search == null) {
            req.getSession().setAttribute("error", "搜索信息不能为空");
            resp.sendRedirect("/error");
            return;
        }
        try {
            //根据搜索内容搜索商品
            ArrayList<Product> products = ProductController.SearchProducts(content_search);
            req.setAttribute("products", products);
            req.getRequestDispatcher("/jsp/search.jsp").forward(req, resp);;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
