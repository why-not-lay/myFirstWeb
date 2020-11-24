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
import java.util.Date;

import com.myFirstWeb.bean.*;
import com.myFirstWeb.controller.*;
import java.util.ArrayList;


public class Userpage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User)req.getSession().getAttribute("user");
            String page_products_str = req.getParameter("page_products");
            String page_views_str = req.getParameter("page_views");
            String page_trades_str = req.getParameter("page_trades");

            if(user == null) {
                resp.sendRedirect("/login");
                return;
            }
            int page_products = 0, page_views = 0, page_trades = 0;
            if(page_products_str != null ) {
                page_products = Integer.parseInt(page_products_str);
            }

            if(page_trades_str != null) {
                page_trades = Integer.parseInt(page_trades_str);
            }

            if(page_views_str != null) {
                page_views = Integer.parseInt(page_views_str);
            }

            ArrayList<Records_trade> trades_income = OrderController.GetDateTradeRecords(new Date(), user.getId());
            if(trades_income == null) {
                System.out.println("trades_income is null");
            }
            Integer income = 0;
            for (Records_trade trade : trades_income) {
                income += trade.getCost();
            }

            ArrayList<Product> products = ProductController.GetSellerOnShelfProducts(user.getId(), 10, page_products);
            ArrayList<Records_view> views = OrderController.GetViewRecords(user.getId(), 10, 10 * page_views);
            ArrayList<Records_trade> trades = OrderController.GetTradeRecords(user.getId(), 10, 10 * page_trades);

            ArrayList<Product> products_view = OrderController.GetViewRecordProducts(user.getId(), 10,10 * page_views);
            ArrayList<Product> products_trade = OrderController.GetTradeRecordProducts(user.getId(), 10, page_trades * 10);

            Integer page_sum_products = ProductController.CountSellerOnShelfProducts(user.getId());
            Integer page_sum_views = OrderController.CountViewRecords(user.getId());
            Integer page_sum_trades = OrderController.CountTradeRecords(user.getId());

            req.setAttribute("products", products);
            req.setAttribute("page_sum_products", page_sum_products);
            req.setAttribute("views", views);
            req.setAttribute("products_view", products_view);
            req.setAttribute("page_sum_views", page_sum_views);
            req.setAttribute("trades", trades);
            req.setAttribute("products_trade", products_trade);
            req.setAttribute("page_sum_trades", page_sum_trades);
            req.setAttribute("trades_income", trades_income);
            req.setAttribute("income",(Integer)income);

            req.getRequestDispatcher("/jsp/user.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
