package kr.or.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.MemoAddService;
import kr.or.bit.service.MemoListService;

@WebServlet("*.do")
public class FrontMemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontMemoController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 한글처리
    	request.setCharacterEncoding("UTF-8");
    	
    	//2. 데이터받기
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlcommand = requestURI.substring(contextPath.length());
    	
    	System.out.println("urlcommand : " + urlcommand);
    
    	Action action=null;
    	ActionForward forward=null;
    	
    	if(urlcommand.equals("/MemoAdd.do")) { 
    		//UI 제공 (서비스 클래스 필요없다)
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/Memo/MemoAdd.jsp");
    		
    	}else if(urlcommand.equals("/MemoAddOk.do")) {
    		//UI 제공  + 로직 필요
    		action = new MemoAddService();
    		forward = action.execute(request, response);
    	}else if(urlcommand.equals("/MemoList.do")) {
    		//UI 제공  + 로직 필요
    		action = new MemoListService();
    		forward = action.execute(request, response);
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
