<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">-->
</head>
<body>

    <%@ include file="/WEB-INF/jsp/header.jsp" %>
    <%@ include file="/WEB-INF/jsp/dashboard-navbar.jsp" %>


    <%@ include file="/WEB-INF/jsp/footer.jsp" %>

</body>
</html>
