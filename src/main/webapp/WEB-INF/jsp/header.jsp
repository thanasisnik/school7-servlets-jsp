<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
     <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<header>
    <div class="outer flex flex-wrap items-center justify-between h-auto p-4">

        <div class="items flex items-center space-x-4">
            <a href=""><img class="gov-gr-logo" src="${pageContext.request.contextPath}/img/gov_header_logo.svg" alt="Gov-Logo" width="40" height="auto"></a>
            <span class="title text-xl font-semibold">Coding Factory - Education Reinvented </span>
        </div>

        <div class="login-name flex items-center space-x-4">
            <span>${sessionScope.username}</span>
            <c:if test="${sessionScope.username != null}">
                <a class="log-out text-blue-600 hover:text-blue-800" href="${pageContext.request.contextPath}/logout">Έξοδος</a>
            </c:if>
        </div>
    </div>


</header>

</body>
</html>