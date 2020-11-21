<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%
User user = (User)request.getSession().getAttribute("user");

ArrayList<Records_shopping_cart> shoppings_used = (ArrayList<Records_shopping_cart>)request.getAttribute("shoppings_used");
ArrayList<Records_shopping_cart> shoppings_offshelf = (ArrayList<Records_shopping_cart>)request.getAttribute("shoppings_offshelf");
ArrayList<Records_shopping_cart> shoppings_not_enough = (ArrayList<Records_shopping_cart>)request.getAttribute("shoppings_not_enough");

ArrayList<Product> products_used = (ArrayList<Product>)request.getAttribute("products_used");
ArrayList<Product> products_offshelf = (ArrayList<Product>)request.getAttribute("products_offshelf");
ArrayList<Product> products_not_enough = (ArrayList<Product>)request.getAttribute("products_not_enough");

Integer sum_used = (Integer)request.getAttribute("sum_used");
Integer sum_offshelf = (Integer)request.getAttribute("sum_offshelf");
Integer sum_not_enough = (Integer)request.getAttribute("sum_not_enough");
%>
<html>
  <body>
    <a href="/index" >返回</a>
    <a href="/seller" >我要卖</a>
    <% if(user == null){ %> <a href="/login" >登录</a>
    <%} else {%>
      <a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a>
    <%}%>
    	
    <h3>购物车</h3>
    <% if(shoppings_used != null && shoppings_used.size() != 0){ %>
        <%for (int i = 0; i < shoppings_used.size(); i++){%>
          <% Product product = products_used.get(i); %>
          <% Records_shopping_cart shopping = shoppings_used.get(i); %>
      <div>
        <div>=======================</div>
        <div><%=product.getName()%></div>
        <div><%=product.getPrice()%></div>
        <div><%=shopping.getNum()%></div>
        <a href="/shoppingcart/buy?sid=<%=shopping.getSid()%>">购买</a>
        <a href="/item?pid=<%=product.getId()%>">商品链接</a>
        <a href="/shoppingcart/remove?sid=<%=shopping.getSid()%>">删除</a>
        <div>=======================</div>
      </div>
        <%}%>
    <%}%>

    <% if(shoppings_not_enough != null && shoppings_not_enough.size() != 0){ %>
    <h3>缺货中</h3>
        <%for (int i = 0; i < shoppings_not_enough.size(); i++){%>
          <% Product product = products_not_enough.get(i); %>
          <% Records_shopping_cart shopping = shoppings_not_enough.get(i); %>
      <div>
        <div>=======================</div>
        <div><%=product.getName()%></div>
        <div><%=product.getPrice()%></div>
        <div><%=shopping.getNum()%></div>
        <a href="/item?pid=<%=product.getId()%>">商品链接</a>
        <a href="/shoppingcart/remove?sid=<%=shopping.getSid()%>">删除</a>
        <div>=======================</div>
      </div>
        <%}%>
    <%}%>
    
    <% if(shoppings_offshelf != null && shoppings_offshelf.size() != 0){ %>
    <h3>已经下架</h3>
        <%for (int i = 0; i < shoppings_offshelf.size(); i++){%>
          <% Product product = products_offshelf.get(i); %>
          <% Records_shopping_cart shopping = shoppings_offshelf.get(i); %>
      <div>
        <div>=======================</div>
        <div><%=product.getName()%></div>
        <div><%=product.getPrice()%></div>
        <div><%=shopping.getNum()%></div>
        <a href="/item?pid=<%=product.getId()%>">商品链接</a>
        <a href="/shoppingcart/remove?sid=<%=shopping.getSid()%>">删除</a>
        <div>=======================</div>
      </div>
        <%}%>
    <%}%>

    <% if(shoppings_used.size() != 0){ %>
      <a href="/shoppingcart/buy">购买全部</a>
    <%} else {%>
      <% if(user == null){ %>
      <h3>要登录才能加入购物车</h3>
      <%} else {%>
      <h3>购物车已经清空</h3>
      <%}%>
    <%}%>
    
  </body>
</html>
