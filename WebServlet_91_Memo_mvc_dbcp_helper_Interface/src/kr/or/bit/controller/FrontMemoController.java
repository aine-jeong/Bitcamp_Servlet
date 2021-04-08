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
import kr.or.bit.service.MemoAddService;
import kr.or.bit.service.MemoIdCheckService;
import kr.or.bit.service.MemoListService;




@WebServlet("*.memo")
public class FrontMemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontMemoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//요청주소의 마지막 값을 뽑아내는 작업
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
	
    	Action action=null;
    	ActionForward forward=null;
    	
    	//memo.html에서 MemoAdd.memo로 전달이 들어왔을 때, 글쓰기 처리
    	if(url_Command.equals("/MemoAdd.memo")) {
    		//UI+로직
    		action = new MemoAddService();
    		forward = action.execute(request, response);
    		System.out.println("MemoAddService 실행");
    		
    	}else if(url_Command.equals("/MemoList.memo")) { //목록보기
    		//UI+로직
    		action = new MemoListService();
    		forward = action.execute(request, response);
    		System.out.println("MemoListService 실행");
    		
    		
    	//비동기의 경우 view가 필요없는뎁... ?
    	//1. 비동기의 경우 Servlet을 따로 만든다 (요청주소에 대한 Servlet을 가지고 있는 것)
    	//	 동기 방식은 컨트롤러 태워서 처리, 비동기는 따로 처리
    	//2. 비동기도 컨트롤러 태우기
    	// 	 HTML에 MemoId.memo url 넣기
    	//	 이 아래의 코드가 비동기도 컨트롤러로 넘어오는 방법!
    	//	 이 케이스의 경우 uservalid가 따로 만들어진다
    	//	 UI를 미리 작업해서 클라이언트한테 날릴거면 이렇게 해도 되지만,
    	//	 요즘은 UI는 클라이언트한테 맡기고 ONLY 데이터만 넘긴다 -> 1번 방식을 사용하는게 낫다(why? uservalid가 너무 의미없이 만들어짐)
    	}else if(url_Command.equals("/MemoId.memo")) { //비동기(ID 사용 유무)
    		//UI+로직
    		action = new MemoIdCheckService();
    		forward = action.execute(request, response);
    		
    		System.out.println("MemoIdCheckService 실행");
    		
    	//상세보기 기능 (현재 음슴)
    	}else if(url_Command.equals("/MemoView.memo")) { 
    		//UI 제공 ...
    		//예) /WEB-INF/views/memoview.jsp 가정
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/memoview.jsp");
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
