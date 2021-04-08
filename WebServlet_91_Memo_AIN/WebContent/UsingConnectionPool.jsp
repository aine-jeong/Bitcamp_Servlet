<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomcat ConnectionPool 사용하기</title>
</head>
<body>
<%
	Connection conn = null;
	
	//JNDI (이름기반검색)
	Context context = new InitialContext(); //현재 프로젝에서 특정 이름을 가진 녀석을 검색(이름 기반 검색)
	DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	//  java:comp/env/ + 이름(jdbc/oracle)
	
	//POOL안에서 connection을 가져오는 작업(빌리기)
	conn = ds.getConnection();
	
	out.print("db연결이 끊겼닝?: " + conn.isClosed() + "<br>");
	
	//## POINT ##
	//작업이 끝나면 반환해야한다!!
	conn.close(); //연결을 끊는 것이 아니라 반환하는 것이다!!!!
	
	out.print("db연결이 끊겼닝?: " + conn.isClosed() + "<br>");
%>
</body>
</html>