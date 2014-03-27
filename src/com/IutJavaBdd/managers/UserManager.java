package com.IutJavaBdd.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserManager {
	private Connection conn;
	
	public UserManager(Connection conn) {
		this.conn = conn;
	}

	public int check(String username, String password) {
		int n = 0;
		int i = 1;
		String sql = "select * from User where username=? and password=?";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(i++, username);
			pstm.setString(i++, password);
			rset = pstm.executeQuery();
			if(rset.next()) {
				n = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}

}
