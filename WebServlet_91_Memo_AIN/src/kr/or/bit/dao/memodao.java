package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.bit.dto.Memo;
import kr.or.bit.utils.ConnectionHelper;

/*
 1. DB연결
 2. CRUD 함수 생성
 - 1개의 테이블에 대해서 (여기서는 memo 테이블에 대해서!)
 2.1. 전체조회 : SELECT id, email, content FROM memo (필요하다면 ORDER BY)
 2.2. 조건조회 : SELECT id, email, content FROM memo where id=? (데이터가 1건 나온다는 것이 확실해야 한다. (id = PK))
 2.3. 삽입 : INSERT INTO memo(id,email,content) VALUES (?,?,?)
 2.4. 수정 : UPDATE memo SET email=? , content=? WHERE id=?
 2.5. 삭제 : DELETE FROM memo WHERE id=?
 + 2.6. 검색 (LIKE) : WHERE email LIKE '%@naver%'
 + 필요하다면 더 만드세용
 
 # 원칙적으로 
 하나의 DTO는 DB테이블과 1대1로 만들고
 DAO는 CRUD 함수를 생성해두는 것
 
 
 -- Singleton은 좋은 작업은 아니다 (나중에 안쓰는듯 ㅇ.ㅇ ...) => ConnectionPool 사용
 -- ArrayList, HaspMap 반드시 복습해라 ~~~~~~~
 */

public class memodao {
	/* 
	 * 싱글톤을 사용한 DB연결은 학습용!!!
	 * 더이상 사용하지 않아용
	Connection conn = null;
	
	public memodao() {
		conn = SingletonHelper.getConnection("oracle"); 
	}
	*/
	
	/* POOL방식을 하나하나 넣지 않아여
	 * WHY? DAO가 여러개 나오면 하나하나 넣어두고 만약 수정이 필요해지면 하나하나 수정해야대니까 ...
	 * 
	 * //POOL방식 DataSource ds = null; 
	 * public memodao() { 
	 * try { Context context = newInitialContext(); 
	 * ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle"); 
	 * } 
	 * catch(NamingException e) {
	 *  e.printStackTrace(); 
	 *  } 
	 * }
	 */
	
	
	//전체조회
	public List<Memo> getMemoList() throws SQLException { // 데이터 여러건을 리턴하는 것
		
		PreparedStatement pstmt = null; 
		String sql = "SELECT id, email, content FROM memo";
		
		////POOL/////////////////////////
		Connection conn = ConnectionHelper.getConnection("oracle");
		////////////////////////////
		
		//컴파일 시켜두는것
		pstmt = conn.prepareStatement(sql); //원래 예외처리를 해야한다!!( 여기서는 함수를 쓰는 사람이 try catch하도록 만들었다 )
		ResultSet rs = pstmt.executeQuery(); //DB서버의 메모리에 접근할 수 있는 주소값
		
		//DTO가 데이터를 한건밖에 담을 수 없으므로 (memo들을 담고있는) List 생성
		//다형성 (List)
		List<Memo> memolist = new ArrayList<Memo>();
		// [new memo][new memo][new memo][new memo]
		
		//출력
		while(rs.next()) {
			Memo m = new Memo();
			m.setId(rs.getString("id"));
			m.setEmail(rs.getString("email"));
			m.setContent(rs.getString("content"));
			
			memolist.add(m);
		}
		
		//연결은 닫으면 안된다
		ConnectionHelper.close(rs);
		ConnectionHelper.close(pstmt);
		
		///POOL반환/////
		ConnectionHelper.close(conn);
		///////////////
		
		
		return memolist;
	}
	
	//조건조회
	//조건조회의 경우 데이터 1건 보장 (PK / UNIQUE 제약이 걸려있어야 한다)
	public Memo getMemoListById(String id) {
		//select id, email, content from memo where id=?
		//memo m = new memo();
		//return m
		
		//여기서는 필요없어서 일단 패스!
		
		return null;
	}
	
	//삽입
	//public int insertMemo(String id, String email, String content)
	// parameter를 객체형태로 받고싶다 (DTO로!)
	public int insertMemo(Memo m) {
		Connection conn = null;
		int resultrow = 0;

		PreparedStatement pstmt = null;
		String sql = "INSERT INTO memo(id,email,content) VALUES (?,?,?)";

		try {
			////POOL/////////////////////
			conn = ConnectionHelper.getConnection("oracle");
			////////////////////////////
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getContent());

			resultrow = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return resultrow;
	}
	
	
	//수정
	public int updateMemo(Memo m) {
		return 0;
	}
	
	//삭제
	//parameter를 dto로 받을 필요가 없음 (전체 데이터가 필요한 것이 아니니까!)
	public int deleteMemo(String id) { 
		return 0;
	}
	
	//검색
	public Memo idSearchByEmail(String email) {
		return null;
	}
	
	//ID중복확인함수 (ID유무 확인!)
	public String isCheckById(String id) {
		
		Connection conn = null;
		
		String ismemoid = null;
		//PreparedStatement
		//미리 SQL문이 셋팅된 Statement가 DB가에 전송되어져서 컴파일되어지고, 
		//SQL문의 ?만 나중에 추가 셋팅해서 실행이 되어지는 준비된 Statement  
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id FROM memo WHERE id=?";	
		
		try {
			//POOL////
			conn = ConnectionHelper.getConnection("oracle");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ismemoid = "false"; //아이디가 한개라도 있다면 false
			}else {
				ismemoid = "true";
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return ismemoid;
	}
	
	
}
