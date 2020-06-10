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
<h3>Edit user</h3>
        <form method="post" action='<c:url value="/admin/edit" />' style="display:inline;">
                ID: ${user.id}
                <input type="hidden" name="id" value="${user.id}"/>
                <p></p>
                Name: <input type="text" name="name" value="${user.name}"/>
                Last Name: <input type="text" name="lastName" value="${user.lastName}"/>
                Age: <input type="text" name="age" value="${user.age}"/>
                Login: <input type="text" name="login" value="${user.login}"/>
                Passsword: <input type="text" name="password" value="${user.password}"/>
                Role: <input type="text" name="role" value="${user.role}"/>
                <input type="submit" value="Edit user">
        </form>

        <p></p>
</body>
</html>
