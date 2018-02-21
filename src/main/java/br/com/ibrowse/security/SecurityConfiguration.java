package br.com.ibrowse.security;
//package br.gov.mg.uberlandia.eautorizaservice.security;
//
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
////@Configuration
////@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	
////	@Autowired
////	UsuariosJpaRepository usuariosJpaRepository;
////
////	@Autowired
////	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
////		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
////		auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
////	}
////	
////	protected void configure(HttpSecurity http) throws Exception {
////
////		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().and().httpBasic();
////
////		//http.addFilterBefore(getAuthenticationTokenProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
////	}
////
////	public Filter getAuthenticationTokenProcessingFilter() {
////		RequestMatcher matcher = new AntPathRequestMatcher("/rest/**");
////		AuthenticationTokenProcessingFilter filter = new AuthenticationTokenProcessingFilter(matcher);
////		filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler());
////		filter.setUsuariosJpaRepository(usuariosJpaRepository);
////		return filter;
////	}
////
////	@Override
////	public void configure(WebSecurity web) throws Exception {
////		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
////	}
//
//}
