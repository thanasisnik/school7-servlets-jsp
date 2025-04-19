<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="el">
<head>
    <title>Προβολή Μαθητή</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center p-6">
    <div class="w-full max-w-xl bg-white rounded-2xl shadow-lg p-8">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Στοιχεία Μαθητή</h2>

        <c:if test="${not empty errorMessage}">
            <p class="text-red-600 text-center mb-4">${errorMessage}</p>
        </c:if>

        <c:if test="${not empty student}">
            <ul class="space-y-2 text-gray-700">

                <li><span class="font-semibold">Όνομα:</span> ${student.firstname}</li>
                <li><span class="font-semibold">Επώνυμο:</span> ${student.lastname}</li>
                <li><span class="font-semibold">Όνομα Πατρός:</span> ${student.fatherName}</li>
                <li><span class="font-semibold">Τηλέφωνο:</span> ${student.phoneNum}</li>
                <li><span class="font-semibold">Email:</span> ${student.email}</li>
                <li><span class="font-semibold">Οδός:</span> ${student.street}</li>
                <li><span class="font-semibold">Αριθμός Οδού:</span> ${student.streetNum}</li>
                <li><span class="font-semibold">Τ.Κ.:</span> ${student.zipCode}</li>
                <li><span class="font-semibold">Πόλη:</span>
                    <c:forEach var="city" items="${cities}">
                        <c:if test="${city.id == student.cityId}">
                            ${city.name}
                        </c:if>
                    </c:forEach>
                </li>

            </ul>

        </c:if>

        <div class="mt-6 flex justify-center items-center">
            <a href="${pageContext.request.contextPath}/school-app/students/view"
               class="inline-block px-5 py-2 bg-blue-700 hover:bg-blue-900 text-white font-semibold rounded-lg transition duration-200">
               Επιστροφή στην Προβολή Μαθητών
            </a>
        </div>
    </div>
</body>
</html>
