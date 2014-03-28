package com.IutJavaBdd.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.IutJavaBdd.tools.Singleton;
import com.IutJavaBdd.tools.TableCreator;

/**
 * Servlet implementation class ConfigServlet
 */
@WebServlet("/Config")
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/config.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlJdbc = request.getParameter("url");
		String userConf = request.getParameter("userConf");
		String passConf = request.getParameter("passConf");
		String messageInfo = null;
		
		if("".equals(urlJdbc) || "".equals(userConf)) {
			messageInfo = "La configuration à échoué !";
		}
		else {
			messageInfo = "Succès de la configuration !";
			Singleton.init(urlJdbc, userConf, passConf);
			TableCreator.dropAllTheTable();
			TableCreator.createTable();
			TableCreator.populateTable();
		}
		
		request.setAttribute("title", "Config");
		request.setAttribute("messageInfo", messageInfo);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

}
