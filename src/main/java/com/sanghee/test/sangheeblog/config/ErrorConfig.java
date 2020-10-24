package com.sanghee.test.sangheeblog.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorConfig {
	 @Bean
	    public ConfigurableServletWebServerFactory webServerFactory() {
	        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
	        factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/v1/errors/400"));
	        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/v1/errors/404"));
	        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/v1/errors/500"));
	        return factory;
	    }
}

