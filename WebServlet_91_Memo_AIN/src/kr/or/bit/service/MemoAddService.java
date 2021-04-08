package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.memodao;
import kr.or.bit.dto.Memo;

public class MemoAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
    	String email = request.getParameter("email");
    	String content = request.getParameter("content");
    	
    	//3. 로직
    	//별도의 UI를 가지지 않고, 성공시 '목록보기' / 실패시 '재입력받도록' 설계할 것
    	
    	//view단 페이지가 가지는 선언부
    	response.setContentType("text/html; charset=UTF-8");
    	//write할 수 있는 객체를 얻어내야 한다 (직접 작성할 수 없으니까!)
    	PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	try {
			memodao dao = new memodao();
			dao.insertMemo(new Memo(id,email,content));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	/////////////////////////
    	
    	ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/Memo/memolist.jsp");
		
		return forward;
	}

}
