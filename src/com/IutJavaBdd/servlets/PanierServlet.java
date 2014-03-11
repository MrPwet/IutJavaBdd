package com.IutJavaBdd.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.IutJavaBdd.beans.Article;
import com.IutJavaBdd.beans.Panier;
import com.IutJavaBdd.managers.ArticleManager;
import com.IutJavaBdd.managers.PanierManager;
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
		Map<Article, Integer> lstArticle = null;
		BigDecimal total = null;
		String usernameParam = request.getParameter("user");
		String idArticleParam = request.getParameter("id");
		
		try {
			conn = Singleton.DS.getConnection();
		} catch (Exception e) {
			try { conn.close(); } catch (Exception ignore) {}
			e.printStackTrace();
		}
		
		am = new ArticleManager(conn);
		pm = new PanierManager(conn);
		
		if(usernameParam != null && idArticleParam != null) {
			if(pm.delete(usernameParam, Integer.parseInt(idArticleParam)) == 1) {
				try { conn.commit(); } catch (Exception e) {e.printStackTrace();}
			}
		}
		
		//Réception des id articles présent dans le panier ansi que de la quantité
		lst = pm.readAll();
		
		//Récupération de toutes les infos sur les articles
		lstArticle = new HashMap<Article, Integer>();
		it = lst.iterator();
		while(it.hasNext()) {
			Panier current = (Panier)it.next();
			lstArticle.put(am.read(current.getIdArticle()), current.getQte());
		}
		
		try { conn.close(); } catch (Exception ignore) {}
		
		request.setAttribute("title", "Panier");
		request.setAttribute("lstArticle", lstArticle);
		request.setAttribute("total", total);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
