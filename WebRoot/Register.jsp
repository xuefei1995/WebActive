<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html">
<html>
  <head>   
    <title>用户注册</title>   
  </head>
  <body>
    <form action="${pageContext.request.contextPath }/RegServlet" method="post">
    	用户名:<input type="text" name="name"><br>
    	密码:<input type="password" name="password"><br>
    	邮箱:<input type="text" name="mail"><br>
    	<input type="submit" value="注册">
    </form>
  </body>
</html>
