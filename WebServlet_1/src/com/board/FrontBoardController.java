package com.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//어노테이션 사용하지 않고 web.xml에서 설정함

public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontBoardController() {
        super();
    }

    // doGet과 doPost 처리방식이 같아서, 따로 함수 생성후 사용하겠당!
    private void doProcess(HttpServletRequest request, HttpServletResponse response, String method) throws ServletException, IOException {
    	//GET, POST 두가지 요청에 대해서 동작하는 함수
    	//method출력하면 요청이 무엇(get/post)인지 확인할 수 있다.
    	System.out.println("클라이언트 요청: " + method);
    	
    	//1. 한글 처리
    	//2. 데이터 요청 받기(request)
    	//3. 요청 판단 
    	//판단의 기준
    	//3.1. command 방식 (parameter를 기준으로)
    	//192.168.0.54:8090/WebServlet_1/board?cmd=login&id=ain&pwd=1004 요청
    	// 서버는 cmd 변수가 가지는 값을 가지고 판단한다 (cmd=login > 로그인처리)
    	//192.168.0.54:8090/WebServlet_1/board?cmd=list 요청 > 게시판 보여주기
    	
    	//String command = request.getParameter("cmd");
    	//if(command.equals("login")) { 로그인 서비스 처리 }
    	//else if(command.equals("list")) { 게시판 목록보기 서비스 }
    	
    	//3.2. URL 주소방식 (주소값에서 추출)
    	//192.168.0.54:8090/WebServalet_1/board/boardlist
    	//192.168.0.54:8090/WebServalet_1/board/boardwrite?title=방가&content=방가방가
    	//마지막 주소값을 추출
    	// /boardlist >> 게시판 목록보기
    	// /boardwrite?title=방가&content=방가방가 >> 게시판 글쓰기
    	
    	//4. 결과 저장 (request, session, application)
    	// 기준: 범위 (모든 페이지가 필요로 하는지, 현재 페이지만 필요로 하는지 등등)
    	
    	//5. view 저장
    	//view page: *.jsp
    	//WebContent > board > boardlist.jsp
    	//WebContent > error > error404.jsp
    	
    	//위 방식은 보안상의 문제가 있다
    	//실 개발에서는 : WebContent > WEB-INF > view > board or member or admin 폴더를 만들어서 관리한다.
    	//ex. 보안폴더 : WEB-INF > views > board > boardlist.jsp
    	
    	//6. view에게 request 객체를 forward해서, 사용 가능하도록 만들어준다.
    	
    	//---------------------------------------------------------------------------
    	
    	//1. 한글처리
    	request.setCharacterEncoding("UTF-8");
    	
    	//2. 데이터 받기
    	String cmd = request.getParameter("cmd");
    	
    	//3. 요청 판단하기
    	String viewpage = null;
    	
    	//cmd : null > error.jsp
    	//cmd : boardlist > list.jsp
    	//cmd : boardwrite > write.jsp
    	
    	if(cmd == null) {
    		viewpage = "/error/error.jsp"; //클라이언트에게 error.jsp전달
    	}else if(cmd.equals("boardlist")) {
    		//실제 업무 처리(서비스)
    		/* 
    		 (ex)
    		 DB 연결 > Select > 객체 담고 > 저장
    		 boardDao dao = new boardDao();
    		 List<board> boardlist = dao.selectBoardList();
    		 request.setAttribute("list",boardlist);
    		 forward > view > 전달 (request.getAttribute()) 가지고와서 화면 출력해야징
    		 출력시에는 EL / JSTL 사용해야징 등등
    		 */
    		
    		viewpage = "/board/boardlist.jsp";
    	}else if(cmd.equals("boardwrite")) {
    		viewpage = "/board/boardwrite.jsp";
    	}else if(cmd.equals("login")) {
    		//보안폴더
    		viewpage = "/WEB-INF/views/login/login.jsp";
    	}else {
    		viewpage = "/error/error.jsp";
    	}
    	
    	//4. 결과 저장하기
    	//ex.
    	//List<Emp> list = new Arraylist<>();
    	//list.add(new Emp(200, 김유신));
    	//request.setAttribute("emplist",list); > 이렇게 넣어주면 getAttribute로 쓸 수 있는 자원이 된다.
    	
    	
    	//5. view 지정
    	RequestDispatcher dis = request.getRequestDispatcher(viewpage);
    	
    	//6. 데이터 전달(forward)
    	dis.forward(request, response);
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "GET");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "POST");
	}

}
