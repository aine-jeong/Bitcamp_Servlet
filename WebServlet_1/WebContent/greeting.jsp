<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>View Page</h3>
	UI코드가 블라블라~~
	<hr>
	EL: ${requestScope.msg}<br>
	스크립트 릿: <%= request.getAttribute("msg") %>
</body>
</html>