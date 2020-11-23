<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%
User user = (User)request.getSession().getAttribute("user");
%>
<html>
  <head>
    <link rel="stylesheet" href="/css/topbar.css">
    <style type="text/css">
#content{
  text-align: center;
  position: relative;
  top:20%;
}
#content a{
  text-decoration:none;
  font-size:25px;
}
    </style>
  </head>
  <body>
    <div id="topbar">
      <div>
        <ul>
          <li><a href="/index" >返回</a></li>
          <li><a href="/seller" >我要卖</a></li>
          <li><a href="/shoppingcart" >购物车</a></li>
          <li><a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a></li>
        </ul>
      </div>
    </div>
    
    
    <div id="content">
      <h1>订单已成功支付,订单结果将发送至你的邮箱中</h1>
      <div id="back">
        <a href="/index">返回主页</a>
      </div>
    </div>
  </body>
</html>
