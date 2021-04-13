package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Reply;

public class ReplyAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("reply_writer");
		String content = request.getParameter("reply_content");
		String pwd = request.getParameter("reply_pwd");
		String idx = request.getParameter("idx");
		String userid = "empty";
		
		//Reply reply = new Reply();
		String msg="";
		
		try {
			BoardDao dao = new BoardDao();
			int idx_fk = Integer.parseInt(idx);
			
			/*
			reply.setWriter(writer);
			reply.setContent(content);
			reply.setPwd(pwd);
			reply.setIdx_fk(idx_fk);
			reply.setUserid(userid);
			*/
			
			int result = dao.replywrite(idx_fk, writer, userid, content, pwd);
			
			
			if(result > 0){
		    	msg ="success";
		    }else{
		    	msg="fail";
		    }
			
			List<Reply> replylist = dao.replylist(idx);
			
			request.setAttribute("replyList", replylist);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		request.setAttribute("board_msg", msg);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/board/Reply.jsp");

		return forward;
	}

}
