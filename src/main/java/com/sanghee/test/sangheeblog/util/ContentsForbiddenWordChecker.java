package com.sanghee.test.sangheeblog.util;


import com.sanghee.test.sangheeblog.util.properties.CommonProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

@Component
@RequiredArgsConstructor
public class ContentsForbiddenWordChecker {
	private final CommonProperties commonProperties;

	public boolean check(String content) throws AddressException, MessagingException {
		String pattern =commonProperties.getForbiddenWord();
		String[] arrays = pattern.split(",");
		for (String value : arrays) {
			boolean check = content.matches(".*"+value+".*");
			if (check) {
				return true;
			}
		}
		return false;
	}
}
