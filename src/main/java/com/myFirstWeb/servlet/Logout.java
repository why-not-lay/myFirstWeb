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

public class Logout extends HttpServlet {

    private void Out(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");

        if(user != null) {
            req.getSession().removeAttribute("user");
        }
//        req.getRequestDispatcher("jsp/index.jsp").forward(req,resp);
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
