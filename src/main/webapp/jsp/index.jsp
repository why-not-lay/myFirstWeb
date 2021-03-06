<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<html>
  <%
    User user = (User)request.getSession().getAttribute("user");
    ArrayList<Product> products = (ArrayList)request.getAttribute("products");
    Integer all = (Integer)request.getAttribute("all");
  %>
  <head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/topbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">
  </head>
  <body>
    <div id="topbar">
      <div>
        <ul>
          <% if(user != null){ %>
          <li><a href="/seller" >我要卖</a></li>
          <li><a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a></li>
          <li><a href="/shoppingcart" >购物车</a></li>
          <li><a href="/logout" >登出</a></li>
          <% }else {%>
          <li><a href="/login" >登录</a></li>
          <li><a href="/signin" >注册</a></li>
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
    </div>
    <div id="page">
      <ul>
      <% if(all == null){ %>
        <li><a href="/index?page=0" >1</a></li>
      <%} else {%>
        <% int all_page = all/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
        <li><a href="/index?page=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>
    <div id="footer">

    </div>
  </body>
</html>
