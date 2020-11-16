<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.myFirstWeb.bean.client"%>

<html>
  <body>
    <%
      client user = (client)request.getAttribute("user");
      out.print(user.getName());
    %>
  </body>
</html>
