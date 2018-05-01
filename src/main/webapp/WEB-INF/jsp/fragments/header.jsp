<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="${pageContext.request.contextPath}/">Главная</a>&nbsp;|&nbsp;<a href="${pageContext.request.contextPath}/search">Поиск</a>&nbsp;|&nbsp;<a href="${pageContext.request.contextPath}/create">Создание</a>
<hr>
<c:if test="${errorMessage!=null}"><h2 class="error">${errorMessage}</h2></c:if>
