package br.com.ibrowse.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import br.com.ibrowse.data.repository.jpa.UsuariosJpaRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UsuariosJpaRepository usuariosJpaRepository;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
	}

	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().and().httpBasic();

		// http.addFilterBefore(getAuthenticationTokenProcessingFilter(),
		// UsernamePasswordAuthenticationFilter.class);
	}

	public Filter getAuthenticationTokenProcessingFilter() {
		RequestMatcher matcher = new AntPathRequestMatcher("/rest/**");
		AuthenticationTokenProcessingFilter filter = new AuthenticationTokenProcessingFilter(matcher);
		filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler());
		filter.setUsuariosJpaRepository(usuariosJpaRepository);
		return filter;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

}
