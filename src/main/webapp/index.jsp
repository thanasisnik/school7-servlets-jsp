<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Central Service</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <script src="https://cdn.tailwindcss.com"></script>
        <script>
            tailwind.config = {
                theme: {
                    extend: {
                        colors: {
                            primary: {
                                600: '#2563eb',
                                700: '#1d4ed8',
                            },
                            success: {
                                500: '#10b981',
                                600: '#059669',
                            }
                        }
                    }
                }
            }
        </script>

</head>
<body class="bg-gray-50 min-h-screen flex flex-col">

<%@ include file="/WEB-INF/jsp/header.jsp" %>

<!-- Main Content -->
<main class="flex-grow flex items-center justify-center px-auto mx-auto">

    <div class="w-full max-w-3xl text-center">
        <div class="space-y-5">
            <h1 class="text-3xl md:text-4xl font-bold text-gray-900 mb-6 ">Κεντρική Υπηρεσία Coding Factory</h1>
             <p class="text-xl text-gray-600 max-w-2xl mx-auto">
                Το κεντρικό σύστημα διαχείρισης για τη διοίκηση και λειτουργία του Coding Factory
             </p>

             <!-- CTA Button -->
             <div class="mt-10">
                 <a href="${pageContext.request.contextPath}/login"
                    class="inline-flex items-center px-8 py-3 border border-transparent text-lg font-medium rounded-md shadow-sm text-white bg-success-500 hover:bg-success-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-success-500 transition-all duration-200 transform hover:scale-105">
                     Συνέχεια
                     <svg xmlns="http://www.w3.org/2000/svg" class="ml-2 -mr-1 h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                         <path fill-rule="evenodd" d="M10.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L12.586 11H5a1 1 0 110-2h7.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd" />
                     </svg>
                 </a>
             </div>
        </div>




    </div>




</main>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>