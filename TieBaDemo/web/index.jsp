<%--
  Created by IntelliJ IDEA.
  User: w_2
  Date: 2016-09-06
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="<%=request.getContextPath()%>/user/userRegister.do" method="post">
    用户名：<input name="userName"><br>
    密码：<input name="passWord"><br>
    头像：<input name="userIcon"><br>
    权限：<input name="userRoleId"><br>
    昵称：<input name="nickName"><br>
    <input type="submit">

  </form>
  </body>
</html>
