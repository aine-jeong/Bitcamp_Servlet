package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class ReplyDeleteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx_fk = request.getParameter("idx");// 댓글의 원본 게시글 번호
		String no = request.getParameter("no");// 댓글의 순번(PK)
		String pwd = request.getParameter("delPwd");// 댓글의 암호

		String msg = "";

		if (idx_fk == null || no == null || pwd == null || no.trim().equals("")) {
			msg = "이상한 링크 입니다.";
		} else {

			try {
				BoardDao dao = new BoardDao();

				int result = dao.replyDelete(no, pwd);
				
				if (result > 0) {
					msg = "댓글 삭제 성공";
				} else {
					msg = "댓글 삭제 실패";
				}

			} catch (NamingException e) {
				e.printStackTrace();
			}

		}

		request.setAttribute("board_msg", msg);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/Reply.jsp");

		return forward;
	}

}
