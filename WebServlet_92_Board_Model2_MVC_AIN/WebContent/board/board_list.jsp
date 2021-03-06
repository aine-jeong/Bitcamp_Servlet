<%@page import="kr.or.bit.utils.ThePager"%>
<%@page import="kr.or.bit.dto.Board"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.bit.service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="Stylesheet" href="<%=request.getContextPath()%>/style/default.css" />
</head>
<body>
	<c:import url="/include/header.jsp" />
	게시판 목록
	<br>
	<%
	
		//BoardService > static 자원으로, new를 내부적으로 실행 후 반환하도록 설정해둠
		//BoardService를 이용하기 위한 코드
		BoardService service = BoardService.getInBoardService();
		
		//게시물 총 건수
		int totalboardcount = service.totalBoardCount();
		
		//상세보기 >> 다시  LIST 넘어올때  >> 현재 페이지 설정
		String ps = request.getParameter("ps"); //pagesize
		String cp = request.getParameter("cp"); //current page
		
		//List 페이지 처음 호출 ...
		if(ps == null || ps.trim().equals("")){
			//default 값 설정
			ps = "5"; //5개씩 
		}
	
		if(cp == null || cp.trim().equals("")){
			//default 값 설정
			cp = "1"; // 1번째 페이지 보겠다 
		}
		
		//페이지 개수 구하기
		int pagesize = Integer.parseInt(ps);
		int cpage = Integer.parseInt(cp);
		int pagecount=0;
		
		//페이지가 딱 맞게 떨어지면 if를, 아니면 else
		if(totalboardcount % pagesize == 0){
			pagecount = totalboardcount / pagesize; //  20 << 100/5
		}else{
			pagecount = (totalboardcount / pagesize) + 1; 
		}
		
		//102건 : pagesize=5 >> pagecount=21페이지
		
		//전체 목록 가져오기
		List<Board> list = service.list(cpage, pagesize); //list >> 1 , 20
		
    %>
    <!-- JSTL / 변수 설정 -->
	<c:set var="pagesize" value="<%=pagesize%>" />
	<c:set var="cpage" value="<%=cpage%>" />
	<c:set var="pagecount" value="<%=pagecount%>" />
	
	<c:set var="list" value="${requestScope.boardlist}"></c:set>
	
	<div id="pagecontainer">
		<div style="padding-top: 30px; text-align: cetner">
			<table width="80%" border="1" cellspacing="0" align="center">
				<tr>
					<td colspan="5">
						<!--  
							form 태그 action 전송 주소(목적지) >> submit()
							>> form태그에 action이 없다면,
							>> [현재 URL 창에 있는 주소] 그대로!!
							>> board_list.jsp?ps=select 태그 값으로 .... 다시 호출 .....
							>>http://192.168.0.169:8090/WebServlet_5_Board_Model1_Sample/board/board_list.jsp?ps=10					
						-->
						<form name="list" >
						 PageSize설정: 
							<select name="ps" onchange="submit()">
								<!-- JSTL, for loop(반복문) -->
							   <c:forEach var="i" begin="5" end="20" step="5">
							   		<!-- JSTL switch문 -->
							   		<!-- 만약 10건을 선택했을 때, 옵션 칸에 (10건)으로 떠있도록 설정 -->
							   		<c:choose>
							   			<c:when test="${pagesize == i}">
							   				<option value="${i}" selected>${i}건</option>
							   			</c:when>
						   				<c:otherwise>
						   					<option value="${i}">${i}건 </option>
						   				</c:otherwise>
							   		</c:choose>
							   </c:forEach>
		   					</select>
						</form>
					</td>
				</tr>
				<tr>
					<th width="10%">순번</th>
					<th width="40%">제목</th>
					<th width="20%">글쓴이</th>
					<th width="20%">날짜</th>
					<th width="10%">조회수</th>
				</tr>
				<!-- 데이터가 한건도 없는 경우  -->
				<%
		     		if(list == null || list.size() == 0){
		     			out.print("<tr><td colspan='5'>데이터가 없습니다</td></tr>");
		     		}
		        %>
				<!-- forEach()  목록 출력하기  -->
				<!-- var: 변수명 / items: 목록데이터 (여기서는 현재 페이지에 보여줘야 하는 게시글을 담은 list) -->
				<c:forEach var="board" items="<%=list%>">
					<!-- 마우스 오버, 아웃시 스타일 변경 -->
					<tr onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
						<td align="center">${board.idx}</td>
						<td align="left">
							<!-- 들여쓰기 하기위한 forEacn -->
							<!-- begin: 시작인덱스 / end:종료인덱스 / step: 증가단계-->
							<!-- 즉, depth의 수만큼 공백 추가 -->
							<c:forEach var="i" begin="1" end="${board.depth}" step="1">
								&nbsp;&nbsp;&nbsp;
							</c:forEach>
							<!--답글 이미지 붙이기 -->
							<!-- 만약 depth가 0이상이면 이미지 삽입 -->
							<c:if test="${board.depth > 0}">
								<img src="${pageContext.request.contextPath}/images/re.gif">
							</c:if>
							<!-- 게시글의 제목 클릭시 해당 게시글화면으로 이동 -->
							<!-- 파라미터에 글번호와 현재페이지, 페이지사이즈 정보 넣어서 이동함 -->
							<a href="BoardContent.board?idx=${board.idx}&cp=${cpage}&ps=${pagesize}">
								<!-- 글 제목이 null값이 아니고 제목길이가 10보다 길다면 잘라서 보여주고, 아니면 그냥출력 -->
								<c:choose>
									<c:when test="${board.subject != null && fn:length(board.subject) > 10}">
										${fn:substring(board.subject,0,10)}...
									</c:when>
									<c:otherwise>
										${board.subject}
									</c:otherwise>
								</c:choose>
							</a>
						</td>
						<!-- 글쓴이, 날짜, 조회수 순서대로 구성 -->
						<td align="center">${board.writer}</td>
						<td align="center">${board.writedate}</td>
						<td align="center">${board.readnum}</td>
					</tr>
				</c:forEach>
				<!-- forEach()  -->
				<tr>
					<td colspan="5" align="center">
						<hr width="100%" color="red">
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
					<!--  
					원칙적인 방법 아래 처럼 구현
					[1][2][3][다음]
					[이전][4][5][6][다음]
					[이전][7][8][9][다음]
					[이전][10][11]
					-->
					
						<!--이전 링크 --> 
						<!-- 만약, 현재 페이지가 2 이상이라면 -->
						<c:if test="${cpage > 1}">
							<!-- 현재페이지의 값을 1 감소시킨 뒤, board_list를 다시 호출하는 링크를 걸어둔 문자를 표시 -->
							<a href="BoardList.board?cp=${cpage-1}&ps=${pagesize}">이전</a>
						</c:if>
						<!-- page 목록 나열하기 -->
						<c:forEach var="i" begin="1" end="${pagecount}" step="1">
							<!-- switch문 -->
							<!-- 만약 현재페이지가 i와 같다면 빨간색으로 표시 -->
							<c:choose>
								<c:when test="${cpage==i}">
										<font color="red" >[${i}]</font>
								</c:when>
								<c:otherwise>
									<a href="BoardList.board?cp=${i}&ps=${pagesize}">[${i}]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!--다음 링크 --> 
						<!-- 만약 현재페이지가 페이지수보다 적다면 (ex.페이지수가 3일때, 현재페이지가1또는2라면 -->
						<c:if test="${cpage < pagecount}">
							<!-- 다음페이지를 호출하는 링크를 걸어둔 문자 표시 -->
							<a href="BoardList.board?cp=${cpage+1}&ps=${pagesize}">다음</a>
						</c:if>
					</td>
					<td colspan="2" align="center">총 게시물 수 : <%= totalboardcount %>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
					<%
						int pagersize=3; //[1][2][3]
						ThePager pager = new ThePager(totalboardcount,cpage,pagesize,pagersize,"BoardList.board");
					%>
					<%= pager.toString() %>
					</td>
			</table>
		</div>
	</div>
</body>
</html>





