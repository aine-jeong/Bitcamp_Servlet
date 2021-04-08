package kr.or.bit.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.memodao;
import kr.or.bit.dto.Memo;

public class MemoListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("[ 목록보기 ]");
		
		memodao dao = new memodao();
    	
    	try {
    		List<Memo> memolist = dao.getMemoList();
    		
    		//화면에 출력해서 Client 전달
    		//View 사용(JSP)
    		
    		//데이터 저장
    		request.setAttribute("memolist", memolist);
    		
    		//view forward
    		ActionForward forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/Memo/memolist.jsp");
    		
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return null;
	}

}
