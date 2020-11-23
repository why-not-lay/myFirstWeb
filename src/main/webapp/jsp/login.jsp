<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myFirstWeb.bean.User" %>
<html>
  <head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/signin.css">
  </head>
  <body>
    <div id="signin">
      <form action="/login" method="post">
        <h2>登录账户</h2>
        <input class="sign-text" type="text" name="username" placeholder="用户名">
        <input class="sign-text" type="password" name="password" placeholder="密码">
        <input class="sign-btn" type="submit" value="登录">
        <div class="sign-func">
          <h3>
            <a href="/index" >主页</a>
          </h3>
        </div>
        <div class="sign-func">
          <h3>
            <a href="/signin" >注册</a>
          </h3>
        </div>
      </form>
    </div>
  </body>
</html>
