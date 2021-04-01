package com;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 web.xml 설정
 <url-pattern>/simple</url-pattern>
 
 localhost:8090/WebServlet/simple 요청이 오면
 public class SimpleController 자바 파일을 컴파일
 
##  Servlet: JAVA로 만든 웹서비스 파일
 - 조건
 1. JAVA 파일이 extends HttpServlet (상속) 하고있어야 한다. ####
 2. 웹 요청(request)객체, 응답(response)객체를 자바파일에서 사용할 수 있다.
    즉, 자바가 웹이 가지고 있는 자원들을 사용하고자 하는 것
    
 - servlet은 url에서 바로 요청할 수 없다 > 요청을 따로 만들어둬야 한다
   요청에 따라 Mapping 된다.
   
 - 요청 주소 생성 방식
 1. web.xml에서 설정
 2. annotation 해두기 ( @WebServlet )
 
 
 ----------------------------------------------
 
 - extends HttpServlet 반드시 상속받고 있어야 한다.
 - SimpleController가 상속받고 있으므로, 얘는 웹 전용 서블릿파일이다!
 - servlet은 이벤트 기반의 동작을 한다
 	(특정 사건(이벤트)가 발생하면 "자동으로 호출되는" 함수가 존재한다)
 	
 - 자동 호출 함수
 1. protected void doGet
 	>> locahost:8090/WebServlet/simple 요청방식이 GET방식이라면 doGet 자동호출
 	>> ex.
 	>> <form method="GET" or <a href="/simple?num=100>
 
 2. protected void doPost
 	>> 요청방식이 POST 방식이라면, doPost 자동호출
 	>> ex.
 	>> <form method="POST"
 	>> form태그가 POST가 아니면 다 GET방식~~
 	
 - doGET(), doPOST()의 역할: 데이터 받기!
 - Parameter:
 - (HttpServletRequest request, HttpServletResponse response)
 - 즉, request와 response객체를 사용할 수 있도록 해주는 것 #
 
 */

public class SimpleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SimpleController() {
        super();
        System.out.println("SimpleController 생성자 함수 호출");
    }

    //클라이언트의 요청이 get방식일때 실행되는 코드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("클라이언트 요청: GET");
		
		//JSP 페이지에서 작업했던 내용과 똑같이 하면댕
		//1. 한글처리
		request.setCharacterEncoding("UTF-8");
		//2. 데이터 받기 (요청 의도 파악)
		String type = request.getParameter("type");
		//3. 로직(=서비스/ 요청에 따른 업무수행)
		Object resultobj = null;
		if(type == null || type.equals("greeting")) {
			resultobj = "hello world";
		}else if(type.equals("data")) {
			resultobj = new Date();
		}else {
			resultobj = "invaild String type";
		}
		
		//4. 요청 완료에 따른 결과를 저장
		//MVC패턴 맛보기
		//서블릿에 만든 객체 정보를 JSP까지 전달해야한다 > JSP가 전달받은 것을 화면에 보여주는 것
		
		//결과를 저장: session변수 , 특정 페이지(include, forword)에서는 request변수
		request.setAttribute("result", resultobj);
		
		//5. 저장한 결과를 JSP에 전달 -> UI 구성
		//아직 전달할수없댜...
		//일단, 결과를 forward방식을 통해서 JSP에게 넘겨주자!
		//		ㄴ> 장점: 클라이언트가 요청한 주소에는 변화가 없다.
		//				buffer forward 방식으로 페이지 정보를 담아서 서비스
		
		RequestDispatcher dis = request.getRequestDispatcher("/simpleview.jsp");
		//getRequestDispatcher > VIEW 페이지 정의
		
		//6. 정의한 페이지를 forward시키기
		dis.forward(request, response);
		
		//내가 화면으로 /simpleview.jsp 얘를 쓸거야
		//내 버퍼의 내용을 /simpleview.jsp가 채우도록 할거야
		//현재 servlet이 가지고 있는 request/response객체의 주소를 
		///simpleview.jsp에게 넘겨서 JSP가 사용하도록 할거양!!!!!!!
		//몬소리지
		
	}

	//클라이언트의 요청이 post방식일때 실행되는 코드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("클라이언트 요청: POST");
		
	}

}
