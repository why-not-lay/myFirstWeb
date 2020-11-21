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

import com.myFirstWeb.bean.User;
import java.util.List;

public class Logout extends HttpServlet {

    private void Out(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<?> list = (List<?>)req.getSession().getAttribute("products_buy");
        List<?> list_shopping = (List<?>)req.getSession().getAttribute("shoppings");
        Integer cost_price = (Integer)req.getSession().getAttribute("cost_price");
        if(list != null) {
            req.getSession().removeAttribute("products_buy");
        }
        if(list_shopping != null) {
            req.getSession().removeAttribute("shoppings");
        }
        if(cost_price != null) {
            req.getSession().removeAttribute("cost_price");
        }

        if(user != null) {
            req.getSession().removeAttribute("user");
        }
        resp.sendRedirect("/");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.Out(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.Out(req, resp);
    }
}
