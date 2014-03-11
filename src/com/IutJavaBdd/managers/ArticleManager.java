package com.IutJavaBdd.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.IutJavaBdd.beans.Article;

public class ArticleManager {
	private Connection conn;
	
	public ArticleManager(Connection conn) {
		this.conn = conn;
	}
	
	public int create(Article article) {
		int n = 0;
		PreparedStatement pstm = null;
		String sql = "insert into Article values(null, ?, ?, ?, ?)";
		int i = 1;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(i++, article.getNomArticle());
			pstm.setBigDecimal(i++, article.getPrixArticle());
			pstm.setInt(i++, article.getDisponibiliteArticle());
			pstm.setString(i++, article.getCategorieArticle());
			
			n = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}
	
	public Article read(int id) {
		Article article = null;
		PreparedStatement pstm = null;
		String sql = "select idArticle, nomArticle, prixArticle, disponibiliteArticle, categorieArticle from Article where idArticle=?;";
		ResultSet rset = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();
			if(rset.next()) {
				article = new Article();
				article.setIdArticle(rset.getInt(1));
				article.setNomArticle(rset.getString(2));
				article.setPrixArticle(rset.getBigDecimal(3));
				article.setDisponibiliteArticle(rset.getInt(4));
				article.setCategorieArticle(rset.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { rset.close(); } catch (Exception ignore) {}
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return article;
	}
	
	public List<Article> readAll() {
		Statement stm = null;
		String sql = "select idArticle, nomArticle, prixArticle, disponibiliteArticle, categorieArticle from Article;";
		List<Article> lst = new ArrayList<Article>();
		ResultSet rset = null;
		
		try {
			stm = conn.createStatement();
			rset = stm.executeQuery(sql);
			while(rset.next()) {
				Article article = new Article();
				article.setIdArticle(rset.getInt(1));
				article.setNomArticle(rset.getString(2));
				article.setPrixArticle(rset.getBigDecimal(3));
				article.setDisponibiliteArticle(rset.getInt(4));
				article.setCategorieArticle(rset.getString(5));
				lst.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { rset.close(); } catch (Exception ignore) {}
			try { stm.close(); } catch (Exception ignore) {}
		}
		
		return lst;
	}
	
	public List<Article> readAll(String critere) {
		PreparedStatement pstm = null;
		String sql = "select idArticle, nomArticle, prixArticle, disponibiliteArticle, categorieArticle from Article order by " + critere +";";
		List<Article> lst = new ArrayList<Article>();
		ResultSet rset = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Article article = new Article();
				article.setIdArticle(rset.getInt(1));
				article.setNomArticle(rset.getString(2));
				article.setPrixArticle(rset.getBigDecimal(3));
				article.setDisponibiliteArticle(rset.getInt(4));
				article.setCategorieArticle(rset.getString(5));
				lst.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { rset.close(); } catch (Exception ignore) {}
			try { pstm.close(); } catch (Exception ignore) {}
		}
		return lst;
	}

	public int update(Article article) {
		PreparedStatement pstm = null;
		String sql = "update Article set nomArticle=?, prixArticle=?, disponibiliteArticle=?, categorieArticle=? where idArticle=?;";
		int n = 0;
		int i = 1;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(i++, article.getNomArticle());
			pstm.setBigDecimal(i++, article.getPrixArticle());
			pstm.setInt(i++, article.getDisponibiliteArticle());
			pstm.setString(i++, article.getCategorieArticle());
			pstm.setInt(i++, article.getIdArticle());
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}
	
	public int delete(String idArticle) {
		PreparedStatement pstm = null;
		String sql = "delete from Article where idArticle=?;";
		int n = 0;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idArticle);
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		return n;
	}
}
