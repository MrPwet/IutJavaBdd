package com.IutJavaBdd.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.IutJavaBdd.beans.Article;
import com.IutJavaBdd.managers.ArticleManager;
import com.IutJavaBdd.tools.Singleton;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/Article")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VUE = "/WEB-INF/article.jsp";   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestion temporaire de la connection
		Connection conn = null;
		ArticleManager am = null;
		List<Article> lst = null;
		
		//Gestion des paramètres
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		
		String messageInfo = "";
		
		try {
			conn = Singleton.DS.getConnection();
		} catch (Exception e) {
			try { conn.close(); } catch (Exception ignore) {}
			e.printStackTrace();
		}
		
		am = new ArticleManager(conn);
		
		if(action != null) {
			if(action.equals("delete") && Integer.valueOf(id) >= 0) {
				if(am.delete(id) == 1) {
					messageInfo = "Article supprimé ! ";
					try {conn.commit();} catch (Exception e) { e.printStackTrace();}
				}
				else {
					messageInfo = "Échec de la suppression ! ";
				}
			}
			else if(action.equals("add")) {
				String nom = request.getParameter("nom");
				String prix = request.getParameter("prix");
				String disponibilite = request.getParameter("dispo");
				String categorie = request.getParameter("cat");
				if(!(nom.isEmpty() || prix.isEmpty() || disponibilite.isEmpty() || categorie.isEmpty())) {
					Article article = new Article();
					article.setNomArticle(nom);
					article.setPrixArticle(new BigDecimal(prix));
					article.setDisponibiliteArticle(Integer.valueOf(disponibilite));
					article.setCategorieArticle(categorie);
				
					if(am.create(article) == 1) {
						messageInfo = "L'article à bien été ajouté !";
						try { conn.commit(); } catch (Exception e) { e.printStackTrace(); }
					} 
					else {
						messageInfo = "Échec de l'ajout !";
					}
				}
				else {
					messageInfo = "Veuillez remplir tous les champs !";
				}
			}
		}
		
		lst = am.readAll();
		
		try { conn.close(); } catch (Exception ignore) {}
		
		request.setAttribute("info", messageInfo);
		request.setAttribute("title", "Articles");
		request.setAttribute("lst", lst);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
