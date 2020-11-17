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

public class RemoveShoppingCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect("/login");
            return;
        }
        try {
            String sid_str = req.getParameter("sid");
            if(sid_str == null) {
                resp.sendRedirect("/shoppingcart");
                return;
            }
            long sid = Long.parseLong(sid_str);
            OrderController.RemoveShoppingCartRecord(sid);
            resp.sendRedirect("/shoppingcart");

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);

    }

}
