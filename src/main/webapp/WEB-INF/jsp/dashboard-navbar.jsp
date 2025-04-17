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
                                 700: '#1e4a8a',
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

    <div class="w-60 min-h-screen bg-primary-700 border-b border-t border-black border-r-2 border-r-black px-4 py-6 mr-2 flex flex-col relative">
      <nav class="space-y-6">
        <a href="<c:url value='${pageContext.request.contextPath}/school-app/teachers/view' />"
           class="block text-white hover:text-gray-300 font-medium transition-colors duration-200">
          Προβολή Καθηγητών
        </a>

        <a href="<c:url value='${pageContext.request.contextPath}/school-app/teachers/insert' />"
           class="block text-white hover:text-gray-300 font-medium transition-colors duration-200">
           Εισαγωγή Καθηγητών
        </a>

        <a href="#"
           class="block text-white hover:text-gray-300 font-medium transition-colors duration-200">
          Επεξεργασία Καθηγητών
        </a>

        <a href="#"
           class="block text-white hover:text-gray-300 font-medium transition-colors duration-200">
          Περισσότερα
        </a>

      </nav>
    </div>


</body>
</html>