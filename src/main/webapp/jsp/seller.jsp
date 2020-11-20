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

  %>
  <body>
    <a href="/logout" >登出</a>
    <a href="/" >back</a>

    <h3>上架商品<h3>
    <% if(products_onshelf == null || products_onshelf.size() == 0){ %>
    <div>当前没有商品</div>
    <%} else {%>
      <%for (int i = 0; i < products_onshelf.size(); i++){%>
    <div>
      <div>==========================================================</div>
      <%Product product = products_onshelf.get(i);%>
      <form action="seller/update?pid=<%=product.getId()%>&uid=<%=user.getId()%>" method="post">
        商品名:<input type="text" name="product_name" value="<%=product.getName()%>">
        商品价格:<input type="number" name="product_price" value="<%=product.getPrice()%>">
        商品描述:<input type="text" name="product_description" value="<%=product.getDescription()%>">
        商品数量:<input type="number" name="product_num" value="<%=product.getNum()%>">
        <input type="submit" value="修改">
      </form>
      <a href="/seller/offshelf?pid=<%=product.getId()%>">下架</a>
      <a href="/item?pid=<%=product.getId()%>">商品页</a>
      <a href="/seller/remove?uid=<%=user.getId()%>&pid=<%=product.getId()%>">删除</a>
      <div>==========================================================</div>
    </div>
      <%}%>
      <% if(sum_onshelf == null){ %>
        <a href="/seller?page_onshlf=0" >1</a>
      <%} else {%>
        <% int all_page = sum_onshelf/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
          <a href="/seller?page_onshlf=<%=i%>" ><%=i+1%></a>
        <%}%>
      <%}%>
    <%}%>


    <h3>售完商品</h3>
    <% if(products_soldout == null || products_soldout.size() == 0){ %>
    <div>当前没有商品</div>
    <%} else {%>
      <%for (int i = 0; i < products_soldout.size(); i++){%>
    <div>
      <div>==========================================================</div>
      <%Product product = products_soldout.get(i);%>
      <form action="/seller/update?pid=<%=product.getId()%>" method="post">
        商品名:<input type="text" name="product_name" value="<%=product.getName()%>">
        商品价格:<input type="number" name="product_price" value="<%=product.getPrice()%>">
        商品描述:<input type="text" name="product_description" value="<%=product.getDescription()%>">
        商品数量:<input type="number" name="product_num" value="<%=product.getNum()%>">
        <input type="submit" value="修改">
      </form>
      <a href="/item?pid=<%=product.getId()%>">商品页</a>
      <a href="/seller/remove?uid=<%=user.getId()%>&pid=<%=product.getId()%>">删除</a>
      <div>==========================================================</div>
    </div>
      <%}%>
      <% if(sum_soldout == null){ %>
        <a href="/seller?page_soldout=0" >1</a>
      <%} else {%>
        <% int all_page = sum_soldout/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
          <a href="/seller?page_soldout=<%=i%>" ><%=i+1%></a>
        <%}%>
      <%}%>
    <%}%>


    <h3>下架商品</h3>
    <% if(products_offshelf == null || products_offshelf.size() == 0){ %>
    <div>当前没有商品</div>
    <%} else {%>
      <%for (int i = 0; i < products_offshelf.size(); i++){%>
    <div>
      <div>==========================================================</div>
      <%Product product = products_offshelf.get(i);%>
      <form action="/seller/update?pid=<%=product.getId()%>" method="post">
        商品名:<input type="text" name="product_name" value="<%=product.getName()%>">
        商品价格:<input type="number" name="product_price" value="<%=product.getPrice()%>">
        商品描述:<input type="text" name="product_description" value="<%=product.getDescription()%>">
        商品数量:<input type="number" name="product_num" value="<%=product.getNum()%>">
        <input type="submit" value="修改">
      </form>
      <a href="/seller/onshelf?pid=<%=product.getId()%>">上架</a>
      <a href="/item?pid=<%=product.getId()%>">商品页</a>
      <a href="/seller/remove?uid=<%=user.getId()%>&pid=<%=product.getId()%>">删除</a>
      <div>==========================================================</div>
    </div>
      <%}%>
      <% if(sum_offshelf == null){ %>
        <a href="/seller?page_offshelf=0" >1</a>
      <%} else {%>
        <% int all_page = sum_offshelf/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
          <a href="/seller?page_offshelf=<%=i%>" ><%=i+1%></a>
        <%}%>
      <%}%>
    <%}%>


    <h3>添加商品</h3>
    <form action="seller/add" method="post">
      商品名:<input type="text" name="product_name">
      商品价格:<input type="number" name="product_price">
      商品描述:<input type="text" name="product_description">
      商品数量:<input type="number" name="product_num">
      <input type="submit" value="提交">
    </form>
  </body>
</html>
