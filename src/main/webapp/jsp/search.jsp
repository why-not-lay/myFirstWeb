<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%
  User user = (User)request.getSession().getAttribute("user");
  ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
%>
<html>
  <head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/topbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">
  </head>
  <body>
    <div id="topbar" >
      <div>
        <ul>
    <% if(user != null){ %>
          <li><a href="/seller" >我要卖</a></li>
          <li><a href="/index" >主页</a></li>
          <li><a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a></li>
          <li><a href="/shoppingcart" >购物车</a></li>
          <li><a href="/logout" >Logout</a></li>
    <% }else {%>
          <li><a href="/login" >login</a></li>
          <li><a href="/signin" >signin</a></li>
    <% } %>
        </ul>
      </div>
    </div>

    <div id="search">
      <form action="/search" method="post">
        <input type="text" name="content_search">
        <input type="submit" value="查询">
      </form>
    </div>


    <div id="container">
    <% if(products == null || products.size() == 0){ %>
      <h1>当前没有商品</h1>
    <%} else {%>
      <%for (Product product: products){%>
      <div class="product">
        <div>
          <img src="" />
        </div>
        <div class="product_name"><%=product.getName()%></div>
        <div class="product_price"><%=product.getPrice()%></div>
        <a href="/addshoppingcart?pid=<%=product.getId()%>&num=1" >添加到购物车</a>
        <a href="/item?pid=<%=product.getId()%>" >商品主页</a>
      </div>
    <%}%>
    <%}%>
    </div>

  </body>
</html>
