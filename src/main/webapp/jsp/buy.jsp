<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%
User user = (User)request.getSession().getAttribute("user");

ArrayList<Product> products = (ArrayList<Product>)request.getSession().getAttribute("products_buy");
Integer cost_price = (Integer)request.getSession().getAttribute("cost_price");

%>
<html>
  <body>
    <a href="/index" >返回</a>
    <a href="/seller" >我要卖</a>
    <a href="#" >购物车</a>
    <% if(user == null){ %> <a href="/login" >登录</a>
    <%} else {%>
      <a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a>
    <%}%>

    <h3>订单详情</h3>
    <%for (int i = 0; i < products.size(); i++){%>
    <% Product product = =products.get(i); %>
      <div>========================================</div>
      商品名:<div><%=product.getName()%></div>
      商品单价:<div><%=product.getPrice()%></div>
      购买数目:<div><%=product.getNum()%></div>
      商品总价:<div><% out.println(product.getPrice() * product.getNum()); %></div>
      <div>========================================</div>
    <%}%>
    <h3>应付价格</h3>
    <div><%=cost_price%></div>
    <a href="/shoppingcart/pay?delete=1">取消订单</a>
    <h3>支付方式</h3>
    <a href="/shoppingcart/pay">微信</a>
    <a href="/shoppingcart/pay">支付宝</a>
    <a href="/shoppingcart/pay">货到付款</a>
  </body>
</html>
