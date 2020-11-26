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
public class Seller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        //判断当前用户是否已经登录
        if(user == null) {
            //未登录跳转至登录页面
            resp.sendRedirect("/login");
        } else {
            try {
                //分别获取上架商品的页数，下架商品的页数和已经卖完商品的页数
                String page_onshlf_str= req.getParameter("page_onshlf");
                String page_offshelf_str = req.getParameter("page_offshelf");
                String page_soldout_str = req.getParameter("page_soldout");

                //获取卖家的销售记录和浏览记录
                String page_seller_trade_str = req.getParameter("page_trade");
                String page_seller_view_str = req.getParameter("page_view");

                Integer page_onshlf = 0;
                Integer page_offshelf = 0;
                Integer page_soldout = 0;

                Integer page_trade = 0;
                Integer page_view = 0;

                if(page_onshlf_str != null) {
                    page_onshlf = Integer.parseInt(page_onshlf_str);
                }

                if(page_offshelf_str != null) {
                    page_offshelf = Integer.parseInt(page_offshelf_str);
                }

                if(page_soldout_str != null) {
                    page_soldout = Integer.parseInt(page_soldout_str);
                }

                if(page_seller_trade_str != null) {
                    page_trade = Integer.parseInt(page_seller_trade_str);
                }

                if(page_seller_view_str != null) {
                    page_view = Integer.parseInt(page_seller_view_str);
                }

                //分别获取已有的上架商品总数，下架商品的总数，卖完商品的总数
                Integer sum_onshelf = ProductController.CountSellerOnShelfProducts(user.getId());
                Integer sum_offshelf = ProductController.CountSellerOffShelfProducts(user.getId());
                Integer sum_soldout = ProductController.CountSellerSoldOutProducts(user.getId());

                Integer sum_trades = OrderController.CountTradeRecordsSeller(user.getId());
                Integer sum_views = OrderController.CountViewRecordsSeller(user.getId());

                //根据指定页数来获取具体上架商品，下架商品和已卖完商品
                ArrayList<Product> products_onshelf = ProductController.GetSellerOnShelfProducts(user.getId(), 10, page_onshlf * 10);
                ArrayList<Product> products_offshelf = ProductController.GetSellerOffShelfProducts(user.getId(), 10, page_offshelf * 10);
                ArrayList<Product> products_soldout = ProductController.GetSellerSoldOutProducts(user.getId(), 10, page_soldout * 10);

                ArrayList<String> users_view = UserController.GetSellerViewRecordUsers(user.getId(), 10, 10 * page_view);
                ArrayList<String> users_trade = UserController.GetSellerTradeRecordUsers(user.getId(), 10, page_trade*10);

                ArrayList<Records_view> views = OrderController.GetViewRecordsSeller(user.getId(), 10, page_view * 10);
                ArrayList<Records_trade> trades  = OrderController.GetTradeRecordsSeller(user.getId(), 10, 10 * page_trade);

                req.setAttribute("products_onshelf", products_onshelf);
                req.setAttribute("products_offshelf", products_offshelf);
                req.setAttribute("products_soldout", products_soldout);

                req.setAttribute("users_view", users_view);
                req.setAttribute("users_trade", users_trade);

                req.setAttribute("views", views);
                req.setAttribute("trades", trades);

                req.setAttribute("sum_onshelf", sum_onshelf);
                req.setAttribute("sum_offshelf", sum_offshelf);
                req.setAttribute("sum_soldout", sum_soldout);

                req.setAttribute("sum_trades", sum_trades);
                req.setAttribute("sum_views", sum_views);

                req.getRequestDispatcher("/jsp/seller.jsp").forward(req,resp);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
