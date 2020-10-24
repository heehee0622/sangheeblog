package com.sanghee.test.sangheeblog.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {
	private int seq;
	private String name;
	private String email;
	private String passwd;
	private String status;
	private String role;
}
