<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<html>
  <%
    //User user = (User)request.getAttribute("user");
    User user = (User)request.getSession().getAttribute("user");
    ArrayList<Product> products = (ArrayList)request.getAttribute("products");
    Integer all = (Integer)request.getAttribute("all");
  %>
  <body>
    <% if(user != null){ %>
    <a href="/seller" >我要卖</a>
    <a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a>
    <a href="#" >购物车</a>
    <h3>welcome <% out.println(user.getName()); %></h3>
    <a href="/logout" >Logout</a>
    <% }else {%>
    <a href="/login" >login</a>
    <a href="/signin" >signin</a>
    <% } %>
    <h3>商品浏览</h3>
    <% if(products == null || products.size() == 0){ %>
    <div>当前没有商品</div>
    <%} else {%>
      <%for (Product product: products){%>
      <div>======================================</div>
      <div><% out.println(product.getName());%></div>
      <div><% out.println(product.getDescription());%></div>
      <div><% out.println(product.getPrice());%></div>
      <div><% out.println(product.getNum());%></div>
      <a href="/user?uid=<%=product.getUid()%>" >卖家</a>
      <div>======================================</div>
      <%}%>
      <% if(all == null){ %>
      <a href="/index?page=0" >1</a>
      <%} else {%>
        <% int all_page = all/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
        <a href="/index?page=<%=i%>" ><%=i+1%></a>
        <%}%>
      <%}%>
    <%}%>
    
    
  </body>
</html>
