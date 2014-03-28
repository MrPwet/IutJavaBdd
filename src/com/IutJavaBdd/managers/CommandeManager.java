package com.IutJavaBdd.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.IutJavaBdd.beans.ArticleCommande;
import com.IutJavaBdd.beans.Commande;
import com.IutJavaBdd.tools.FlorantPanier;

public class CommandeManager {
	private Connection conn;
	
	public CommandeManager(Connection conn) {
		this.conn = conn;
	}
	
	public int create(Commande cmd) {
		int n = 0;
		PreparedStatement pstm = null;
		String sql = "insert into Commande values(null, ?, ?, ?);";
		ResultSet rset = null;
		
		try {
			int i = 1;
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(i++, cmd.getUsername());
			pstm.setBigDecimal(i++, cmd.getPrixTotal());
			pstm.setDate(i++, cmd.getDateCommande());
			pstm.executeUpdate();
			rset = pstm.getGeneratedKeys();
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

	public List<Commande> readAll(String username) {
		List<Commande> lstCommande = new ArrayList<Commande>();
		PreparedStatement pstm = null;
		String sql = "select idCommande, username, prixTotal, dateCommande from Commande where username=?;";
		ResultSet rset = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Commande cmd = new Commande();
				cmd.setIdCommande(rset.getInt(1));
				cmd.setUsername(rset.getString(2));
				cmd.setPrixTotal(rset.getBigDecimal(3));
				cmd.setDateCommande(rset.getDate(4));
				lstCommande.add(cmd);
			}
		} catch (Exception e) {
			
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		return lstCommande;
	}
	
	public int addArticleToCommande(int idCommande, FlorantPanier lst) {
		int n = 0;
		PreparedStatement pstm = null;
		String sql = "insert into Concerne values(?,?,?,?,?);";
		int i = 1;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(i++, idCommande);
			pstm.setInt(i++, lst.getArticle().getIdArticle());
			pstm.setInt(i++, lst.getQuantite());
			pstm.setBigDecimal(i++, lst.getPrixTotal());
			pstm.setBigDecimal(i++, lst.getPrixTotalTTC());
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}

	public List<ArticleCommande> getArticleFromCommande(int idCommande) {
		PreparedStatement pstm = null;
		List<ArticleCommande> lstArticle = new ArrayList<ArticleCommande>();
		String sql = "select idCommande, idArticle, qte, prixTotal, prixTotalTTC from Concerne where idCommande=?;";
		ResultSet rset = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idCommande);
			rset = pstm.executeQuery();
			while(rset.next()) {
				ArticleCommande article = new ArticleCommande();
				article.setIdCommande(rset.getInt(1));
				article.setIdArticle(rset.getInt(2));
				article.setQte(rset.getInt(3));
				article.setPrixTotal(rset.getBigDecimal(4));
				article.setPrixTotalTTC(rset.getBigDecimal(5));
				lstArticle.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return lstArticle;
	}
	
	
}
