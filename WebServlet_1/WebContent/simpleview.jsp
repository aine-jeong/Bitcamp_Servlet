<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>simpleController에서 forward된 페이지</h3>
	요청한 결과를 출력(EL): ${requestScope.result}<br> <!-- EL방식 권장 -->
	요청한 결과를 출력(스크립트 릿): <%= request.getAttribute("result") %>
</body>
</html>