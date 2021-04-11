package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.utils.ThePager;

public class BoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html; charset=UTF-8");
		
		ActionForward forward = null;
		
		try {

			BoardDao dao = new BoardDao();
			int totalboardcount = dao.totalBoardCount();
			
			String ps = request.getParameter("ps"); //pagesize
			String cp = request.getParameter("cp"); //current page
			
			//List 페이지 처음 호출 ...
			if(ps == null || ps.trim().equals("")){
				//default 값 설정
				ps = "5"; //5개씩 
			}
		
			if(cp == null || cp.trim().equals("")){
				//default 값 설정
				cp = "1"; // 1번째 페이지 보겠다 
			}
			
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;
			
			//23건  % 5
			if(totalboardcount % pagesize == 0){
				pagecount = totalboardcount / pagesize; //  20 << 100/5
			}else{
				pagecount = (totalboardcount / pagesize) + 1; 
			}
			
			//전체 목록 가져오기
			List<Board> list = dao.list(cpage, pagesize); //list >> 1 , 20
			
			PrintWriter out = response.getWriter();
			
			if(list == null || list.size() == 0){
     			out.print("<tr><td colspan='5'>데이터가 없습니다</td></tr>");
     		}
			
			int pagersize = 3; //[1][2][3]
			ThePager pager = new ThePager(totalboardcount,cpage,pagesize,pagersize,"list.board");
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("list", list);
			request.setAttribute("totalboardcount", totalboardcount);
			request.setAttribute("pager", pager);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/board_list.jsp");
			
		} catch (NamingException | IOException e) {
			e.printStackTrace();
			
		}
		
		return forward;
	}

}