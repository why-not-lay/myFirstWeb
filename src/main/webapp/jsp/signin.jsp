<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/signin.css">
  </head>
  <body>
    <div id="signin">
      <form action="signin" method="post">
        <h2>创建新用户</h2>
        <input class="sign-text" type="text" name="username" placeholder="用户名">
        <input class="sign-text" type="email" name="email" placeholder="邮箱">
        <input class="sign-text" type="password" name="password" placeholder="新密码">
        <input class="sign-btn" type="submit" value="创建用户">
        <div class="sign-func">
          <h3>
            <a href="/index" >主页</a>
          </h3>
        </div>
        <div class="sign-func">
          <h3>
            <a href="/login" >登录</a>
          </h3>
        </div>
      </form>
    </div>
  </body>
</html>
