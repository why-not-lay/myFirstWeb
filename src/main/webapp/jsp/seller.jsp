<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>

<html>
  <%
    User user = (User)request.getSession().getAttribute("user");

    ArrayList<Product> products_onshelf = (ArrayList<Product>)request.getAttribute("products_onshelf");
    ArrayList<Product> products_offshelf = (ArrayList<Product>)request.getAttribute("products_offshelf");
    ArrayList<Product> products_soldout = (ArrayList<Product>)request.getAttribute("products_soldout");

    Integer sum_onshelf = (Integer)request.getAttribute("sum_onshelf");
    Integer sum_offshelf = (Integer)request.getAttribute("sum_offshelf");
    Integer sum_soldout = (Integer)request.getAttribute("sum_soldout");

    Integer sum_trades = (Integer)request.getAttribute("sum_trades");
    Integer sum_views = (Integer)request.getAttribute("sum_views");

    ArrayList<Records_view> views = (ArrayList<Records_view>)request.getAttribute("views");
    ArrayList<Records_trade> trades = (ArrayList<Records_trade>)request.getAttribute("trades");

    ArrayList<String> users_view = (ArrayList<String>)request.getAttribute("users_view");
    ArrayList<String> users_trade = (ArrayList<String>)request.getAttribute("users_trade");
  %>
  <head>
    <link rel="stylesheet" href="/css/product.css">
    <link rel="stylesheet" href="/css/shoppingcart.css">
    <link rel="stylesheet" href="/css/topbar.css">
    <style type="text/css" >
#add{
  text-align:center;
  font-size:25px;
}
    </style>
  </head>
  <body>
    <div id="topbar">
      <div>
        <ul>
          <li><a href="/logout" >登出</a></li>
          <li><a href="/" >back</a></li>
          <li><a href="/shoppingcart" >购物车</a></li>
        </ul>
      </div>
    </div>
    
    
    <h2>上架商品</h2>
    <% if(products_onshelf == null || products_onshelf.size() == 0){ %>
    <h2>当前没有商品</h2>
    <%} else {%>
    <table class="products">
      <tr>
        <th>商品名</th>
        <th>商品描述</th>
        <th>商品价格</th>
        <th>商品数目</th>
        <th colspan="4">功能</th>
      </tr>
      <%for (int i = 0; i < products_onshelf.size(); i++){%>
      <%Product product = products_onshelf.get(i);%>
      <tr>
        <form action="/seller/update" method="post">
          <input type="hidden" name="pid" value="<%=product.getId()%>">
          <input type="hidden" name="uid" value="<%=user.getId()%>">
          <th><input type="text" name="product_name" value="<%=product.getName()%>"></th>
          <th><input type="text" name="product_description" value="<%=product.getDescription()%>"></th>
          <th><input type="number" name="product_price" value="<%=product.getPrice()%>"></th>
          <th><input type="number" name="product_num" value="<%=product.getNum()%>"></th>
          <th class="func"><a href="/seller/offshelf?pid=<%=product.getId()%>">下架</a></th>
          <th class="func"><a href="/item?pid=<%=product.getId()%>">商品页</a></th>
          <th class="func"><a href="/seller/remove?uid=<%=user.getId()%>&pid=<%=product.getId()%>">删除</a></th>
          <th><input type="submit" value="修改"></th>
        </form>
      </tr>
      <%}%>
    </table>
    <div class="page">
      <ul>
      <% if(sum_onshelf == null){ %>
      <li><a href="/user?uid=<%=user.getId()%>&page_onshlf=0" >1</a></li>
      <%} else {%>
        <% int all_page = sum_onshelf/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
            <li><a href="/user?uid=<%=user.getId()%>&page_onshlf=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>
    


    <h2>售完商品</h2>
    <% if(products_soldout == null || products_soldout.size() == 0){ %>
    <h2>当前没有商品</h2>
    <%} else {%>
    <table class="products">
      <tr>
        <th>商品名</th>
        <th>商品描述</th>
        <th>商品价格</th>
        <th>商品数目</th>
        <th colspan="4">功能</th>
      </tr>
      <%for (int i = 0; i < products_soldout.size(); i++){%>
      <%Product product = products_soldout.get(i);%>
      <tr>
        <form action="/seller/update" method="post">
          <input type="hidden" name="pid" value="<%=product.getId()%>">
          <input type="hidden" name="uid" value="<%=user.getId()%>">
          <th><input type="text" name="product_name" value="<%=product.getName()%>"></th>
          <th><input type="text" name="product_description" value="<%=product.getDescription()%>"></th>
          <th><input type="number" name="product_price" value="<%=product.getPrice()%>"></th>
          <th><input type="number" name="product_num" value="<%=product.getNum()%>"></th>
          <th class="func"><a href="/seller/offshelf?pid=<%=product.getId()%>">下架</a></th>
          <th class="func"><a href="/item?pid=<%=product.getId()%>">商品页</a></th>
          <th class="func"><a href="/seller/remove?uid=<%=user.getId()%>&pid=<%=product.getId()%>">删除</a></th>
          <th><input type="submit" value="修改"></th>
        </form>
      </tr>
      <%}%>
    </table>
    <div class="page">
      <ul>
      <% if(sum_soldout == null){ %>
      <li><a href="/user?uid=<%=user.getId()%>&page_soldout=0" >1</a></li>
      <%} else {%>
        <% int all_page = sum_soldout/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
            <li><a href="/user?uid=<%=user.getId()%>&page_soldout=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>


    <h2>下架商品</h2>
    <% if(products_offshelf == null || products_offshelf.size() == 0){ %>
    <h2>当前没有商品</h2>
    <%} else {%>
    <table class="products">
      <tr>
        <th>商品名</th>
        <th>商品描述</th>
        <th>商品价格</th>
        <th>商品数目</th>
        <th colspan="4">功能</th>
      </tr>
      <%for (int i = 0; i < products_offshelf.size(); i++){%>
      <%Product product = products_offshelf.get(i);%>
      <tr>
        <form action="/seller/update" method="post">
          <input type="hidden" name="pid" value="<%=product.getId()%>">
          <input type="hidden" name="uid" value="<%=user.getId()%>">
          <th><input type="text" name="product_name" value="<%=product.getName()%>"></th>
          <th><input type="text" name="product_description" value="<%=product.getDescription()%>"></th>
          <th><input type="number" name="product_price" value="<%=product.getPrice()%>"></th>
          <th><input type="number" name="product_num" value="<%=product.getNum()%>"></th>
          <th class="func"><a href="/seller/onshelf?pid=<%=product.getId()%>">上架</a></th>
          <th class="func"><a href="/item?pid=<%=product.getId()%>">商品页</a></th>
          <th class="func"><a href="/seller/remove?uid=<%=user.getId()%>&pid=<%=product.getId()%>">删除</a></th>
          <th><input type="submit" value="修改"></th>
        </form>
      </tr>
      <%}%>
    </table>
    <div class="page">
      <ul>
      <% if(sum_offshelf == null){ %>
      <li><a href="/user?uid=<%=user.getId()%>&page_offshelf=0" >1</a></li>
      <%} else {%>
        <% int all_page = sum_offshelf/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
            <li><a href="/user?uid=<%=user.getId()%>&page_offshelf=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>

    <h2>添加商品</h2>
    <div id="add">
      <form action="/seller/add" method="post">
        商品名:<input type="text" name="product_name">
        商品价格:<input type="number" name="product_price">
        商品描述:<input type="text" name="product_description">
        商品数量:<input type="number" name="product_num">
        <input type="submit" value="提交">
      </form>
    </div>

    <h2>浏览记录</h2>
    <% if(users_view == null || users_view.size() == 0){ %>
    <h2>当前没有浏览记录</h2>
    <%} else {%>
    <table class="products">
      <tr>
        <th>客户名</th>
        <th>商品id</th>
        <th>日期</th>
        <th>功能</th>
      </tr>
      <%for (int i = 0; i < users_view.size(); i++){%>
      <%Records_view view = views.get(i);%>
      <%String name = users_view.get(i);%>
      <tr>
        <th><%=name%></th>
        <th><%=view.getPid()%></th>
        <th><%=view.getDate()%></th>
        <th class="func"><a href="/item?pid=<%=view.getPid()%>">商品页</a></th>
      </tr>
      <%}%>
    </table>
    <div class="page">
      <ul>
      <% if(sum_views == null){ %>
      <li><a href="/seller?uid=<%=user.getId()%>&page_view=0" >1</a></li>
      <%} else {%>
        <% int all_page = sum_views/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
      <li><a href="/seller?uid=<%=user.getId()%>&page_view=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>


    <h2>购买记录</h2>
    <% if(users_trade == null || users_trade.size() == 0){ %>
    <h2>当前没有购买记录</h2>
    <%} else {%>
    <table class="products">
      <tr>
        <th>客户名</th>
        <th>商品id</th>
        <th>日期</th>
        <th>功能</th>
      </tr>
      <%for (int i = 0; i < users_trade.size(); i++){%>
      <%Records_trade trade = trades.get(i);%>
      <%String name = users_trade.get(i);%>
      <tr>
        <th><%=name%></th>
        <th><%=trade.getPid()%></th>
        <th><%=trade.getDate_trade()%></th>
        <th class="func"><a href="/item?pid=<%=trade.getPid()%>">商品页</a></th>
      </tr>
      <%}%>
    </table>
    <div class="page">
      <ul>
      <% if(sum_trades == null){ %>
      <li><a href="/seller?uid=<%=user.getId()%>&page_trade=0" >1</a></li>
      <%} else {%>
        <% int all_page = sum_trades/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
      <li><a href="/seller?uid=<%=user.getId()%>&page_trade=<%=i%>" ><%=i+1%></a></li>
        <%}%>
      <%}%>
      </ul>
    </div>
    <%}%>





  </body>
</html>
