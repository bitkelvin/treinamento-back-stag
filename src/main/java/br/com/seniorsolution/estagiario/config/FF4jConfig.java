package br.com.seniorsolution.estagiario.config;

import org.ff4j.FF4j;
import org.ff4j.web.FF4jDispatcherServlet;
import org.ff4j.web.FF4jProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.seniorsolution.estagiario.helper.FF4JHelper;

@Configuration
@ComponentScan(basePackages = "org.ff4j.aop")
public class FF4jConfig implements FF4jProvider {

	@Autowired
	private FF4jDispatcherServlet ff4jServlet;

	@Bean
	public ServletRegistrationBean newFf4jServletRegistrationBean() {
		return new ServletRegistrationBean(ff4jServlet, "/secure/ff4j/*");
	}

	@Bean
	public FF4j getFF4j() {
		return new FF4j().createFeature(
				FF4JHelper.FEATURE_UID_F1, false, "Habilita a aplicaçao para usar mock no serviço de estagiário");
	}

	@Bean
	public FF4jDispatcherServlet getFF4jDispatcherServlet() {
		FF4jDispatcherServlet servlet = new FF4jDispatcherServlet();
		servlet.setFf4j(getFF4j());
		return servlet;
	}
}
