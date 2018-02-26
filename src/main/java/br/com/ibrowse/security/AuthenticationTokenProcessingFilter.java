package br.com.ibrowse.security;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.util.NestedServletException;

import br.com.ibrowse.bean.RoleUser;
import br.com.ibrowse.bean.jpa.UsuariosEntity;
import br.com.ibrowse.data.repository.jpa.UsuariosJpaRepository;
import br.com.ibrowse.utils.UsuarioTokenUtils;

public class AuthenticationTokenProcessingFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger LOGGER = LoggerFactory.logger(AuthenticationTokenProcessingFilter.class);
	private static final String SECURITY_TOKEN_KEY = "X-Auth-Token";
	private static final String FILTER_APPLIED = "TokenFilter";

	@Autowired
	UsuariosJpaRepository usuariosJpaRepository;

	protected AuthenticationTokenProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	public UsuariosJpaRepository getUsuariosJpaRepository() {
		return usuariosJpaRepository;
	}

	public void setUsuariosJpaRepository(UsuariosJpaRepository usuariosJpaRepository) {
		this.usuariosJpaRepository = usuariosJpaRepository;
	}

	@Autowired
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
			chain.doFilter(request, response);
			return;
		}

		if (request.getAttribute(FILTER_APPLIED) != null) {
			chain.doFilter(request, response);
			return;
		}

		request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

		if (!requiresAuthentication(request, response)) {
			chain.doFilter(request, response);
			return;
		}

		Authentication authResult;
		try {
			authResult = attemptAuthentication(request, response);
		} catch (AuthenticationException failed) {
			unsuccessfulAuthentication(request, response, failed);
			return;
		}

		try {
			successfulAuthentication(request, response/* , chain */ , authResult);
		} catch (NestedServletException ex) {
			LOGGER.warn(ex.getMessage(), ex);
			if (ex.getCause() instanceof AccessDeniedException) {
				unsuccessfulAuthentication(request, response, new LockedException("Forbidden"));
			}
		}
	}

	public Authentication attemptAuthentication(HttpServletRequest request,
		HttpServletResponse response)
				throws AuthenticationException, IOException, ServletException {
		String token = request.getHeader(SECURITY_TOKEN_KEY);

		AbstractAuthenticationToken userAuthenticationToken =
				authUserByToken(token);
		if (userAuthenticationToken == null) {
			throw new AuthenticationServiceException(MessageFormat.format("Error {0}", "Bad Token"));
		}

 return userAuthenticationToken;
 }

	private AbstractAuthenticationToken authUserByToken(String token) {
		AbstractAuthenticationToken authToken = null;
		try {
			if (token != null) {
				String username = UsuarioTokenUtils.extractUsername(token);
				UsuariosEntity usuario = new UsuariosEntity();

				if (UsuarioTokenUtils.validateToken(usuario, token)) {
					LOGGER.info("valid token found");
					authToken = new UsernamePasswordAuthenticationToken(usuario.getSenha(),
							Arrays.asList(new RoleUser()));
				} else {
					LOGGER.info("invalid token");
				}
			} else {
				LOGGER.info("no token found");
			}
		} catch (Exception e) {
			LOGGER.error("Error during authUserByToken", e);
		}

		return authToken;
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(authResult);

		getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
	}
}
