<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<% 
  //User user = (User)request.getAttribute("user"); 
  User user = (User)request.getSession().getAttribute("user");
  User new_user = (User)request.getAttribute("new_user");
  ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
  Integer all = (Integer)request.getAttribute("all");
  
%>
<html>
  <body>
    <a href="/index" >返回</a>
    <a href="/seller" >我要卖</a>
    <a href="#" >购物车</a>
    <% if(user == null){ %>
      <a href="/login" >登录</a>
    <%} else {%>
      <a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a>
    <%}%>
    
    <h3>个人信息</h3>
    用户名:<div><%=new_user.getName()%></div>
    邮箱:<div><%=new_user.getEmail()%></div>
    <h3>个人商品</h3>
    <% if(products == null){ %>
    <div>当前没有商品</div>
    <%} else {%>
      <%for (int i = 0; i < products.size(); i++){%>
    <div>
      <div>==========================================================</div>
      <%Product product = products.get(i);%>
      <div><% out.println(product.getName());%></div>
      <div><% out.println(product.getDescription());%></div>
      <div><% out.println(product.getPrice());%></div>
      <div><% out.println(product.getNum());%></div>
      <div>==========================================================</div>
    </div>
      <%}%>
      <% if(all == null){ %>
        <a href="/user?uid=<%=new_user.getId()%>&page=0" >1</a>
      <%} else {%>
        <% int all_page = all/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
          <a href="/user?uid=<%=new_user.getId()%>&page=<%=i%>" ><%=i+1%></a>
        <%}%>
      <%}%>
    <%}%>
    
    
    <% if(user != null && user.equals(new_user)){ %>
      <h3>浏览记录</h3>
      <h3>购买记录</h3>
    <%}%>
    
  </body>
</html>
