package kr.or.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.memodao;

@WebServlet("/MemoId")
public class MemoId extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
  
    public MemoId() {
        super();
        
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // 비동기 요청
       // client에게 전달을 한다 (ID 사용 유무)
       // 1. Text (html, text, script, css, json)
       // 2. xml
       
    	//한글처리
       request.setCharacterEncoding("UTF-8");
       //view단 페이지가 가지는 선언부
       response.setContentType("text/html;charset=UTF-8");
       //write할 수 있는 객체를 얻어내야 한다 (직접 작성할 수 없으니까!)
       PrintWriter out = response.getWriter();
       
       //데이터 받기
       String id = request.getParameter("id");
       
       //memodao 생성
       memodao dao = new memodao();
       String ischeck = dao.isCheckById(id);
       
       // "false" or "true"
       
       // point
       out.print(ischeck); 
       
       // id 중복 유무 확인하는법
       //http://localhost:8090/WebServlet_4_Memo_mvc/MemoId?id=hong -> id 넣고 true가 뜨면 사용가능
       
       
   }
    
    
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request, response);
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request, response);
   }

}