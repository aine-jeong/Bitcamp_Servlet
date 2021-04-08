package kr.or.bit.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
	
	//만약 Action 인터페이스를 구현한다면(implement),
	//반드시 execute함수를 구현해라(강제사항).
	
	//ex.
	//execute() { return new ActionForward(); } 
	//execute함수를 구현하려면 new ActionForward해야한다 (리턴값으로 걸어놨으니까!)
}
