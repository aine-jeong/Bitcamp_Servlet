package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.Mvcregisterdao;
import kr.or.bit.dto.registerdto;

/*
 Command 방식
 @WebServlet("/web.do")
 web.do?cmd=login
 web.do?cmd=loginok
 -> Command방식은 주소가 고정되어도 상관없다
 
 URL방식
 @WebServlet("*.do")
 -> 주소가 고정되면 안된다.
 
 */

/*
 
 /aaa.do
 /mvc.do
 
 주소가 .do 로 끝나는 모든 처리를 여기서 할거얌!
 
 */
@WebServlet("*.do")
public class RegisterFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterFrontController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//POINT
    	//요청 주소
    	//목록보기	: list.do
    	//글쓰기		: write.do
    	//글쓰기 처리	: writeOk.do
    	//이렇게 요청이 오면 모두 doProcess 실행
    	
    	//어떤 서비스를 하라는거지?
    	//주소를 판단하는 방법이 필요하다! 
    	//1. command 방식
    	//	:servlet.do?cmd=login&id=kglim&pwd=1004   > cmd > if(cmd.equals("login"))
    	//2. URl 방식
    	//	:login.do?id=kglim&pwd=1004  :  login.do > url 주소로 요청을 판단
    	
    	//--URL방식--
    	//1. 한글처리
    	request.setCharacterEncoding("UTF-8");
    	
    	//2. 데이터 받기
    	//String command = request.getParameter("cmd");
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlcommand = requestURI.substring(contextPath.length());
    	
    	/*
    	System.out.println("requestURI: " + requestURI);
    	System.out.println("contextPath: " + contextPath);
    	System.out.println("urlcommand: " + urlcommand);
    	requestURI: /WebServlet_8_Member_Model2_Mvc_Url/Register.do
		contextPath: /WebServlet_8_Member_Model2_Mvc_Url
		urlcommand: /Register.do
    	*/
    	
    	//3. 요청 판단해서 서비스 만들기
    	String viewpage="";
    	
    	if(urlcommand.equals("/Register.do")) { 
    		//화면전달 - 클라이언트에게 페이지만 전달하면 된다
    		viewpage = "/WEB-INF/Register/Register.jsp";
    	}else if(urlcommand.equals("/Registerok.do")) {
    		//로직처리
    		//추가적인 데이터
    		int id = Integer.parseInt(request.getParameter("id"));
    		String pwd = request.getParameter("pwd");
    		String email = request.getParameter("email");
    		
    		//controller > service요청 > DAO요청
    		//Mvcregister dto = new Mvcregister();
    		//dto.setId(id);
    		//dto.setPwd(pwd);
    		//dto.setEmail(email);
    		
    		registerdto dto = new registerdto();
    		dto.setId(id);
    		dto.setPwd(pwd);
    		dto.setEmail(email);
    		
    		Mvcregisterdao dao = new Mvcregisterdao();
    		int result = dao.writeOk(dto);
    		
    		String resultdata="";
    		if(result > 0) {
    			//세션에 저장해도 댄다
    			resultdata = "✨ welcome to Bit " + dto.getId() + "님 ✨";
    		}else {
    			resultdata = "회원가입에 실패했습니다. 다시 시도해주세요! 😣 ";
    		}
    		
    		//결과 저장하기
    		request.setAttribute("data", resultdata);
    		
    		//viewpage 만들기
    		viewpage = "/WEB-INF/Register/Register_welcome.jsp";
    	}
    	
    	//리다이렉트 시키는 페이지에 전달해서 사용
    	RequestDispatcher dis = request.getRequestDispatcher(viewpage);
    	
    	dis.forward(request, response);
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
