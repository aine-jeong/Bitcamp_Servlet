<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <form action="${pageContext.request.contextPath}/Register.do?cmd=registerok" method="POST"> --%>
	<form action="${pageContext.request.contextPath}/Registerok.do" method="POST">
		ID: 	<input type="text" 		name="id" placeholder="id 입력"><br>
		PWD: 	<input type="password" 	name="pwd" placeholder="pwd 입력"><br>
		EMAIl:  <input type="text" 		name="email" placeholder="email 입력"><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>