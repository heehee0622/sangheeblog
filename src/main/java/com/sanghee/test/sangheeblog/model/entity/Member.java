package com.sanghee.test.sangheeblog.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	private String name;
	private String email;
	private String passwd;
	private String status;
	@OneToMany(mappedBy = "member")
	private List<Content> contentList ;
	private String role;
	private int rank;
}
