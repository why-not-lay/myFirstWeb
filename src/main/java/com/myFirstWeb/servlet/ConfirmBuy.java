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

public class ConfirmBuy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        ArrayList<Product> products_buy = (ArrayList<Product>)req.getSession().getAttribute("products_buy");
        Integer cost_price = (Integer)req.getSession().getAttribute("cost_price");
        if(user == null) {
            resp.sendRedirect("/login");
            return;
        }

        if(products_buy == null || cost_price == null) {
            resp.sendRedirect("/index");
            return;
        }

        try {
            for (Product product : products_buy) {
                int flag = OrderController.InsertTradeRecord(product.getUid(), product.getId(), product.getNum());
                if(flag == 0) {
                    req.getSession().removeAttribute("products_buy");
                    req.getSession().removeAttribute("cost_price");
                    resp.sendRedirect("/shoppingcart");
                    return;
                }
            }
            req.getSession().removeAttribute("products_buy");
            req.getSession().removeAttribute("cost_price");
            req.setAttribute("cost_price", cost_price);
            req.setAttribute("products_buy", products_buy);

            // sendemail:  <17-11-20, yourname> //
            req.getRequestDispatcher("jsp/confirmbuy.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
