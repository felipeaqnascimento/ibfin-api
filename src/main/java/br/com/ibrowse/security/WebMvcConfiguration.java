package br.com.ibrowse.security;
//package br.gov.mg.uberlandia.eautorizaservice.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
//
//	@Autowired
//	private EAutorizaSessionInterceptor eAutorizaSessionInterceptor;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		super.addInterceptors(registry);
//		registry.addInterceptor(eAutorizaSessionInterceptor).addPathPatterns("/rest/**")
//				.excludePathPatterns("/rest/usuarios/cadastra","/login");
//
//	}
//}
