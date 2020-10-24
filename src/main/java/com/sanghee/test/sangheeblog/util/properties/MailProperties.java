package com.sanghee.test.sangheeblog.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "mail")
@Component
public class MailProperties {
	private String hostname;
	private String port;
	private String username;
	private String password;
	private String sslyn;
	private String timeout;
	private String contentType;
}