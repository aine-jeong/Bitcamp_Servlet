package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.or.bit.dto.Mvcregister;
import kr.or.bit.dto.registerdto;
import kr.or.bit.utils.ConnectionHelper;

public class Mvcregisterdao {
	
	//CRUD
	
	//글쓰기(writeOk 함수)
	//INSERT INTO mvcregister(id,pwd,email) VALUES(?,?,?)
	public int writeOk(registerdto m) {
		Connection conn = null;
		int resultrow = 0;
		
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO mvcregister(id,pwd,email) VALUES(?,?,?)";
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getEmail());
			
			resultrow = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return resultrow;
	}
	

	
}
