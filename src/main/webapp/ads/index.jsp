<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%@page import="ListAdsDao" %>--%>

<%--<%--%>

<%--%>--%>

<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Adlister" />
    </jsp:include>
</head>
<body>
<jsp:include page="../partials/navbar.jsp" />
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">Items for Sale</h1>
        <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aspernatur consectetur consequatur repellendus reprehenderit, sequi ut voluptate.</p>
    </div>
</div>

<div class="container">
    <c:forEach var="ad" items="${ads}">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h2 class="card-title">${ad.title}</h2>
                <p class="card-text">${ad.description}</p>
            </div>
        </div>
    </c:forEach>

</div>
</body>
</html>
