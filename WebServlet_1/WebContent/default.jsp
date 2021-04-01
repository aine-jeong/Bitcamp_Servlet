<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servalet 요청하기</title>
</head>
<body>
	<h3>servlet 요청</h3>
	<h3>getContextPath</h3>
	<%= request.getContextPath() %> <hr>
	
	<a href="<%= request.getContextPath() %>/simple">simple_요청하기(GET)</a>
	<br>
	<a href="<%= request.getContextPath() %>/simple?type=data">날짜_요청하기(GET)</a>
	<br>
	<a href="<%= request.getContextPath() %>/simple?type=abcd">비정상_요청하기(GET)</a>
	<hr>
	<h3>FrontServletController</h3>
	<a href="<%= request.getContextPath() %>/action.do?cmd=greeting">요청하기(GET)</a>
	<hr>
	<h3>FrontBoardController</h3>
	<br>
	<a href="<%= request.getContextPath() %>/board?cmd=boardlist">게시판 목록 보기</a>
	<br>
	<a href="<%= request.getContextPath() %>/board?cmd=boardwrite">게시판 글쓰기</a>
	<br>
	<a href="<%= request.getContextPath() %>/board">cmd null Error 유도하기</a>
	<br>
	<a href="<%= request.getContextPath() %>/board?cmd=boardelete">게시판 삭제하기</a>
	<br>
	<a href="<%= request.getContextPath() %>/board?cmd=login">로그인 페이지 보여주기(보안페이지)</a>
	
	<br>
	EL 사용하기
	<a href="${pageContext.request.contextPath}/board?cmd=login">로그인 페이지 보여주기(보안페이지)</a>
	
	<hr>
	<br>
	<a href="${pageContext.request.contextPath}/bbs?cmd=book">책주세요.</a>
	<br>
	<a href="<%= request.getContextPath() %>/bbs?cmd=pencil">연필주세요.</a>
	<br>
	<a href="<%= request.getContextPath() %>/bbs">연필주세요.</a>
	
</body>
</html>