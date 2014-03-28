package com.IutJavaBdd.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.IutJavaBdd.managers.UserManager;
import com.IutJavaBdd.tools.Singleton;

/**
 * Servlet implementation class ConnectionServlet
 */
@WebServlet("/Connection")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VUE = "/WEB-INF/connection.jsp"; 
    private static final String VUE2 = "/WEB-INF/connection_info.jsp";  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dc = request.getParameter("dc");
		
		if("true".equals(dc)) {
			request.getSession().removeAttribute("userSigned");
		}
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		UserManager us = null;
		Connection conn = null;
		String msgInfo = null;
		
		if(username != null && password != null) {
			try {
				conn = Singleton.DS.getConnection();
				us = new UserManager(conn);
				if(us.check(username, password) == 1) {
					request.getSession().setAttribute("userSigned", username);
					msgInfo = "Vous êtes bien authentifié !";
				}
				else {
					msgInfo = "Erreur lors de la connection !";
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try { conn.close(); } catch (Exception ignore) {}
			}
		}
		
		request.setAttribute("msgInfo", msgInfo);
		this.getServletContext().getRequestDispatcher(VUE2).forward(request, response);
	}

}
