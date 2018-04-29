<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
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
</body>
</html>
