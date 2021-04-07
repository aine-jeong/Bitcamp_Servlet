package kr.or.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.memodao;
import kr.or.bit.dto.Memo;

@WebServlet("/MemoServlet")
public class MemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemoServlet() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//localhost:8090/WebServlet_4_Memo_mvc/MemoServlet
    	//해당 URL로 요청이 들어오면 doGet 또는 doPost가 실행된다 (여기서는 둘다 doProcess실행!)
    	
    	//servlet하나만 가지고 작업 가능하다 (=FrontServlet)
    	//현재 실습은 요청당 Servlet하나를 만든 것!!
    	
    	//INSERT
    	//1. 한글처리
    	//2. 데이터받기
    	//3. 비즈니스 (처리)
    	//4. 결과
    	
    	//1. 한글처리
    	request.setCharacterEncoding("UTF-8");
    	
    	//2. 데이터받기
    	String id = request.getParameter("id");
    	String email = request.getParameter("email");
    	String content = request.getParameter("content");
    	
    	//3. 로직
    	//별도의 UI를 가지지 않고, 성공시 '목록보기' / 실패시 '재입력받도록' 설계할 것
    	
    	//view단 페이지가 가지는 선언부
    	response.setContentType("text/html; charset=UTF-8");
    	//write할 수 있는 객체를 얻어내야 한다 (직접 작성할 수 없으니까!)
    	PrintWriter out = response.getWriter();
    	
    	try {
			memodao dao = new memodao();
			
			//parameter로 넣을 memo객체 생성
			//memo m = new memo(id, email, content);
			//but, 따로 만들 필요가 없당..! (다른데 사용할 일이 없으므로)
			//so, 파라미터에 직접 생성
			
			int row = dao.insertMemo(new Memo(id,email,content));
			
			if(row > 0) { //insert 성공
				//이부분은 JSP로 해도 된당 (여기서는 코드량이 많지 않으므로 그냥 작성)
				out.print("<script>");
					out.print("alert('등록성공');");
					//새로운 내용이 들어왔으니,
					//localhost:8090/WebServlet_4_Memo_mvc/MemoList 여기로 보내주겠다
					out.print("location.href='MemoList';");
				out.print("</script>");
			}else {
				//예외가 날 경우 catch로 갈 것 (so, 해당 else문은 사용될 일이 음슴)
			}
				
			
		} catch (Exception e) {
			out.print("<script>");
			out.print("alert('등록실패');");
			//등록에 실패한 경우!!
			//localhost:8090/WebServlet_4_Memo_mvc/memo.html 여기로 보내주겠다
			out.print("location.href='memo.html';");
		out.print("</script>");
		}
    	
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
