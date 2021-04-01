package prac;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/pracController", "/bbs" })
public class pracController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pracController() {
        super();
        System.out.println("처음 요청하셨어욤");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 한글처리
		request.setCharacterEncoding("UTF-8");
		//2. 데이터 받기
		String cmd = request.getParameter("cmd");
		String msg = "";
		//3. 서비스 수행하기
		if(cmd == null) {
			msg = " 뭣이 필요한지 말을 해라 ";
			System.out.println("아무것도 안줄거얌");
		}else if(cmd.equals("book")) {
			msg = " 📕📗📘 책 가져라 📘📗📕 ";
			System.out.println("책 드릴게욤");
		}else if(cmd.equals("pencil")) {
			msg = " ✏✏✏ 연필 가져라 ✏✏✏ ";
			System.out.println("연필 드릴게욤");
		}else {
			msg = " 🚨 에러예용... ";
		}
		//4. 결과 저장하기 / 데이터 담기
		request.setAttribute("msg", msg);
		
		//5. 결과 전달하기
		RequestDispatcher dis = request.getRequestDispatcher("/prac.jsp");
		//view단에다가 forward 시키기
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
