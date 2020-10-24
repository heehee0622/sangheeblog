package com.sanghee.test.sangheeblog.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "common")
@Component
public class CommonProperties {
	private String forbiddenWord;
	private String forbiddenScript;
	private int writeLimitByDay;
}