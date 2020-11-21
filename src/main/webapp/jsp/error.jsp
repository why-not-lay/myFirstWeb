<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
  User user = (User)request.getSession().getAttribute("user");
%>

<html>
  <body>
    <% if(user != null){ %>
    <a href="/seller" >我要卖</a>
    <a href="/user?uid=<%=user.getId()%>" ><%=user.getName()%></a>
    <a href="#" >购物车</a>
    <a href="/logout" >Logout</a>
    <% }else {%>
    <a href="/login" >login</a>
    <a href="/signin" >signin</a>
    <% } %>
    <h3>有些错误发生了</h3>
  </body>
</html>
