<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.*" %>
<%@ page import="java.util.ArrayList" %>

<html>
  <%
    //User user = (User)request.getAttribute("user");
    User user = (User)request.getSession().getAttribute("user");
    ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
    Integer all = (Integer)request.getAttribute("all");
  %>
  <body>
    <a href="/logout" >登出</a>
    <a href="/" >back</a>
    <% if(products == null || products.size() == 0){ %>
    <div>当前没有商品</div>
    <%} else {%>
      <%for (int i = 0; i < products.size(); i++){%>
    <div>
      <div>==========================================================</div>
      <%Product product = products.get(i);%>
      <div><% out.println(product.getName());%></div>
      <div><% out.println(product.getDescription());%></div>
      <div><% out.println(product.getPrice());%></div>
      <div><% out.println(product.getNum());%></div>
      <a href="/seller/remove?uid=<%=user.getId()%>&pid=<%=product.getId()%>">删除</a>
      <div>==========================================================</div>
    </div>
      <%}%>
      <% if(all == null){ %>
        <a href="/seller?page=0" >1</a>
      <%} else {%>
        <% int all_page = all/10; %>
        <%for (int i = 0; i <= all_page; i++){%>
          <a href="/seller?page=<%=i%>" ><%=i+1%></a>
        <%}%>
      <%}%>
    <%}%>
    <h3>插入商品</h3>
    <form action="seller/add" method="post">
      商品名:<input type="text" name="product_name">
      商品价格:<input type="number" name="product_price">
      商品描述:<input type="text" name="product_description">
      商品数量:<input type="number" name="product_num">
      <input type="submit" value="提交">
    </form>
  </body>
</html>
