<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<html>
  <%
    User user = (User)request.getSession().getAttribute("user");
    Product product = (Product)request.getAttribute("product");
  %>
  <body>
    <a href="/logout" >登出</a>
    <a href="/index" >返回</a>
    <a href="/shoppingcart" >购物车</a>
    <a href="/seller" >我要卖</a>
    <a href="/user" ><%=user.getName()%></a>
    <div>==========================================================</div>
    <div><%=product.getName()%></div>
    <div><%=product.getDescription()%></div>
    <div><%=product.getPrice()%></div>
    <% if(product.getStatus() == Status.Status_products.ON_SHELF){ %>
      <form action="/addshoppingcart" method="post">
        <input type="hidden" name="pid" value="<%=product.getId()%>">
        数量: <input type="num" name="num" value="1" min="1" max="<%=product.getNum()%>">
        <input type="submit" value="添加购物车">
      </form>
    <%} else {%>
      <% if(product.getStatus() == Status.Status_products.SOLD_OUT){ %>
        商品正缺货
      <%} else {%>
        商品已下架
      <%}%>
    <%}%>
    <div>==========================================================</div>
    	
  </body>
</html>
