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
        if(user == null) {
            resp.sendRedirect("/login");
        } else {
            try {
                String page_onshlf_str= req.getParameter("page_onshlf");
                String page_offshelf_str = req.getParameter("page_offshelf");
                String page_soldout_str = req.getParameter("page_soldout");

                Integer page_onshlf = 0;
                Integer page_offshelf = 0;
                Integer page_soldout = 0;

                if(page_onshlf_str != null) {
                    page_onshlf = Integer.parseInt(page_onshlf_str);
                }

                if(page_offshelf_str != null) {
                    page_offshelf = Integer.parseInt(page_offshelf_str);
                }

                if(page_soldout_str != null) {
                    page_soldout = Integer.parseInt(page_soldout_str);
                }

                Integer sum_onshelf = ProductController.CountSellerOnShelfProducts(user.getId());
                Integer sum_offshelf = ProductController.CountSellerOffShelfProducts(user.getId());
                Integer sum_soldout = ProductController.CountSellerSoldOutProducts(user.getId());

                ArrayList<Product> products_onshelf = ProductController.GetSellerOnShelfProducts(user.getId(), 10, page_onshlf * 10);
                ArrayList<Product> products_offshelf = ProductController.GetSellerOffShelfProducts(user.getId(), 10, page_offshelf * 10);
                ArrayList<Product> products_soldout = ProductController.GetSellerSoldOutProducts(user.getId(), 10, page_soldout * 10);


                req.setAttribute("products_onshelf", products_onshelf);
                req.setAttribute("products_offshelf", products_offshelf);
                req.setAttribute("products_soldout", products_soldout);

                req.setAttribute("sum_onshelf", sum_onshelf);
                req.setAttribute("sum_offshelf", sum_offshelf);
                req.setAttribute("sum_soldout", sum_soldout);

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
