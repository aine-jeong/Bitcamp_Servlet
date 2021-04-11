package kr.or.bit.service;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class BoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		int pagesize = Integer.parseInt(request.getParameter("pagesize"));
		
		ActionForward forward = null;
		
		BoardDao dao;
		try {
			dao = new BoardDao();
			List<Board> boardlist = dao.list(cpage, pagesize);
			
			request.setAttribute("boardlist", boardlist);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/board_list.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
