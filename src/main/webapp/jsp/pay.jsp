<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%
User user = (User)request.getSession().getAttribute("user");
%>
<html>
  <body>
    <a href="/index" >返回</a>
    <a href="/seller" >我要卖</a>
    <a href="/shoppingcart" >购物车</a>
    <% if(user == null){ %> <a href="/login" >登录</a>
    <%} else {%>
      <a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a>
    <%}%>
    <h3>订单已成功支付,订单将发送至你的邮箱中</h3>
    <a href="/index">返回主页</a>
  </body>
</html>
