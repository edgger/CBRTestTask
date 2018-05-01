<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>
<h3>Табель отсутствия</h3>
<table border="3">
    <tr>
        <th>ФИО</th>
        <th>Должность</th>
        <th>Дата</th>
        <th>Время</th>
        <th>Причина</th>
    </tr>
<c:forEach items="${absencesData}" var="absenceData">
    <jsp:useBean id="absenceData" scope="page" type="com.github.edgarzed.CBRTestTask.dto.AbsenceData"/>
    <tr>
        <td>${absenceData.employee}</td>
        <td>${absenceData.employeePosition}</td>
        <td>${absenceData.date}</td>
        <td>${absenceData.timeMinutes}</td>
        <td>${absenceData.reason}</td>
    </tr>
</c:forEach>
</table>
<c:forEach begin="1" end="${lastPage}" varStatus="loop">
    <a href="${pageContext.request.contextPath}/?page=${loop.index-1}">${loop.index}</a>
</c:forEach>
</body>
</html>
