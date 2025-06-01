package br.com.phoenix.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.phoenix.dao.UserDAO;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns = { "/app/*" })
public class AuthFilter extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public AuthFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();

		String path = servletRequest.getRequestURI().substring(servletRequest.getContextPath().length());

		if ("/app".equals(path)) {
			if (session.getAttribute("auth") != null) {
				servletResponse.sendRedirect(request.getServletContext().getContextPath() + "/app/dashboard");
				return;
			} else {
				chain.doFilter(request, response); // deixa seguir para tela de login
				return;
			}
		}

		// Verificando se usuário não está logado
		if (session.getAttribute("auth") == null) {
			servletResponse.sendRedirect(request.getServletContext().getContextPath() + "/app");
			return;
		}

		// deslogue o usuário caso haja alguma alteração no user
		if (!(new UserDAO().exists(session.getAttribute("auth_user").toString()))) {
			session.removeAttribute("auth");
			session.removeAttribute("auth_user");
			session.removeAttribute("auth_pass");
			servletResponse.sendRedirect(request.getServletContext().getContextPath() + "/app");
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
