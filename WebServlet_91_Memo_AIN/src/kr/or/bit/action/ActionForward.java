package kr.or.bit.action;

/*
 Servlet이 받는 요청
 1. 화면 출력
 2. 로직 처리
 
 여기서 해줄일~~
 1. 화면출력인가, 로직처리인가 판단
 2. 뷰의 경로
 */

public class ActionForward {
	private boolean isRedirect = false; //다른 화면으로 이동할고야말고야 (화면전환여부)
	private String path = null; //이동할 경로는 오디얌 (뷰의 주소)
	
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
