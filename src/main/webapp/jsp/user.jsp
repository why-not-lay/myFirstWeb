<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<% 
  User user = (User)request.getSession().getAttribute("user");

  ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
  ArrayList<Records_view> views = (ArrayList<Records_view>)request.getAttribute("views");
  ArrayList<Records_trade> trades = (ArrayList<Records_trade>)request.getAttribute("trades");

  ArrayList<Product> products_view = (ArrayList<Product>)request.getAttribute("products_view");
  ArrayList<Product> products_trade = (ArrayList<Product>)request.getAttribute("products_trade");

  Integer page_sum_views = (Integer)request.getAttribute("page_sum_views");
  Integer page_sum_trades= (Integer)request.getAttribute("page_sum_trades");
  Integer page_sum_products= (Integer)request.getAttribute("page_sum_products");
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
    
    <h3>个人信息</h3>
    用户名:<div><%=user.getName()%></div>
    邮箱:<div><%=user.getEmail()%></div>
    <h3>个人商品</h3>
    <% if(products == null || products.size() == 0){ %>
    <div>当前没有商品</div>
    <%} else {%>
      <%for (int i = 0; i < products.size(); i++){%>
    <div>
      <div>==========================================================</div>
      <%Product product = products.get(i);%>
      <div><%=product.getName()%></div>
      <div><%=product.getDescription()%></div>
      <div><%=product.getPrice()%></div>
      <div><%=product.getNum()%></div>
      <a href="/item?pid=<%=product.getId()%>">商品页</a>
      <div>==========================================================</div>
    </div>
      <%}%>
      <% if(page_sum_products == null){ %>
        <a href="/user?uid=<%=user.getId()%>&page_products=0" >1</a>
      <%} else {%>
        <% int all_page = page_sum_products/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
          <a href="/user?uid=<%=user.getId()%>&page_products=<%=i%>" ><%=i+1%></a>
        <%}%>
      <%}%>
    <%}%>


    <h3>浏览商品</h3>
    <% if(products_view == null || products_view.size() == 0){ %>
    <div>没有浏览过的商品</div>
    <%} else {%>
      <%for (int i = 0; i < products_view.size(); i++){%>
    <div>
      <div>==========================================================</div>
      <%Records_view view = views.get(i);%>
      <%Product product = products_view.get(i);%>
      <div><%=product.getName()%></div>
      <div><%=product.getPrice()%></div>
      <div><%=view.getDate()%></div>
      <a href="/item?pid=<%=product.getId()%>">商品页</a>
      <a href="/removeview?vid=<%=view.getVid()%>">删除</a>
      <div>==========================================================</div>
    </div>
      <%}%>
      <% if(page_sum_views == null){ %>
        <a href="/user?uid=<%=user.getId()%>&page_views=0" >1</a>
      <%} else {%>
        <% int all_page = page_sum_views/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
          <a href="/user?uid=<%=user.getId()%>&page_views=<%=i%>" ><%=i+1%></a>
        <%}%>
      <%}%>
    <%}%>

    <h3>购买记录</h3>
    <% if(products_trade == null || products_trade.size() == 0){ %>
    <div>没有购买过的商品</div>
    <%} else {%>
      <%for (int i = 0; i < products_trade.size(); i++){%>
    <div>
      <div>==========================================================</div>
      <%Records_trade trade = trades.get(i);%>
      <%Product product = products_trade.get(i);%>
      <div><%=product.getName()%></div>
      <div><%=product.getPrice()%></div>
      <div><%=trade.getNum()%></div>
      <div><%=trade.getCost()%></div>
      <a href="/item?pid=<%=product.getId()%>">商品页</a>
      <a href="/removetrade?tid=<%=trade.getTid()%>">删除</a>
      <div>==========================================================</div>
    </div>
      <%}%>
      <% if(page_sum_trades == null){ %>
        <a href="/user?uid=<%=user.getId()%>&page_trades=0" >1</a>
      <%} else {%>
        <% int all_page = page_sum_trades/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
          <a href="/user?uid=<%=user.getId()%>&page_trades=<%=i%>" ><%=i+1%></a>
        <%}%>
      <%}%>
    <%}%>


  </body>
</html>
