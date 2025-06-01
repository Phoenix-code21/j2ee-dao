package br.com.phoenix.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.phoenix.utils.Helper;
import br.com.phoenix.dao.UserDAO;
import br.com.phoenix.utils.DB;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/app/dashboard/register/*")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("title", "Dashboard:: Cadastro de usuário");

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		UserDAO userDAO = new UserDAO();
		
		Map<String, String> post = Helper.getParameterTreat(request);

		String user = post.get("user");
		String password = post.get("password");

		if (user == null || password == null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/register.jsp");
			request.setAttribute("error", "Preencha o campo usuário e senha");
			dispatcher.forward(request, response);
			return;
		}
		
		if(userDAO.exists(user)) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/register.jsp");
			request.setAttribute("error", "Usuário existente! entre com um novo nome de usuário.");
			dispatcher.forward(request, response);
			return;
		}
		
		if(userDAO.register(user, password)) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/register.jsp");
			request.setAttribute("success", "Usuário cadastrado com sucesso.");
			dispatcher.forward(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/register.jsp");
		request.setAttribute("error", "Erro ao cadastrar usuário.");
		dispatcher.forward(request, response);

		doGet(request, response);
	}

}
