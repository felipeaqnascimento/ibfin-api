package br.com.ibrowse.security;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.ibrowse.utils.UsuarioTokenUtils;

@Component
public class FinanceiroSessionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(FinanceiroSessionInterceptor.class);
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info("Before handling the request");
		String token = request.getHeader("X-Session-Data");
		if ("".equals(token)) {
			throw new SessionAuthenticationException("Session Token vazio");
		}

		try {
			if (!isTokenValid(token)) {
				throw new SessionAuthenticationException("Session Token inválido");
			}
		} catch (Exception e) {
			throw new SessionAuthenticationException("Session Token inválido");
		}

		return super.preHandle(request, response, handler);
	}

	private static boolean isTokenValid(String token) {
		String[] decoded = UsuarioTokenUtils.decode(token);
		String validity = decoded[1];
		LocalDateTime parse = LocalDateTime.parse(validity);
		return LocalDateTime.now().isBefore(parse);
	}
}
