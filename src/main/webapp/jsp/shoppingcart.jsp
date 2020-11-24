<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%
User user = (User)request.getSession().getAttribute("user");

ArrayList<Records_shopping_cart> shoppings_used = (ArrayList<Records_shopping_cart>)request.getAttribute("shoppings_used");
ArrayList<Records_shopping_cart> shoppings_offshelf = (ArrayList<Records_shopping_cart>)request.getAttribute("shoppings_offshelf");
ArrayList<Records_shopping_cart> shoppings_not_enough = (ArrayList<Records_shopping_cart>)request.getAttribute("shoppings_not_enough");

ArrayList<Product> products_used = (ArrayList<Product>)request.getAttribute("products_used");
ArrayList<Product> products_offshelf = (ArrayList<Product>)request.getAttribute("products_offshelf");
ArrayList<Product> products_not_enough = (ArrayList<Product>)request.getAttribute("products_not_enough");

Integer sum_used = (Integer)request.getAttribute("sum_used");
Integer sum_offshelf = (Integer)request.getAttribute("sum_offshelf");
Integer sum_not_enough = (Integer)request.getAttribute("sum_not_enough");
%>
<html>
  <head>
    <link rel="stylesheet" href="/css/shoppingcart.css">
    <link rel="stylesheet" href="/css/product.css">
    <link rel="stylesheet" href="/css/topbar.css">
  </head>
  <body>

    <div id="topbar">
      <div>
        <ul>
    <% if(user == null){ %> 
    <li><a href="/login" >登录</a></li>
    <%} else {%>
    <li><a href="/index" >主页</a></li>
    <li><a href="/seller" >我要卖</a></li>
    <li><a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a></li>
    <li><a href="/logout" >登出</a></li>
    <%}%>
        </ul>
      </div>
    </div>
    
    
    <hr>
    <h1>购物车</h1>
    <hr>
    <% if(shoppings_used != null && shoppings_used.size() != 0){ %>
    <table class="products">
      <tr>
        <th>商品名</th>
        <th>商品价格</th>
        <th>购买数量</th>
        <th colspan="2">功能</th>
        <th><a id="buy_all" href="/shoppingcart/buy">购买全部</a></th>

      </tr>
        <%for (int i = 0; i < shoppings_used.size(); i++){%>
          <% Product product = products_used.get(i); %>
          <% Records_shopping_cart shopping = shoppings_used.get(i); %>
      <tr>
        <th><%=product.getName()%></th>
        <th><%=product.getPrice()%></th>
        <th><%=shopping.getNum()%></th>
        <th class="func"><a href="/shoppingcart/remove?sid=<%=shopping.getSid()%>">删除</a></th>
        <th class="func"><a href="/item?pid=<%=product.getId()%>">商品页</a></th>
        <th class="func"><a href="/shoppingcart/buy?sid=<%=shopping.getSid()%>">购买</a></th>
      </tr>
        <%}%>
    </table>

    <div class="page">
      <ul>
        <% if(sum_used == null){ %>
        <li><a href="/shoppingcart?page_used=0" >1</a></li>
        <%} else {%>
        <% int all_page = sum_used/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
        <li><a href="/shoppingcart?page_used=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%} else {%>
    <h1>购物车已经清空</h1>
    <%}%>
    
    <% if(shoppings_not_enough != null && shoppings_not_enough.size() != 0){ %>
    <h2>缺货中</h2>
    <table class="products">
      <tr>
        <th>商品名</th>
        <th>商品价格</th>
        <th>购买数量</th>
        <th colspan="3">功能</th>
      </tr>
        <%for (int i = 0; i < shoppings_not_enough.size(); i++){%>
          <% Product product = products_not_enough.get(i); %>
          <% Records_shopping_cart shopping = shoppings_not_enough.get(i); %>
      <tr>
        <th><%=product.getName()%></th>
        <th><%=product.getPrice()%></th>
        <th><%=shopping.getNum()%></th>
        <th class="func"><a href="/item?pid=<%=product.getId()%>">商品页</a></th>
        <th class="func"><a href=/shoppingcart/remove?sid=<%=shopping.getSid()%>"">删除</a></th>
      </tr>
        <%}%>
    </table>
    <div class="page">
      <ul>
        <% if(sum_not_enough == null){ %>
        <li><a href="/shoppingcart?sum_not_enough=0" >1</a></li>
        <%} else {%>
        <% int all_page = sum_not_enough/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
        <li><a href="/shoppingcart?sum_not_enough=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>



    <% if(shoppings_offshelf != null && shoppings_offshelf.size() != 0){ %>
    <h2>已经下架</h2>
    <table class="products">
      <tr>
        <th>商品名</th>
        <th>商品价格</th>
        <th>购买数量</th>
        <th colspan="3">功能</th>
      </tr>
        <%for (int i = 0; i < shoppings_offshelf.size(); i++){%>
          <% Product product = products_offshelf.get(i); %>
          <% Records_shopping_cart shopping = shoppings_offshelf.get(i); %>
      <tr>
        <th><%=product.getName()%></th>
        <th><%=product.getPrice()%></th>
        <th><%=shopping.getNum()%></th>
        <th class="func"><a href="/item?pid=<%=product.getId()%>">商品主页</a></th>
        <th class="func"><a href="/shoppingcart/remove?sid=<%=shopping.getSid()%>">删除</a></th>
      </tr>
        <%}%>
    </table>
    <div class="page">
      <ul>
        <% if(sum_offshelf == null){ %>
        <li><a href="/shoppingcart?sum_offshelf=0" >1</a></li>
        <%} else {%>
        <% int all_page = sum_offshelf/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
        <li><a href="/shoppingcart?sum_offshelf=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>
    
  </body>
</html>
