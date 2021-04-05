package kr.or.bit.dto;

/*
 create table memo(
	id varchar2(15) not null,
	email varchar2(20) not null,
	content varchar2(100)
);

select id, email. content from memo where id=?  => 데이터 1건을 담을 수 있는 클래스 (DTO)
>> [hong, hong@naver.com, 방가방가] 와 같은 결과를 담을 수 있는 클래스

DTO는 DB에 있는 Table의 구조와 동일하게 만들면 된다(컬럼명까지!) (why? 자동화)


###
DB의 테이블에 있는 데이터를 담을 수 있는 클래스
테이블과 1대1로 만들어둔다.
###

데이터를 가져왔을 때 new memo 해서 여기다 담는다고 생각하면 된다.
 */

public class memo {
	private String id;
	private String email;
	private String content;
	
	public memo() {}

	public memo(String id, String email, String content) {
		super();
		this.id = id;
		this.email = email;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "memo [id=" + id + ", email=" + email + ", content=" + content + "]";
	}
	
	
}
