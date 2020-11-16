<%@page language="java" contentType="text/html;charser=UTF-8" pageEncoding="UTF-8"%>
<html>
  <body>
    <a href="/index" >back</a>
    <a href="/login" >login</a>
    <form action="signin" method="post">
      Username: <input type="text" name="username">
      <br />
      Email: <input type="text" name="email">
      <br />
      NewPassword: <input type="text" name="password">
      <br />
      RepeatPassword: <input type="text" name="new_password">
      <input type="submit" value="Submit">
    </form>
  </body>
</html>
