package com.IutJavaBdd.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.IutJavaBdd.beans.Article;
import com.IutJavaBdd.managers.ArticleManager;
import com.IutJavaBdd.managers.PanierManager;
import com.IutJavaBdd.tools.Singleton;

/**
 * Servlet implementation class Article2
 */
@WebServlet("/Article2")
public class Article2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/article2.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Article2Servlet() {
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
		List<Article> lst = null;
		
		//Gestion des paramètres
		String idParam = request.getParameter("id");
		String qteParam = request.getParameter("qte");
		String usernameParam = "user1";
		
		try {
			conn = Singleton.DS.getConnection();
		} catch (Exception e) {
			try { conn.close(); } catch (Exception ignore) {}
			e.printStackTrace();
		}
		
		am = new ArticleManager(conn);
		pm = new PanierManager(conn);
		
		if(idParam != null && qteParam != null) {
			int id = Integer.valueOf(idParam);
			int qte = Integer.valueOf(qteParam);
			int qteInitial = pm.getQte(usernameParam, id);
			if(qteInitial > 0) {
				if(pm.updateUsersArticle(usernameParam, id, qte + qteInitial) == 1) {
					try { conn.commit(); } catch (Exception e) { e.printStackTrace(); }
				}
			}
			else {
				if(pm.addArticleToUser(usernameParam, id, qte) == 1) {
					try { conn.commit(); } catch (Exception e) { e.printStackTrace(); }
				}
			}
		}
		
		lst = am.readAll();
		
		try { conn.close(); } catch (Exception ignore) {}
		
		request.setAttribute("title", "Articles");
		request.setAttribute("lst", lst);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
