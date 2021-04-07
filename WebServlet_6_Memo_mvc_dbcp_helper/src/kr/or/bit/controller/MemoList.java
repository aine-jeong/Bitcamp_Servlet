package kr.or.bit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.memodao;
import kr.or.bit.dto.Memo;

@WebServlet("/MemoList")
public class MemoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemoList() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("[ 목록보기 ]");
    	
    	//여기서는 얘가 서비스 클래스의 역할도 같이하도록 만든다
    	//요청받고 서비스 수행 -> Servlet이 다 하도록
    	
    	//전체 조회 요청이 들어왔당
    	//서비스 (DB에서 데이터를 가져다가 클라이언트에게 보여주는 작업)
    	//DAO를 만들어놨넹!
    	memodao dao = new memodao();
    	
    	try {
    		List<Memo> memolist = dao.getMemoList();
    		
    		//화면에 출력해서 Client 전달
    		//View 사용(JSP)
    		
    		//데이터 저장
    		request.setAttribute("memolist", memolist);
    		
    		//view페이지 설정
    		RequestDispatcher dis = request.getRequestDispatcher("/memolist.jsp");
    		
    		//view forward
    		dis.forward(request, response);
    		
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
