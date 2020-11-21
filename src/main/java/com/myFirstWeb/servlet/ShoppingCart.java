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

public class ShoppingCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect("/login");
            return;
        }

        String page_used_str = req.getParameter("page_used");
        String page_offshelf_str = req.getParameter("page_offshelf");
        String page_not_enough_str = req.getParameter("page_not_enought");

        int page_used = 0, page_offshelf = 0, page_not_enough = 0;
        if(page_used_str != null) {
            page_used = Integer.parseInt(page_used_str);
        }
        if(page_offshelf_str != null) {
            page_offshelf = Integer.parseInt(page_offshelf_str);
        }
        if(page_not_enough_str != null) {
            page_not_enough = Integer.parseInt(page_not_enough_str);
        }

        try {
            ArrayList<Records_shopping_cart> shoppings_used = OrderController.GetShoppingCartRecords(user.getId(), Status.Status_records_shopping_cart.USED,10,page_used*10 );
            ArrayList<Records_shopping_cart> shoppings_offshelf = OrderController.GetShoppingCartRecords(user.getId(), Status.Status_records_shopping_cart.OFF_SHLEF,10,page_offshelf* 10 );
            ArrayList<Records_shopping_cart> shoppings_not_enough = OrderController.GetShoppingCartRecords(user.getId(), Status.Status_records_shopping_cart.NOT_ENOUGH,10,page_not_enough*10 );


            ArrayList<Product> products_used = OrderController.GetShoppingCartProducts(user.getId(), Status.Status_records_shopping_cart.USED,10, page_used*10);
            ArrayList<Product> products_offshelf = OrderController.GetShoppingCartProducts(user.getId(), Status.Status_records_shopping_cart.OFF_SHLEF,10,page_offshelf*10);
            ArrayList<Product> products_not_enough = OrderController.GetShoppingCartProducts(user.getId(), Status.Status_records_shopping_cart.NOT_ENOUGH,10, page_not_enough*10);

            Integer sum_used = OrderController.CountShoppingCartRecords(user.getId(), Status.Status_records_shopping_cart.USED);
            Integer sum_offshelf = OrderController.CountShoppingCartRecords(user.getId(),Status.Status_records_shopping_cart.OFF_SHLEF);
            Integer sum_not_enough = OrderController.CountShoppingCartRecords(user.getId(),Status.Status_records_shopping_cart.NOT_ENOUGH);

            req.setAttribute("shoppings_used", shoppings_used);
            req.setAttribute("shoppings_offshelf",shoppings_offshelf);
            req.setAttribute("shoppings_not_enough", shoppings_not_enough);

            req.setAttribute("products_used", products_used);
            req.setAttribute("products_offshelf", products_offshelf);
            req.setAttribute("products_not_enough", products_not_enough);

            req.setAttribute("sum_used", sum_used);
            req.setAttribute("sum_offshelf", sum_offshelf);
            req.setAttribute("sum_not_enough", sum_not_enough);

            req.getRequestDispatcher("jsp/shoppingcart.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
