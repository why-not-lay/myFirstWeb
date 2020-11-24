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

  ArrayList<Records_trade> trades_income = (ArrayList<Records_trade>)request.getAttribute("trades_income");
  Integer income = (Integer)request.getAttribute("income");
%>
<html>
  <head>
    <link rel="stylesheet" href="/css/product.css">
    <link rel="stylesheet" href="/css/shoppingcart.css">
    <link rel="stylesheet" href="/css/topbar.css">
    <style type="text/css">
#person{
  font-size:25px;
  text-align: center;
}
    </style>
  </head>
  <body>
    <div id="topbar">
      <div>
        <ul>
    <% if(user == null){ %> 
          <li><a href="/login" >登录</a></li>
    <%} else {%>
          <li><a href="/index" >主页</a></li>
          <li><a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a></li>
          <li><a href="/seller" >我要卖</a></li>
          <li><a href="/shoppingcart" >购物车</a></li>
    <%}%>
        </ul>
      </div>
    </div>
    <div id="person">
      <h2>个人信息</h2>
      <p>用户名:<%=user.getName()%></p>
      <p>邮箱:<%=user.getEmail()%></p>
    </div>


    <h2>个人商品</h2>
    <% if(products == null || products.size() == 0){ %>
    <h2>当前没有商品</h2>
    <%} else {%>
    <table class="products">
      <tr>
        <th>商品名</th>
        <th>商品价格</th>
        <th>商品数量</th>
        <th>功能</th>
      </tr>
      <%for (int i = 0; i < products.size(); i++){%>
      <% Product product = products.get(i); %>
      <tr>
        <th><%=product.getName()%></th>
        <th><%=product.getPrice()%></th>
        <th><%=product.getNum()%></th>
        <th class="func"><a href="/item?pid=<%=product.getId()%>">商品页</a></th>
      </tr>
      <%}%>
    </table>
    <div class="page">
      <ul>
      <% if(page_sum_products == null){ %>
      <li><a href="/user?uid=<%=user.getId()%>&page_products=0" >1</a></li>
      <%} else {%>
        <% int all_page = page_sum_products/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
            <li><a href="/user?uid=<%=user.getId()%>&page_products=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>

          
    <h2>今日营业额</h2>
    <% if(trades_income == null || trades_income.size() == 0){ %>
    <h2>今日暂无收入</h2>
    <%} else {%>
    <table class="products">
      <tr>
        <th>订单号</th>
        <th>商品号</th>
        <th>交易数量</th>
        <th>交易额</th>
      </tr>
      <%for (int i = 0; i < trades_income.size(); i++){%>
      <%Records_trade trade = trades_income.get(i);%>
      <tr>
        <th><%=trade.getTid()%></th>
        <th><%=trade.getPid()%></th>
        <th><%=trade.getNum()%></th>
        <th><%=trade.getCost()%></th>
      </tr>
      <%}%>
      <tr>
        <th colspan="2" >今日营业额</th>
        <th colspan="2" ><%=income%></th>
      </tr>
    </table>
    <%}%>

    <h2>浏览商品</h2>
    <% if(products_view == null || products_view.size() == 0){ %>
    <h2>没有浏览过的商品</h2>
    <%} else {%>
    <table class="products">
      <tr>
        <th>商品名</th>
        <th>商品价格</th>
        <th>日期</th>
        <th colspan="2">功能</th>

      </tr>
      <%for (int i = 0; i < products_view.size(); i++){%>
      <%Records_view view = views.get(i);%>
      <%Product product = products_view.get(i);%>
      <tr>
        <th><%=product.getName()%></th>
        <th><%=product.getPrice()%></th>
        <th><%=view.getDate()%></th>
        <th class="func"><a href="/item?pid=<%=product.getId()%>">商品页</a></th>
        <th class="func"><a href="/removeview?vid=<%=view.getVid()%>">删除</a></th>
      </tr>
      <%}%>
    </table>
    <div class="page">
      <ul>
      <% if(page_sum_views == null){ %>
        <li><a href="/user?uid=<%=user.getId()%>&page_views=0" >1</a></li>
      <%} else {%>
        <% int all_page = page_sum_views/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
        <li><a href="/user?uid=<%=user.getId()%>&page_views=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>

    <h2>购买记录</h2>
    <% if(products_trade == null || products_trade.size() == 0){ %>
    <h2>没有购买过的商品</h2>
    <%} else {%>
    <table class="products">
      <tr>
        <th>商品名</th>
        <th>商品价格</th>
        <th>商品数量</th>
        <th>购买总价</th>
        <th colspan="2">功能</th>
      </tr>
      <%for (int i = 0; i < products_trade.size(); i++){%>
      <%Records_trade trade = trades.get(i);%>
      <%Product product = products_trade.get(i);%>
      <tr>
        <th><%=product.getName()%></th>
        <th><%=product.getPrice()%></th>
        <th><%=trade.getNum()%></th>
        <th><%=trade.getCost()%></th>
        <th class="func"><a href="/item?pid=<%=product.getId()%>">商品页</a></th>
        <th class="func"><a href="/removetrade?tid=<%=trade.getTid()%>">删除</a></th>
      </tr>
      <%}%>
    </table>
    <div class="page">
      <ul>
      <% if(page_sum_trades == null){ %>
      <li><a href="/user?uid=<%=user.getId()%>&page_trades=0" >1</a></li>
      <%} else {%>
        <% int all_page = page_sum_trades/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
      <li><a href="/user?uid=<%=user.getId()%>&page_trades=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>
        

  </body>
</html>
