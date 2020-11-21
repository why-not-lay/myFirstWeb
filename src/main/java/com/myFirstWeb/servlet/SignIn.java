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

public class SignIn extends HttpServlet {
//    private UserController user = new UserController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException{
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            if(username != null && password != null) {
                User user = new User(username,password);
                user.setEmail(email);
                UserController.Insert(user);
                request.getSession().setAttribute("user",user);
                request.setAttribute("user",user);
            }
//        request.getRequestDispatcher("jsp/index.jsp").forward(request,response);
            response.sendRedirect("/");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user != null) {
//            req.setAttribute("user",user);
//            req.getRequestDispatcher("jsp/index.jsp").forward(req, resp);
            resp.sendRedirect("/");
        } else {
            req.getRequestDispatcher("/jsp/signin.jsp").forward(req,resp);
        }

    }
}
