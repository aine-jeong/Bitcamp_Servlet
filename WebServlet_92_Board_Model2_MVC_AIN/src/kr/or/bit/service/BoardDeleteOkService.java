package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class BoardDeleteOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");
        String pwd = request.getParameter("pwd");
        
        ActionForward forward = new ActionForward();
        
        String msg="";
        String url="";
        
		try {
			BoardDao dao = new BoardDao();
			int result = dao.deleteOk(idx, pwd);
			
			if(result > 0){
	            msg="delete success";
	            url="BoardList.board";
	        }else{
	            msg="delete fail";
	            url="BoardList.board";
	        }
		} catch (NamingException e) {
			e.printStackTrace();
		}
        
        request.setAttribute("board_msg",msg);
        request.setAttribute("board_url",url);
        
        forward.setRedirect(false);
        forward.setPath("/board/redirect.jsp");
        
        return forward;
	}

}
