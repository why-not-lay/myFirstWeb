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
    <div><%=product.getPrice%></div>
    <form action="/item/update" method="post">
      <input type="hidden" name="pid" value="<%=product.getId()%>">
      数量: <input type="num" name="num" value="<%%product.getNum()>">
      <input type="submit" value="Submit">
    </form>

    <div>==========================================================</div>
    	
  </body>
</html>
