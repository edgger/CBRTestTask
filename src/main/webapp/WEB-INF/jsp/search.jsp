<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>
<h3>Поиск</h3>
<form method="get" action="${pageContext.request.contextPath}/search">
    <div class="tableContainer">
        <div class="tableRow">
            <label class="tableCell">Фамилия:<input type="text" name="lname" value="${lname}"></label>
            <label class="tableCell">Имя:<input type="text" name="fname" value="${fname}"></label>
            <label class="tableCell">Отчество:<input type="text" name="mname" value="${mname}"></label>
        </div>
        <div class="tableRow">
            <label class="tableCell">Должность:
            <select name="positionId">
                <option value=""></option>
                <c:forEach items="${positions}" var="position">
                    <jsp:useBean id="position" scope="page" type="com.github.edgarzed.CBRTestTask.model.Position"/>
                    <option value="${position.id}" <c:if test="${position.id==positionId}">selected</c:if>>${position.name}</option>
                </c:forEach>
            </select></label>
            <label class="tableCell">Дата:<input type="date" name="date" value="${date}"></label>
        </div>
    </div>
    <button type="submit">Найти</button>
</form>
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
    <a href="${pageContext.request.contextPath}/search?lname=${lname}&fname=${fname}&mname=${mname}&positionId=${position.id}&date=${date}&page=${loop.index-1}" <c:if test="${loop.index-1==currentPage}">style="background-color: darkcyan" </c:if>>${loop.index}</a>
</c:forEach>
</body>
</html>
