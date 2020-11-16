<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.User" %>
<html>
  <body>
    <a href="/index" >back</a>
    <a href="/signin" >signin</a>
    <form action="login" method="post">
      Username: <input type="text" name="username">
      <br />
      Password: <input type="text" name="password">
      <input type="submit" value="Submit">
    </form>
  </body>
</html>
