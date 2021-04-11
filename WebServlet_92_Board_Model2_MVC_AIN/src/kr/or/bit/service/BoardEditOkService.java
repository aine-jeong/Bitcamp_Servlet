package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class BoardEditOkService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response){

        // 1. board_edit.jsp > 입력값 받기 > board dto 객체에 담기

        // 방법 1) request.getParameter("subject") > new Board > setter > request > return
        // Board
        // 가장 일반적인 방법

        // 방법 2) usebean 액션태그 (property )통해서 setter 주입

        // 방법 3) request 객체를 통으로 넘기는 방법 (이 방법) >> 실습코드 ...

        
        String idx = request.getParameter("idx");
        
        if (idx == null || idx.trim().equals("")) {
            
            
			try {
				PrintWriter out = response.getWriter();
				out.print("<script>");
	            out.print("alert('글번호 입력 오류')");
	            out.print("location.href='board_list.jsp'");
	            out.print("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
            
        }

        BoardService service = BoardService.getInBoardService();
        int result = 0;
		try {
			result = service.board_Edit(request);
		} catch (NamingException e) {
			e.printStackTrace();
		}

        String msg = "";
        String url = "";
        if (result > 0) {
            msg = "edit success";
            url = "BoardList.board";
        } else {
            msg = "edit fail";
            url = "BoardEdit.board?idx=" + idx;
        }

        request.setAttribute("board_msg", msg);
        request.setAttribute("board_url", url);

        ActionForward forward = new ActionForward();

        forward.setRedirect(false);
        forward.setPath("/board/redirect.jsp");

        return forward;
    }

}
