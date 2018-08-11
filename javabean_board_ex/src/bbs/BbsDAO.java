package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BbsDAO {
		
		private Connection conn;
		private ResultSet rs;
		
		public BbsDAO() {
			try {
				String dbURL = "jdbc:mysql://localhost/syrup?serverTimezone=UTC";
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(dbURL, "root", "tmdgml65");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public String getDate() {
			String sql = "select now()";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "";
			
		}
		public int getNext() {
			String sql = "select bbsID from bbs order by bbsID desc";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getInt(1) + 1;
				}
				return 1;
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
		public int write(String bbsTitle, String userID, String bbsContent) {
			String sql = "insert into bbs value (?, ?, ?, ?, ?, ?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getInt(1) + 1;
				}
				return 1;
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;
			
		}

	}


