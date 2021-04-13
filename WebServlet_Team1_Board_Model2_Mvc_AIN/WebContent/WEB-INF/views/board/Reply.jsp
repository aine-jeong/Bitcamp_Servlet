<%@page import="kr.or.bit.dto.Reply"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("board_msg");
	List<Reply> list = (List<Reply>)request.getAttribute("replyList");
%>
