package com.sanghee.test.sangheeblog.util;


import com.sanghee.test.sangheeblog.util.properties.MailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@RequiredArgsConstructor
public class MailSender {
	private final MailProperties mailProperties;

	public void send(String title, String content, String toAddress) throws AddressException, MessagingException {
		final String username = mailProperties.getUsername();
		final String password = mailProperties.getPassword();
		Properties prop = createProperties();
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		Message message = message(title, content, toAddress, session);
		Transport.send(message);
	}

	private Message message(String title, String content, String toAdress, Session session)
			throws AddressException, MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailProperties.getUsername()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAdress));
		message.setSubject(title);
		message.setText(content);
		return message;
	}

	private Properties createProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", mailProperties.getHostname());
		prop.put("mail.smtp.port", mailProperties.getPort());
		prop.put("mail.smtp.auth", mailProperties.getSslyn().equals("Y") ? true : false);
		prop.put("mail.smtp.socketFactory.port", mailProperties.getPort());
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return prop;
	}
}
