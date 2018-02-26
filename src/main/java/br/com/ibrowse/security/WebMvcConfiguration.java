package br.com.ibrowse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private FinanceiroSessionInterceptor financeiroSessionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(financeiroSessionInterceptor)
				.addPathPatterns("/rest/**")/* .excludePathPatterns("/login") */;

	}
}
