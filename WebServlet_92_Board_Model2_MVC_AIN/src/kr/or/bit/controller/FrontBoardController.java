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
import kr.or.bit.service.BoardDeleteOkService;
import kr.or.bit.service.BoardEditOkService;
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
    		
    	//BoardContent.board (상세보기)
    	}else if(url_Command.equals("/BoardContent.board")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/board/board_content.jsp");
    		System.out.println("BoardContent 제공");
    		
    	//BoardList.board (목록보기)	
    	}else if(url_Command.equals("/BoardList.board")) { 
    		action = new BoardListService();
    		forward = action.execute(request, response);
    		System.out.println("BoardListService 실행");
    	
    	//BoardEdit.board (글수정화면 보여주기)
    	}else if(url_Command.equals("/BoardEdit.board")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/board/board_edit.jsp");
    		System.out.println("BoardEdit 제공");
    		
    	//BoardEditOk.board (글수정처리하기)
    	}else if(url_Command.equals("/BoardEditOk.board")) {
    		action = new BoardEditOkService();
    		forward = action.execute(request, response);
    		System.out.println("BoardEditOkService 실행");
    		
    	//BoardDelete.board (글삭제화면 보여주기)
    	}else if(url_Command.equals("/BoardDelete.board")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/board/board_delete.jsp");
    		System.out.println("BoardDelete 제공");
    		
        //BoardDelete.board (글삭제처리하기)
        }else if(url_Command.equals("/BoardDeleteOk.board")) {
        	action = new BoardDeleteOkService();
        	forward = action.execute(request, response);
        	System.out.println("BoardDeleteOkService 실행");
        		
    	//BoardRewrite.board (답글쓰기화면 보여주기)
    	}else if(url_Command.equals("/BoardRewrite.board")) {
    		
    		
    	//BoardRewrite.board (답글쓰기 처리하기)
    	}else if(url_Command.equals("/BoardRewrite.board")) {	
    		
    		
    	//BoardRewrite.board (댓글 처리하기)
    	}else if(url_Command.equals("/BoardRewrite.board")) {
    		
    		
    	//BoardRewrite.board (댓글 삭제하기)
    	}else if(url_Command.equals("/BoardRewrite.board")) {
    		
    		
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
