<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tab65
  Date: 29.04.2020
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Login</h3>
<form method="post" action='<c:url value="/login" />' style="display:inline;">
    Login: <input type="text" name="login"/>
    Passsword: <input type="text" name="password"/>
    <input type="submit" value="Login">
</form>

</body>
</html>
