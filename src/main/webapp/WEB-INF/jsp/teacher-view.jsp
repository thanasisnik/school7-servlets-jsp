<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="el">
<head>
    <title>Προβολή Καθηγητή</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center p-6">
    <div class="w-full max-w-xl bg-white rounded-2xl shadow-lg p-8">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Στοιχεία Καθηγητή</h2>

        <c:if test="${not empty errorMessage}">
            <p class="text-red-600 text-center mb-4">${errorMessage}</p>
        </c:if>

        <c:if test="${not empty teacher}">
            <ul class="space-y-2 text-gray-700">

                <li><span class="font-semibold">Όνομα:</span> ${teacher.firstname}</li>
                <li><span class="font-semibold">Επώνυμο:</span> ${teacher.lastname}</li>
                <li><span class="font-semibold">ΑΦΜ:</span> ${teacher.vat}</li>
                <li><span class="font-semibold">Όνομα Πατρός:</span> ${teacher.fatherName}</li>
                <li><span class="font-semibold">Τηλέφωνο:</span> ${teacher.phoneNum}</li>
                <li><span class="font-semibold">Email:</span> ${teacher.email}</li>
                <li><span class="font-semibold">Οδός:</span> ${teacher.street}</li>
                <li><span class="font-semibold">Αριθμός Οδού:</span> ${teacher.streetNum}</li>
                <li><span class="font-semibold">Τ.Κ.:</span> ${teacher.zipCode}</li>
                <li><span class="font-semibold">Πόλη:</span>
                    <c:forEach var="city" items="${cities}">
                        <c:if test="${city.id == teacher.cityId}">
                            ${city.name}
                        </c:if>
                    </c:forEach>
                </li>

            </ul>

        </c:if>

        <div class="mt-6 flex justify-center items-center">
            <a href="${pageContext.request.contextPath}/school-app/teachers/view"
               class="inline-block px-5 py-2 bg-blue-700 hover:bg-blue-900 text-white font-semibold rounded-lg transition duration-200">
               Επιστροφή στην Προβολή Καθηγητών
            </a>
        </div>
    </div>
</body>
</html>
