<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tab65
  Date: 15.04.2020
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<h3>User</h3>
    ID: ${user.id} <p />
    Name: ${user.name}<p />
    Last Name: ${user.lastName}<p />
    Age: ${user.age}<p />
    Login: ${user.login}<p />
    Passsword: ${user.password}<p />
    Role: ${user.role}

    <p></p>
    <form>
        <a href="login">Login</a>
    </form>
</body>
</html>
