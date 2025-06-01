package br.com.phoenix.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Helper {

	/**
	 * Realiza tratamento de paramêtros
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParameterTreat(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		Enumeration<String> requestParams = request.getParameterNames();

		while (requestParams.hasMoreElements()) {
			String name = requestParams.nextElement();
			String value = request.getParameter(name);

			params.put(name, (value == null || value.trim().isEmpty()) ? null : value);
		}
 
		return params;
	}

}
