package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class BoardAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String filename = request.getParameter("filename");
		
		ActionForward forward = new ActionForward();
		
		try {
			BoardDao dao = new BoardDao();
			int result = dao.writeok(subject, writer, email, homepage, content, pwd, filename);
			
			 String msg="";
			 String url="";
			 if(result > 0){
			 	msg ="insert success";
			  	url ="board_list.jsp";
			 }else{
			   	msg="insert fail";
			   	url="board_write.jsp";
			 }
			    
			 request.setAttribute("board_msg",msg);
			 request.setAttribute("board_url", url);
			    
			 forward.setRedirect(false);
			 forward.setPath("/board/redirect.jsp");
			    
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
