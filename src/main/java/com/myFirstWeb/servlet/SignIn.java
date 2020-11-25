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

import com.myFirstWeb.controller.MailController;
import com.myFirstWeb.controller.UserController;
import com.myFirstWeb.bean.User;

public class SignIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException{
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            if( username != null && password != null && email != null && username.length()!=0 && password.length() != 0  && email.length() != 0) {
                //创建用户
                User user = new User(username,password);
                user.setEmail(email);
                UserController.Insert(user);
                request.getSession().setAttribute("user",user);
                request.setAttribute("user",user);
                response.sendRedirect("/index");
            } else {
                request.getSession().setAttribute("error", "注册失败");
                response.sendRedirect("/error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        //判断当前用户是否已经登录
        if(user != null) {
            resp.sendRedirect("/index");
        } else {
            req.getRequestDispatcher("/jsp/signin.jsp").forward(req,resp);
        }

    }
}
