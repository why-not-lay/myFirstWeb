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
        //判断当前用户是否已经登录
        if(user == null) {
            //未登录跳转至登录页面
            resp.sendRedirect("/login");
            return;
        }
        try {
            //获取delete flag ，该flag用于判断是否要取消订单
            String delete_str = req.getParameter("delete");
            //delete flag不为空，则取消订单
            if(delete_str != null) {
                req.getSession().removeAttribute("products_buy");
                req.getSession().removeAttribute("cost_price");
                resp.sendRedirect("/shoppingcart");
                return;
            }

            List<?> list = (List<?>)req.getSession().getAttribute("products_buy");
            List<?> list_shopping = (List<?>)req.getSession().getAttribute("shoppings");
            ArrayList<Product> products_buy = new ArrayList<Product>();
            ArrayList<Records_shopping_cart> shoppings = new ArrayList<Records_shopping_cart>();
            for (Object obj : list) {
                products_buy.add((Product)obj);
            }
            for(Object obj: list_shopping) {
                shoppings.add((Records_shopping_cart)obj);
            }

            //获取订单信息，即之前要清空的购物车信息
            Integer cost_price = (Integer)req.getSession().getAttribute("cost_price");
            req.getSession().removeAttribute("products_buy");
            req.getSession().removeAttribute("cost_price");
            req.getSession().removeAttribute("shoppings");


            if(cost_price == null) {
                req.getSession().setAttribute("error", "订单有误");
                resp.sendRedirect("/error");
                return;
            }
            for (Product product : products_buy) {
                //结算订单同时插入交易记录
                int flag = OrderController.InsertTradeRecord(user.getId(), product.getId(), product.getNum());
                if(flag == 0) {
                    req.getSession().setAttribute("error", "订单有误");
                    resp.sendRedirect("/error");
                    return;
                }
            }
            //在订单成功结算后删除要清空的购物车记录
            for (Records_shopping_cart shopping : shoppings) {
                OrderController.RemoveShoppingCartRecord(shopping.getSid());
            }

            //发送邮件告知
            String content = "你已经成功支付" + cost_price + "元,感谢你对本平台的支持,希望你过得愉快!";
            MailController.SendMail(user.getEmail(), "订单已成功支付", content);
            req.getRequestDispatcher("/jsp/pay.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
