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
import java.util.List;

public class ConfirmBuy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect("/login");
            return;
        }
        try {
            String delete_str = req.getParameter("delete");
            if(delete_str != null) {
                req.getSession().removeAttribute("products_buy");
                req.getSession().removeAttribute("cost_price");
                resp.sendRedirect("/shoppingcart");
                return;
            }

            List<?> list = (List<?>)req.getSession().getAttribute("products_buy");
            ArrayList<Product> products_buy = new ArrayList<Product>();
            for (Object obj : list) {
                products_buy.add((Product)obj);
            }
            Integer cost_price = (Integer)req.getSession().getAttribute("cost_price");
            req.getSession().removeAttribute("products_buy");
            req.getSession().removeAttribute("cost_price");

            if(cost_price == null) {
                resp.sendRedirect("/index");
                return;
            }
            for (Product product : products_buy) {
                int flag = OrderController.InsertTradeRecord(product.getUid(), product.getId(), product.getNum());
                if(flag == 0) {
                    resp.sendRedirect("/shoppingcart");
                    return;
                }
            }
//            req.setAttribute("cost_price", cost_price);
//            req.setAttribute("products_buy", products_buy);

            // sendemail:  <17-11-20, yourname> //
            req.getRequestDispatcher("jsp/pay.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
