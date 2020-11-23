<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%
User user = (User)request.getSession().getAttribute("user");

ArrayList<Product> products = (ArrayList<Product>)request.getSession().getAttribute("products_buy");
Integer cost_price = (Integer)request.getSession().getAttribute("cost_price");

%>
<html>
  <head>
    <link rel="stylesheet" href="/css/topbar.css">
    <link rel="stylesheet" href="/css/buy.css">
    <link rel="stylesheet" href="/css/product.css">
  <body>
    <div id="topbar">
      <div>
        <ul>
          <li><a href="/index" >主页</a></li>
          <li><a href="/seller" >我要卖</a></li>
          <li><a href="/shoppingcart" >购物车</a></li>
          <li><a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a></li>
        </ul>
      </div>
    </div>
    

    <div id="content">
      <h2>订单详情</h2>
      <table class="products">
        <tr>
          <th>商品名</th>
          <th>商品单价</th>
          <th>购买数量</th>
          <th>总价格</th>
        </tr>
    <%for (int i = 0; i < products.size(); i++){%>
    <% Product product = products.get(i); %>
        <tr>
          <th><%=product.getName()%></th>
          <th><%=product.getPrice()%></th>
          <th><%=product.getNum()%></th>
          <th><% out.println(product.getPrice() * product.getNum()); %></th>
        </tr>
    <%}%>
        <th colspan="2">总花费</th>
        <th ><%=cost_price%></th>
        <th ><a href="/shoppingcart/pay?delete=1">取消订单</a></th>
      </table>
      <h2>支付方式</h2>

      <div id="pay">
        <a href="/shoppingcart/pay">微信</a>
        <a href="/shoppingcart/pay">支付宝</a>
        <a href="/shoppingcart/pay">货到付款</a>
      </div>
    </div>
  </body>
</html>
