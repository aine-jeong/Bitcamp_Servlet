package com.bit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//192.168.0.128:8090/WebServlet_1/action.do 라는 요청이 오면
//FrontServletController 자바파일이 실행되도록 하겠다!
@WebServlet(description = "여기는 설명을 하는 곳입니다.", 
			urlPatterns = { "/action.do" })
// 어노테이션: 해당 서블릿의 설명과 url 알려주기
// web.xml에다가 적었던것을 어노테이션으로 넣어줄 수 있다 (같은 역할)

//url >  "~.do" 로 만드는 경우가 많다.

public class FrontServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontServletController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET");
		
		//192.168.0.128:8090/WebServlet_1/action.do?cmd=greeting 으로 들어오면~~~
		//1. 한글처리
		request.setCharacterEncoding("UTF-8");
		//2. 데이터 받기
		String cmd = request.getParameter("cmd");
		String msg = "";
		//3. 서비스 수행하기
		if(cmd.equals("greeting")) {
			Message m = new Message();
			msg = m.getMessage(cmd);
		}
		//4. 결과 저장하기 / 데이터 담기
		request.setAttribute("msg", msg);
		
		//5. 결과 전달하기
		RequestDispatcher dis = request.getRequestDispatcher("/greeting.jsp");
		//view단에다가 forward 시키기
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	// 만약 get / post 방식의 로직이 같으면 한쪽에 로직을 작성, 다른쪽에는 로직을 작성한 함수를 호출시키면 된당~~~
	// 구분하기 위해서 파라미터 하나 추가하면 된당
	// doGet에 , String method 파라미터 추가하고,
	// doPost에서 부를 때 doGet(request, response, "POST"); 처럼 부르면 된당

}
