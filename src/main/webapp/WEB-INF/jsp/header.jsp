<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
     <script src="https://cdn.tailwindcss.com"></script>
      <script>
             tailwind.config = {
                 theme: {
                     extend: {
                         colors: {
                             primary: {
                                 800: '#1e3a8a',  // Dark blue for header
                                 900: '#172554'   // Even darker blue
                             }
                         }
                     }
                 }
             }
         </script>
</head>
<body>

<header class="bg-primary-800">
    <div class="container mx-auto px-4 ">

        <div class="flex items-center justify-between py-4">
            <div class=" flex items-center space-x-4">
                        <a href=""><img class="h-12 w-auto" src="${pageContext.request.contextPath}/img/gov_header_logo.svg" alt="Gov-Logo"></a>
                        <span class="title hidden md:block text-lg font-semibold text-white">Coding Factory - Education Reinvented </span>

                    </div>

                    <div class="flex items-center space-x-4">

                        <c:if test="${sessionScope.username != null}">
                            <span class="text-lg font-medium text-gray-200 hidden md:inline-flex" >${sessionScope.username}</span>
                            <a class="flex items-center px-4 py-2 text-lg font-medium text-white hover:text-gray-200 gap-1" href="${pageContext.request.contextPath}/logout">
                            <span>Έξοδος</span>
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 ml-1" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
                            </svg>
                            </a>
                        </c:if>
                    </div>
        </div>

    </div>


</header>

</body>
</html>