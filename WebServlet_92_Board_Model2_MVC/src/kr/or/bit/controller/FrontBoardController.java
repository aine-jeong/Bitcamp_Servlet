package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.BoardAddService;
import kr.or.bit.service.BoardListService;

@WebServlet("*.board")
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontBoardController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청주소가져오기
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	Action action = null;
    	ActionForward forward=null;
    	
    	System.out.println(url_Command);
    	//### 현재 /board가 더 붙어서 나오는 부분 처리하기
    	
    	//BoardWrite.board (글쓰기 화면 보여주기)
    	if(url_Command.equals("/BoardWrite.board")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/board/board_write.jsp");
    		System.out.println("BoardWrite 제공"); 
    	
    	//BoardAdd.board (글쓰기처리)
    	} else if(url_Command.equals("/BoardAdd.board")) {
    		action = new BoardAddService();
    		forward = action.execute(request, response);
    		System.out.println("BoardAddService 실행");
    		
    	//목록보기	
    	}else if(url_Command.equals("/BoardList.board")) {
    		action = new BoardListService();
    		forward = action.execute(request, response);
    		System.out.println("BoardListService 실행");
    	}
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			//UI
    			//UI + 로직
    			//forward url 주소 변환 없어 View 내용을 받을 수 있다
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
