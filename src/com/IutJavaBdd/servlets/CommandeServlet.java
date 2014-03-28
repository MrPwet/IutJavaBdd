package com.IutJavaBdd.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.IutJavaBdd.beans.Article;
import com.IutJavaBdd.beans.Commande;
import com.IutJavaBdd.beans.Panier;
import com.IutJavaBdd.managers.ArticleManager;
import com.IutJavaBdd.managers.CommandeManager;
import com.IutJavaBdd.managers.PanierManager;
import com.IutJavaBdd.tools.FlorantPanier;
import com.IutJavaBdd.tools.Singleton;

/**
 * Servlet implementation class CommandeServlet
 */
@WebServlet("/Commande")
public class CommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/commande.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandeManager cm = null;
		Connection conn = null;
		List<Commande> lstCommande = null;
		String username = (String)request.getSession().getAttribute("userSigned");
		if(username == null) {
			username = "defaut";
		}
		String validation = request.getParameter("valider");
		String messageInfo = null;
		
		if("true".equals(validation)) {
			if(validerPanier(username)) {
				messageInfo = "Panier validé !";
			}
			else {
				messageInfo = "Manque de stock ! Panier refusé !";
			}
		}
		
		try {
			conn = Singleton.DS.getConnection();
			cm = new CommandeManager(conn);
			lstCommande = cm.readAll(username);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (Exception ignore) {}
		}
		
		request.setAttribute("title", "Commande");
		request.setAttribute("messageInfo", messageInfo);
		request.setAttribute("lstCommande", lstCommande);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private boolean validerPanier(String username) {
		boolean n = false;
		Connection conn = null;
		CommandeManager cm = null;
		PanierManager pm = null;
		ArticleManager am = null;
		//Contient la liste des articles du panier
		List<Panier> articlesPanier = null;
		//Contient la liste des articles avec info prix
		List<FlorantPanier> articlesInfo = new ArrayList<FlorantPanier>();
		Commande cmd = new Commande();
		BigDecimal total = new BigDecimal(0);
		Date date = new Date(new java.util.Date().getTime());
		int idCommande = 0;
		
		try {
			conn = Singleton.DS.getConnection();
			cm = new CommandeManager(conn);
			pm = new PanierManager(conn);
			am = new ArticleManager(conn);
			
			articlesPanier = pm.readAll(username);
			for(Panier p : articlesPanier) {
				Article a = am.read(p.getIdArticle());
				FlorantPanier fp = new FlorantPanier(
						a,
						p.getQte());
				articlesInfo.add(fp);
				total = total.add(fp.getPrixTotalTTC());
			}
			
			cmd.setUsername(username);
			cmd.setPrixTotal(total);
			cmd.setDateCommande(date);
			
			//On crée la commande et on récupère l'id
			idCommande = cm.create(cmd);
			
			//On ajoute les articles du panier à la commande
			for(FlorantPanier fp : articlesInfo) {
				cm.addArticleToCommande(idCommande, fp);
				pm.delete(username, fp.getArticle().getIdArticle());
			}
			
			//La va falloir check pour savoir si on commit ou non
			if(checkDisponibilite(articlesInfo)) {
				diminuerQuantite(am,articlesInfo);
				conn.commit();
				n = true;
			}
			else {
				conn.rollback();
				n = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.close(); } catch(Exception ignore) {}
		}
		
		return n;
	}
	
	private boolean checkDisponibilite(List<FlorantPanier> lst) {
		for(FlorantPanier fp : lst) {
			if(fp.getArticle().getDisponibiliteArticle() < fp.getQuantite()) {
				return false;
			}
		}
		
		return true;
		
	}
	
	private int diminuerQuantite(ArticleManager am, List<FlorantPanier> lst) {
		int n = 0;
		
		for(FlorantPanier fp : lst) {
			int quantiteAchetee = fp.getQuantite();
			int disponibilite = fp.getArticle().getDisponibiliteArticle();
			int newDispo = disponibilite - quantiteAchetee;
			Article a = new Article();
			a.setIdArticle(fp.getArticle().getIdArticle());
			a.setNomArticle(fp.getArticle().getNomArticle());
			a.setPrixArticle(fp.getArticle().getPrixArticle());
			a.setCategorieArticle(fp.getArticle().getCategorieArticle());
			a.setDisponibiliteArticle(newDispo);
			am.update(a);
		}
		
		return n;
	}
}

