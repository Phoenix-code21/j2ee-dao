package br.com.phoenix.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.phoenix.model.User;
import br.com.phoenix.dao.UserDAO;
import br.com.phoenix.utils.Helper;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/app/dashboard/edit/*")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
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
		session.setAttribute("title", "Dashboard:: Editar usuário");

		UserDAO userDAO = new UserDAO();

		Map<String, String> params = Helper.getParameterTreat(request);
		String action = params.get("action");

		if (action == null) {
			request.setAttribute("users", userDAO.alls());
		} else if (action.equals("edit")) {
			if (params.get("id") == null) {
				request.setAttribute("error", "001 - Usuário não identificado.");
			} else {
				int id = Integer.parseInt(params.get("id"));
				List<User> user = userDAO.findById(id);

				if (user.size() > 0) {
					request.setAttribute("edit", user);
				} else {
					request.setAttribute("error", "002 - Usuário não identificado.");
				}
			}
		} else if (action.equals("remove")) {
			if (params.get("id") == null) {
				request.setAttribute("error", "001 - Usuário não identificado.");
			} else {

				int id = Integer.parseInt(params.get("id"));
				List<User> user = userDAO.findById(id);

				if (user.get(0).getUser().equals(session.getAttribute("auth_user").toString())) {
					request.setAttribute("error", "Você não pode remover seu próprio usuário.");
				} else {
					if (user.size() > 0) {
						request.setAttribute("remove", user);
					} else {
						request.setAttribute("error", "002 - Usuário não identificado.");
					}
				}

			}
		} else {
		}

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/edit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		UserDAO userDAO = new UserDAO();
		Map<String, String> params = Helper.getParameterTreat(request);

		String action = params.get("action");

		if (action == null) {
			request.setAttribute("error", "Nenhuma ação definida");
		} else {

			// editar
			if (action.equals("edit")) {
				int id = Integer.parseInt(params.get("id"));
				String user = params.get("user");
				String password = params.get("password");

				if (userDAO.findById(id).size() == 0) {
					request.setAttribute("error", "Não foi possível atualizar este usuário");
				} else if (userDAO.differentFromID(id, user)) {
					request.setAttribute("error", "Usuário já registrado no banco de dados");
				} else {
					List<User> data = new ArrayList<User>();
					data.add(new User(id, user, password));

					if (userDAO.update(data)) {
						request.setAttribute("success", "Dados de usuário salvo com sucesso");
					} else {
						request.setAttribute("error", "Erro ao salvar dados de usuário");
					}
				}
			}

			// remover
			if (action.equals("remove")) {
				int id = Integer.parseInt(params.get("id"));

				if (userDAO.findById(id).size() == 0) {
					request.setAttribute("error", "001 - Não foi possível remover este usuário");
				}

				if (userDAO.delete(id)) {
					response.sendRedirect(request.getContextPath() + "/app/dashboard/edit");
					return;
				} else {
					request.setAttribute("error", "002 - Não foi possível remover este usuário");
				}

			}
		}

		doGet(request, response);
	}

}
