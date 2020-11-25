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

public class RemoveViewRecord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        //判断当前用户是否已经登录
        if(user == null) {
            //未登录跳转至登录页面
            resp.sendRedirect("/login");
            return;
        }
        try {
            String vid_str = req.getParameter("vid");
            //判断是否有该条浏览记录
            if(vid_str == null) {
                req.getSession().setAttribute("error", "无该条购买记录");
                resp.sendRedirect("/error");
                return;
            }
            long vid = Long.parseLong(vid_str);
            //删除浏览记录
            OrderController.RemoveViewRecord(vid);
            resp.sendRedirect("/user");

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);

    }

}
