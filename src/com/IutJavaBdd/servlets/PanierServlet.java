package com.IutJavaBdd.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.IutJavaBdd.beans.Article;
import com.IutJavaBdd.beans.Panier;
import com.IutJavaBdd.managers.ArticleManager;
import com.IutJavaBdd.managers.PanierManager;
import com.IutJavaBdd.tools.FlorantPanier;
import com.IutJavaBdd.tools.Singleton;

/**
 * Servlet implementation class PanierServlet
 */
@WebServlet("/Panier")
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/panier.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		ArticleManager am = null;
		PanierManager pm = null;
		List<Panier> lst = null;
		Iterator<Panier> it = null;
		List<FlorantPanier> lstArticle = null;
		BigDecimal totalHTC = null;
		BigDecimal totalTTC = null;
		String usernameParam = (String)request.getSession().getAttribute("userSigned");
		String idArticleParam = request.getParameter("id");
		
		if(usernameParam == null) {
			usernameParam = "defaut";
		}
		
		try {
			conn = Singleton.DS.getConnection();
		} catch (Exception e) {
			try { conn.close(); } catch (Exception ignore) {}
			e.printStackTrace();
		}
		
		am = new ArticleManager(conn);
		pm = new PanierManager(conn);
		
		if(idArticleParam != null) {
			if(pm.delete(usernameParam, Integer.parseInt(idArticleParam)) == 1) {
				try { conn.commit(); } catch (Exception e) {e.printStackTrace();}
			}
		}
		
		//On récupère le pseudo de l'utilisateur s'il est connecté
		String username = (String)request.getSession().getAttribute("userSigned");
		if(username == null) {
			username = "defaut";
		}
		
		//Réception des id articles présent dans le panier ansi que de la quantité
		lst = pm.readAll(username);
		
		//Récupération de toutes les infos sur les articles
		lstArticle = new ArrayList<FlorantPanier>();
		totalHTC = new BigDecimal(0);
		totalTTC = new BigDecimal(0);
		it = lst.iterator();
		while(it.hasNext()) {
			Panier current = (Panier)it.next();
			Article currentArticle = am.read(current.getIdArticle());
			int quantiteArticle = current.getQte();
			FlorantPanier fp = new FlorantPanier(currentArticle, quantiteArticle);
			totalHTC = totalHTC.add(fp.getPrixTotal());
			lstArticle.add(fp);
		}
		
		totalTTC = totalHTC.multiply(new BigDecimal(1.20));
		totalTTC = totalTTC.setScale(2, RoundingMode.CEILING);
		
		try { conn.close(); } catch (Exception ignore) {}
		
		request.setAttribute("title", "Panier");
		request.setAttribute("lstArticle", lstArticle);
		request.setAttribute("totalHTC", totalHTC);
		request.setAttribute("totalTTC", totalTTC);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
