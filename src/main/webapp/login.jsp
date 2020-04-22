<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%

    if (request.getMethod().equalsIgnoreCase("post")) {


        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if ((username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("password"))) {


            session.setAttribute("username", username);
            response.sendRedirect("/profile.jsp");

        } else {
            response.sendRedirect("/login.jsp");
        }

    }
%>



<html>
<head>
    <title>Mitchel Web</title>
    <jsp:include page="/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>
<body>
<%@ include file="partials/navbar.jsp" %>

<h1>Login form:</h1>
<h3>Please fill out the information.</h3>

<form action="${pageContext.request.contextPath}/login.jsp" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" placeholder="user">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="password">
    <input type="submit">
</form>

</body>
</html>