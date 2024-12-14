<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP</title>
</head>
<body>
    <!-- 스코프 -->
    <!-- Page -->
    <% pageContext.setAttribute("pageScope", "페이지"); %>
    <div>페이지:</div>
    <p>${pageContext.getAttribute("pageScope")}</p>

    <!-- Request -->
    <div>리퀘스트:</div>
    <p>${requestScope.request}</p>

    <!-- Session -->
    <div>세션:</div>
    <p>${sessionScope.session}</p>

    <!-- Application -->
    <div>어플리케이션:</div>
    <p>${applicationScope.application}</p>

    <!-- Model -->
    <div>모델:</div>
    <p>${model}</p>

    <!-- JSTL -->
    <!-- Core -->
    <!-- 변수값 설정 -->
    <c:set var="name" value="lee" />
    <c:set var="age" value="20" />
    <h1>${name}, ${age}</h1>

    <!-- 조건문 -->
    <c:if test="${name == 'lee'}">
        <p>내이름은 lee</p>
    </c:if>
    <c:choose>
        <c:when test="${age >= 30}">A</c:when>
        <c:when test="${age >= 20}">B</c:when>
        <c:otherwise>C</c:otherwise>
    </c:choose>

    <!-- 반복문 -->
    <c:forEach var="user" items="${users}">
        <p>${user.name}: ${user.age}</p>
    </c:forEach>

    <!-- Formatting -->
    <!-- 숫자 -->
    <c:set var="num" value="1234567.89" /><br>
    <!-- 기본 숫자 -->
    <fmt:formatNumber value="${num}" /><br>
    <!-- 퍼센트 -->
    <fmt:formatNumber value="${num}" type="percent" /><br>
    <!-- 소수점 자릿수 지정 -->
    <fmt:formatNumber value="${num}" maxFractionDigits="2" /><br>

    <!-- 날짜 -->
    <c:set var="now" value="<%= new java.util.Date() %>" />
    <!-- 기본 날짜 -->
    <fmt:formatDate value="${now}" /><br>
    <!-- date -->
    <fmt:formatDate value="${now}" type="date" /><br>
    <!-- 시간 -->
    <fmt:formatDate value="${now}" type="time" /><br>
    <!-- 사용자 정의 형식 -->
    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" /><br>

</body>
</html>