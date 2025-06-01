package br.com.phoenix.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.coyote.Request;

import br.com.phoenix.utils.DB;
import br.com.phoenix.utils.Helper;
import br.com.phoenix.dao.UserDAO;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/app")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("title", "Tela de login");

		// Verificando se usuário já está logado no sistema.
		if (session.getAttribute("auth") != null) {
			String auth_user = session.getAttribute("auth_user").toString();
			String auth_pass = session.getAttribute("auth_pass").toString();

			UserDAO userDAO = new UserDAO();
			if (userDAO.authentication(auth_user, auth_pass)) {
				response.sendRedirect("app/dashboard");
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("views/index.jsp");
		request.setAttribute("title", "Tela de login");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String user = request.getParameter("user");
		String password = request.getParameter("password");

		if (user == null || password == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/index.jsp");
			request.setAttribute("error", "Preencha o campo usuário e senha");
			dispatcher.forward(request, response);
			return;
		}

		UserDAO userDAO = new UserDAO();

		if (!userDAO.authentication(user, password)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/index.jsp");
			request.setAttribute("error", "Usuário ou senha incorreto");
			dispatcher.forward(request, response);
			return;
		}

		HttpSession session = request.getSession();
		session.setAttribute("auth", true);
		session.setAttribute("auth_user", user);
		session.setAttribute("auth_pass", password);

		response.sendRedirect(request.getServletContext().getContextPath() + "/app/dashboard");
		return;
	}

}
