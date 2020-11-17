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

public class ShoppingCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect("/login");
            return;
        }
        String status_str = req.getParameter("status");
        String sid_str = req.getParameter("sid");
        String page_str = req.getParameter("page");

        try {
            int page = 0;
            if(page_str != null) {
                page = Integer.parseInt(page_str);
            }

            if(sid_str != null && status_str != null) {
                long sid = Long.parseLong(sid_str);
                int status = Integer.parseInt(status_str);
                if(status == 2 || status == 3) {
                    OrderController.UpdateShoppingCartStatus(sid, status);
                }
            }

            ArrayList<Records_shopping_cart> shoppings = OrderController.GetShoppingCartRecords(user.getId(), 10, page);
            ArrayList<Product> products = new ArrayList<Product>();
            for (Records_shopping_cart shopping : shoppings) {
                Product product = ProductController.SearchProduct(shopping.getPid());
                if(product != null) {
                    products.add(product);
                }
            }
            req.setAttribute("shoppings", shoppings);
            req.setAttribute("products", products);
            req.getRequestDispatcher("jsp/shoppingcart.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
