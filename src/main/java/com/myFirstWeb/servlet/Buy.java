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

public class Buy extends HttpServlet {
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
            //获取单条要清空的购物车id
            String sid_str = req.getParameter("sid");
            int sid = -1;
            ArrayList<Records_shopping_cart> shoppings = null;

            //如果单条购物车id为空，则默认清空整个购物车
            if(sid_str != null) {
                sid = Integer.parseInt(sid_str);
                shoppings = new ArrayList<Records_shopping_cart>();
                Records_shopping_cart shopping = OrderController.SearchShoppingCartRecord(sid, Status.Status_records_shopping_cart.USED);
                if(shopping != null) {
                    shoppings.add(shopping);
                }

            } else {
                shoppings = OrderController.GetSelected(user.getId());
            }

            //如果购物车本来就已经是空的,重定向
            if(shoppings.size() == 0) {
                req.getSession().setAttribute("error", "购物车为空");
                resp.sendRedirect("/error");
                return;
            }

            ArrayList<Product> products = new ArrayList<Product>();
            int cost_price = 0;
            //根据购物车记录中的pid获取商品的各项具体信息
            for (Records_shopping_cart shopping : shoppings) {
                Product product = ProductController.SearchProduct(shopping.getPid());
                if(product != null) {
                    product.setNum(shopping.getNum());
                    cost_price += product.getPrice() * product.getNum();
                    products.add(product);
                }
            }

            //保存购物车的记录
            req.getSession().setAttribute("products_buy", products);
            req.getSession().setAttribute("shoppings", shoppings);
            req.getSession().setAttribute("cost_price",(Integer)cost_price);

            req.getRequestDispatcher("/jsp/buy.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
