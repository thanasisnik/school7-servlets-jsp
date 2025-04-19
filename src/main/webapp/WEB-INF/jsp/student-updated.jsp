<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>

<div class="max-w-xl mx-auto mt-20 p-10 bg-white shadow-lg rounded-xl border border-green-200">
    <h1 class="text-2xl font-bold text-green-700 mb-5">✅ Επιτυχής Ενημέρωση</h1>
    <div class="space-y-2 text-gray-700">
        <p><span class="font-medium">Κωδικός:</span> ${sessionScope.studentInfo.id}</p>
        <p><span class="font-medium">Όνομα:</span> ${sessionScope.studentInfo.firstname}</p>
        <p><span class="font-medium">Επώνυμο:</span> ${sessionScope.studentInfo.lastname}</p>
    </div>
</div>

<div>
    <a href="${pageContext.request.contextPath}/school-app/students/view"
    class="inline-block px-5 py-2 bg-blue-700 hover:bg-blue-900 text-white font-semibold rounded-lg transition duration-200"
    >Επιστροφή στον Πίνακα Ελέγχου</a>
</div>
</body>
</html>
