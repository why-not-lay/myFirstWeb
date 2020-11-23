<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<html>
  <%
    User user = (User)request.getSession().getAttribute("user");
    Product product = (Product)request.getAttribute("product");
  %>
  <head>
  <link rel="stylesheet" href="/css/topbar.css">
  <link rel="stylesheet" href="/css/item.css">
  </head>
  <body>
    <div id="topbar">
      <div>
        <ul>
          <li><a href="/logout" >登出</a></li>
          <li><a href="/index" >主页</a></li>
          <li><a href="/shoppingcart" >购物车</a></li>
          <li><a href="/seller" >我要卖</a></li>
          <li><a href="/user" ><%=user.getName()%></a></li>
        </ul>
      </div>
    </div>
    
    
    <div id="product">
      <div>
        <img src="" />
      </div>
      <p><%=product.getName()%></p>
      <p><%=product.getDescription()%></p>
      <div id="num"v>
    <% if(product.getStatus() == Status.Status_products.ON_SHELF){ %>
        <form action="/addshoppingcart" method="post">
          数量: <input type="num" name="num" value="1" min="1" max="<%=product.getNum()%>">
          <input type="submit" value="添加购物车">
        </form>
    <%} else {%>
        <div id="func">
      <% if(product.getStatus() == Status.Status_products.SOLD_OUT){ %>
        商品正缺货
      <%} else {%>
        商品已下架
      <%}%>
        </div>
    <%}%>
      </div>
    </div>
  </body>
</html>
