<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Επιτυχής Εισαγωγή</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/success.css">
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

	<div class="max-w-xl mx-auto  mt-20 p-10 bg-white shadow-lg rounded-xl border border-green-200">
        <h1 class="text-2xl font-bold text-green-700 mb-5">✅ Επιτυχής Εισαγωγή</h1>

        <div class="space-y-2 text-gray-700">
            <p><span class="font-medium">Επώνυμο:</span> ${sessionScope.studentInfo.lastname}</p>
            <p><span class="font-medium">Όνομα:</span> ${sessionScope.studentInfo.firstname}</p>
            <p><span class="font-medium">Email:</span> ${sessionScope.studentInfo.email}</p>
            <p><span class="font-medium">Τηλέφωνο:</span> ${sessionScope.studentInfo.phoneNum}</p>
        </div>

        <div class="mt-6">
            <a href="${pageContext.request.contextPath}/school-app/dashboard"
               class="inline-block px-5 py-2 bg-primary-700 hover:bg-primary-900 text-white font-semibold rounded-lg transition duration-200">
               Επιστροφή στον Πίνακα Ελέγχου
            </a>
        </div>
    </div>


</body>
</html>
