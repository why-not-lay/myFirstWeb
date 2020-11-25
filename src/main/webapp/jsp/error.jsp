<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%
  User user = (User)request.getSession().getAttribute("user");
  String error = (String)request.getAttribute("error");
%>

<html>
  <head>
    <link rel="stylesheet" href="/css/topbar.css">
    <style type="text/css" >
h1{
  text-align:center;
}
    </style>

  </head>
  <body>
    <div id="topbar">
      <div>
        <ul>
          <% if(user != null){ %>
          <li><a href="/seller" >我要卖</a></li>
          <li><a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a></li>
          <li><a href="/shoppingcart" >购物车</a></li>
          <li><a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a></li>
          <li><a href="/logout" >登出</a></li>
          <% }else {%>
          <li><a href="/index" >主页</a></li>
          <li><a href="/login" >登录</a></li>
          <li><a href="/signin" >注册</a></li>
          <% } %>
        </ul>
      </div>
     </div>
      <h1><%=error%></h1>
  </body>
</html>
