<%--
  Created by IntelliJ IDEA.
  User: tab65
  Date: 13.04.2020
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

    <form method="POST">
        Name: <input type="text" name="name"/>
        LastName: <input type="text" name="lastName"/>
        Age: <input type="number" name="age"/>
        Login: <input type="text" name="login"/>
        Passsword: <input type="text" name="password"/>
        Role: <input type="text" name="role"/>
        <input type="submit" value="Add new user">
    </form>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>LastName</th>
            <th>Age</th>
            <th>Login</th>
            <th>Password</th>
            <th>Role</th>
        </tr>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <form method="get" action='<c:url value="/admin/edit" />' style="display:inline;">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="name" value="${user.name}">
                        <input type="hidden" name="lastName" value="${user.lastName}">
                        <input type="hidden" name="age" value="${user.age}">
                        <input type="hidden" name="login" value="${user.login}"/>
                        <input type="hidden" name="password" value="${user.password}"/>
                        <input type="hidden" name="role" value="${user.role}"/>
                        <input type="submit" value="Edit">
                    </form>

                    <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>

        </c:forEach>

    </table>

    <p></p>
    <form>
        <a href="login">Login</a>
    </form>

</body>
</html>
