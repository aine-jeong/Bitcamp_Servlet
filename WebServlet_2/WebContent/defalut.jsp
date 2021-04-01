<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>default page</h3>
	
	<!-- 192.168.0.54:8090/WebServlet_2/NowServlet 을 요청하는 것과 같다 -->
	<br>
	<a href="${pageContext.request.contextPath}/NowServlet">요청하기</a>
	<br>
	<a href="${pageContext.request.contextPath}/Now.do">요청하기</a>
	<br>
	<a href="${pageContext.request.contextPath}/Now.action">요청하기</a>
	
</body>
</html>