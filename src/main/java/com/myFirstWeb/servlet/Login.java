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
import java.util.List;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");

        //判断当前用户是否已经登录
        if(user != null) {
            //如果登录了就跳转到主页
            resp.sendRedirect("/index");
            return;
        }
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if(username == null || password == null || username.length() == 0 || password.length() == 0) {
                req.getSession().setAttribute("error", "登录失败");
                resp.sendRedirect("/error");
                return;
            }

            // 查找数据库，匹配用户名和密码
            if(UserController.Match(username,password)) {
                //匹配成功则跳转到主页
                User user = UserController.Search(username);
                req.setAttribute("user",user);
                //为了记录用户的登录状态，将其保存在session里
                req.getSession().setAttribute("user",user);
                resp.sendRedirect("/index");
            } else {
                req.getSession().setAttribute("error", "登录失败");
                resp.sendRedirect("/error");

            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
