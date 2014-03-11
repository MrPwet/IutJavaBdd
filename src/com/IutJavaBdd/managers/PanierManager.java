package com.IutJavaBdd.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PanierManager {
	Connection conn;
	
	public PanierManager(Connection conn) {
		this.conn = conn;
	}
	
	public int addArticleToUser(String username, int id, int qte) {
		int n = 0;
		PreparedStatement pstm = null;
		String sql = "insert into Contient values(?,?,?);";
		
		try {
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, username);
			pstm.setInt(i++, id);
			pstm.setInt(i++, qte);
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}
	
	public int updateUsersArticle(String username, int id, int qte) {
		int n = 0;
		PreparedStatement pstm = null;
		String sql = "update Contient set qte=? where username=? and idArticle=?;";
		
		try {
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setInt(i++, qte);
			pstm.setString(i++, username);
			pstm.setInt(i++, id);
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}
	
	public int getQte(String username, int idArticle) {
		PreparedStatement pstm = null;
		int n = -1;
		String sql = "select qte from Contient where username=? and idArticle=?;";
		ResultSet rset = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, username);
			pstm.setInt(i++, idArticle);
			rset =  pstm.executeQuery();
			if(rset.next()) {
				n = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}
	
}
