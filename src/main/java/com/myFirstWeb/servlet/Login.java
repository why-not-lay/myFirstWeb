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

import java.sql.SQLException;

import com.myFirstWeb.controller.UserController;
import com.myFirstWeb.bean.User;
import java.util.List;

public class Login extends HttpServlet {
//    private final UserController user = new UserController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user != null) {
            resp.sendRedirect("/");
            return;
        }
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if(UserController.Match(username,password)) {
                User user = UserController.Search(username);
                req.setAttribute("user",user);
                req.getSession().setAttribute("user",user);
//            req.getRequestDispatcher("jsp/index.jsp").forward(req,resp);
                resp.sendRedirect("/");
            } else {
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);

            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
