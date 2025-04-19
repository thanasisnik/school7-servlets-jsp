<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Update</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/teacher-update.css">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<%@ include file="header.jsp"%>

<div class="max-w-3xl mx-auto p-8 mt-6 bg-white border  shadow-lg rounded-xl">

	<div class="mb-5">
		<form class="bg-gray-50 p-8 rounded-lg shadow-md" method="POST" action="${pageContext.request.contextPath}/school-app/students/update">
            <input type="hidden" name="id" value="${requestScope.updateDTO.id}" >

            <div class="flex justify-between gap-8 m-bottom">
                <input class="w-full border border-gray-300 rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" type="text" name="firstname" value="${requestScope.updateDTO.firstname}" placeholder="Όνομα">
                <p class="validation-error">${sessionScope.firstnameMessage}</p>

                <input class="w-full border border-gray-300 rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" type="text" name="lastname" value="${requestScope.updateDTO.lastname}" placeholder="Επώνυμο">
                <p class="validation-error">${sessionScope.lastnameMessage}</p>
            </div>

            <div class="flex justify-between gap-8 m-bottom">

                <input class="w-full border border-gray-300 rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" type="text" name="fatherName" value="${requestScope.updateDTO.fatherName}" placeholder="Επώνυμο Πατρός">
                <p class="validation-error">${sessionScope.fathernameMessage}</p>
            </div>

            <div class="flex justify-between gap-8 m-bottom">
                <input class="w-full border border-gray-300 rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" type="text" name="phoneNum" value="${requestScope.updateDTO.phoneNum}" placeholder="Αριθμός Τηλεφώνου">
                <p class="validation-error">${sessionScope.phoneNumMessage}</p>
                <input class="w-full border border-gray-300 rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" type="text" name="email" value="${requestScope.updateDTO.email}" placeholder="E-mail">
                <p class="validation-error">${sessionScope.emailMessage}</p>
            </div>

            <div class="flex justify-between gap-8 m-bottom">
                <input class="w-full border border-gray-300 rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" type="text" name="street" value="${requestScope.updateDTO.street}" placeholder="Οδός">
                <p class="validation-error">${sessionScope.streetMessage}</p>
                <input class="w-full border border-gray-300 rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" type="text" name="streetNum" value="${requestScope.updateDTO.streetNum}" placeholder="Αριθμός">
                <p class="validation-error">${sessionScope.streetNumMessage}</p>
            </div>

            <div class="flex justify-between gap-8 row m-bottom">
                <input class="w-full border border-gray-300 rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" type="text" name="zipCode" value="${requestScope.updateDTO.zipCode}" placeholder="ΤΚ">
                <p class="validation-error">${sessionScope.zipcodeMessage}</p>

            <!-- City Dropdown -->
                <select class="w-full border border-gray-300 rounded-md px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" name="cityId">
                <%-- The Select City option appears in the dropdown but cannot be chosen after a real selection is made --%>
                    <option value="" disabled ${empty insertDTO.cityId ? 'selected' : ''}>
                        Select City
                    </option>
                    <c:forEach items="${cities}" var="city">
                        <option value="${city.id}"
                                ${city.id eq updateDTO.cityId ? 'selected' : ''}>
                            ${city.name}
                        </option>
                    </c:forEach>
                </select>
                <p class="validation-error">${sessionScope.cityIdMessage}</p>
            </div>
            <div class="flex mx-auto items-center justify-center mt-10 mb-2">
                <button type="submit">Εισαγωγή</button>
            </div>

		</form>
	</div>

    <div class="mt-6 flex mx-auto justify-center mt-4 ">
        <a href="${pageContext.request.contextPath}/school-app/dashboard"
           class="inline-block px-5 py-2 bg-blue-700 hover:bg-blue-900 text-white font-semibold rounded-lg transition duration-200">
           Επιστροφή στον Πίνακα Ελέγχου
        </a>
    </div>

    <div>
        ${requestScope.errorMessage}
    </div>
</div>
</body>
</html>
