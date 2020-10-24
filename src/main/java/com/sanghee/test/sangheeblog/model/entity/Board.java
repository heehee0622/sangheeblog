package com.sanghee.test.sangheeblog.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Builder
public class Board {
	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardSeq;
	private String name;
	private String description;
	private String status;
	@OneToMany(mappedBy = "board")
	private List<Content> contentList ;
}
