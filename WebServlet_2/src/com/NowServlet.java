package com;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		description = "서블릿은 클래스입니다", 
		urlPatterns = { 
				"/NowServlet", 
				"/Now.do", 
				"/Now.action", 
				"/Now.stop"
		}, 
		initParams = { 
				@WebInitParam(name = "Id", value = "bit", description = "id초기값 설정"), 
				@WebInitParam(name = "jdbcDriver", value = "oracle.jdbc.OracleDriver", description = "오라클 드라이버 제공")
		})
public class NowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NowServlet() {
        super();
        System.out.println("생성자 객체 생성시 한번 호출");
    }

	public void init(ServletConfig config) throws ServletException {
		//초기화 함수 (자동 호출 함수)
		//호출시점: NowServlet 클래스 파일에 대한 '최초 요청'시 한번만 실행된다.
		//재실행: 개발자 코드 수정, 서버의 재시작
		
		//it.co.kr 서버 오픈 > WAS (서블릿: NowServlet.java)
		//첫 접속자 "홍길동" -> it.co.kr/NowServlet 서버 요청 -> NowServlet컴파일
		//NowServlet컴파일 > class 실행 > 생성자 호출 > init함수 자동호출 > doGet, doPost함수 중 하나 자동호출
		
		//다음 접속자가 서버 요청시, class실행부터!
		
		//init: 클래스 내에 다음 함수가 사용하는 공통 자원의 load 또는 초기화
		
		//DB연결은 한번만 해놓고 init을 사용
		
		String drivername = config.getInitParameter("jdbcDriver"); //value값 얻어온당
		System.out.println("최초 요청시 한번 실행: 드라이버 로딩 > " + drivername);
		
		// ####
		// 최초접속시, 생성자 > init > call
		// 다음부터는 call부터
		// ####
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() call");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() call");
	}

}
