<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>
<h3>Создание</h3>
<form method="post" action="${pageContext.request.contextPath}/">
    <dl>
        <dt>Фамилия:</dt>
        <dd><input type="text" name="lname" value="${lname}" required></dd>
    </dl>
    <dl>
        <dt>Имя:</dt>
        <dd><input type="text" name="fname" value="${fname}" required></dd>
    </dl>
    <dl>
        <dt>Отчество:</dt>
        <dd><input type="text" name="mname" value="${mname}" required></dd>
    </dl>
    <dl>
        <dt>Должность:</dt>
        <dd><select name="positionId" required>
            <c:forEach items="${positions}" var="position">
                <jsp:useBean id="position" scope="page" type="com.github.edgarzed.CBRTestTask.model.Position"/>
                <option value="${position.id}" <c:if test="${position.id==positionId}">selected</c:if>>${position.name}</option>
            </c:forEach>
        </select> </dd>
    </dl>
    <dl>
        <dt>Дата:</dt>
        <dd><input type="date" name="date" value="${date}" required></dd>
    </dl>
    <dl>
        <dt>Время (минут):</dt>
        <dd><input type="number" name="minutes" value="${minutes}" required></dd>
    </dl>
    <dl>
        <dt>Причина:</dt>
        <dd><textarea name="reason" rows="3" required>${reason}</textarea> </dd>
    </dl>
    <button type="submit">save</button>
</form>
</body>
</html>
