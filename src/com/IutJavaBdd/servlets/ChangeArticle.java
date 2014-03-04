package com.IutJavaBdd.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.IutJavaBdd.beans.Article;
import com.IutJavaBdd.managers.ArticleManager;
import com.IutJavaBdd.tools.Singleton;

/**
 * Servlet implementation class ChangeArticle
 */
@WebServlet("/ChangeArticle")
public class ChangeArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/change_article.jsp";
	private static final String VUE2 = "/WEB-INF/valid_modif.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Connection conn = null;
		ArticleManager am = null;
		Article article = null;
		
		try {
			conn = Singleton.DS.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		am = new ArticleManager(conn);
		article = am.read(id);
		
		if(article == null) {
			System.out.println("CA MARCHEPAS");
		}
		else {
			System.out.println("CA DEVRAIT MARCHER");
		}
		
		try { conn.close();  } catch (Exception ignore) {};
		
		request.setAttribute("title", "Modification");
		request.setAttribute("article", article);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		ArticleManager am = null;
		
		Article article = new Article();
		String messageInfo = "";
		
		String idArticle = request.getParameter("id");
		String nomArticle = request.getParameter("nom");
		String prixArticle = request.getParameter("prix");
		String disponibiliteArticle = request.getParameter("dispo");
		String categorieArticle = request.getParameter("cat");
		
		try { conn = Singleton.DS.getConnection(); } catch (Exception e) { e.printStackTrace();}
		
		am = new ArticleManager(conn);
		article.setIdArticle(Integer.valueOf(idArticle));
		article.setNomArticle(nomArticle);
		article.setPrixArticle(new BigDecimal(prixArticle));
		article.setDisponibiliteArticle(Integer.valueOf(disponibiliteArticle));
		article.setCategorieArticle(categorieArticle);
		
		if(am.update(article) == 1) {
			messageInfo = "L'article à bien été modifié !";
			try { conn.commit(); } catch (Exception ignore) {}
		}
		else {
			messageInfo = "Échec de la modification de l'article !";
		}
		
		try { conn.close(); } catch (Exception ignore) {}
		
		request.setAttribute("messageInfo", messageInfo);
		this.getServletContext().getRequestDispatcher(VUE2).forward(request, response);
	}

}
