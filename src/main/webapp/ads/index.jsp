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
<div class="container">
    <h1>List Of ADS!!</h1>
    <jsp:useBean id="link" class="ListAdsDao" />
    <%=link.generateAds() %>
</div>
</body>
</html>
