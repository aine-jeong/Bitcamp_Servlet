package kr.or.bit.dto;

public class Mvcregister {
	private int id;
	private String pwd;
	private String emaill;
	
	//생성자는 필요에 따라 만드세염
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmaill() {
		return emaill;
	}
	public void setEmaill(String emaill) {
		this.emaill = emaill;
	}
	
	@Override
	public String toString() {
		return "mvcregister [id=" + id + ", pwd=" + pwd + ", emaill=" + emaill + "]";
	}
	
	
	
	
}
