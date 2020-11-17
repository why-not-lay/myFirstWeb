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

public class Buy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect("/login");
            return;
        }
        try {
            ArrayList<Records_shopping_cart> shoppings = OrderController.GetOrders(user.getId());
            ArrayList<Product> products = new ArrayList<Product>();
            int cost_price = 0;
            for (Records_shopping_cart shopping : shoppings) {
                Product product = ProductController.SearchProduct(shopping.getPid());
                if(product != null) {
                    product.setNum(shopping.getNum());
                    cost_price += product.getPrice() * product.getNum();
                    products.add(product);
                }
            }
            req.getSession().setAttribute("products_buy", products);
            req.getSession().setAttribute("cost_price",(Integer)cost_price);
            req.getRequestDispatcher("jsp/buy.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
