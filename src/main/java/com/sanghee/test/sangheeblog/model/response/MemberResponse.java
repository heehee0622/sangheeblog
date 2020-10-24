package com.sanghee.test.sangheeblog.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
	private int seq;
	private String name;
	private String email;
	private String status;
	private String role;
	private int rank;
}
