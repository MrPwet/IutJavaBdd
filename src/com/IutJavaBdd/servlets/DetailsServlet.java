package com.IutJavaBdd.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.IutJavaBdd.beans.Article;
import com.IutJavaBdd.beans.ArticleCommande;
import com.IutJavaBdd.managers.ArticleManager;
import com.IutJavaBdd.managers.CommandeManager;
import com.IutJavaBdd.tools.FlorantPanier;
import com.IutJavaBdd.tools.Singleton;

/**
 * Servlet implementation class DetailsServlet
 */
@WebServlet("/Details")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/details.jsp"; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCommande = request.getParameter("idCommande");
		Connection conn = null;
		CommandeManager cm = null;
		ArticleManager am = null;
		List<ArticleCommande> lstArticleCommande = null;
		List<FlorantPanier> lstArticle = new ArrayList<FlorantPanier>();
		
		try {
			conn = Singleton.DS.getConnection();
			cm = new CommandeManager(conn);
			am = new ArticleManager(conn);
			lstArticleCommande = cm.getArticleFromCommande(Integer.parseInt(idCommande));
			for(ArticleCommande articleCommande : lstArticleCommande) {
				Article article = am.read(articleCommande.getIdArticle());
				FlorantPanier fp = new FlorantPanier(
						article,
						articleCommande.getQte(),
						articleCommande.getPrixTotal(),
						articleCommande.getPrixTotalTTC()
						);
				lstArticle.add(fp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (Exception ignore) {}
		}
		
		request.setAttribute("title", "Details de la commande");
		request.setAttribute("lstArticle", lstArticle);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
