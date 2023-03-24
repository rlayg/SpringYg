<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>login 성공</h1>
	<c:if test="${not empty pageContext.request.userPrincipal }">
		${pageContext.request.userPrincipal }
		<p> is Log-In</p>
	</c:if>
	<c:if test="${empty pageContext.request.userPrincipal }">
		<p> is Log-Out</p>
	</c:if>
	USER ID : ${pageContext.request.userPrincipal }<br/>
	<a href="${pageContext.request.contextPath }/j_spring_security_logout">Log Out</a><br/>
	<!-- j_spring_security_logout는 예약어 -->
</body>
</html>