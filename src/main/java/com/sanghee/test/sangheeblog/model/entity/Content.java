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
public class Content extends BaseDateEntity{
	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contentSeq;
	@Column(name = "BOARD_SEQ")
	private int boardSeq;
	private String content;
	private String title;
	private int writer;
	private String status;
	@OneToMany(mappedBy = "content")
	private List<Comments> commentList ;
	@ManyToOne
	@JoinColumn(name="WRITER", updatable = false, insertable = false)
	public Member member;
	@ManyToOne
	@JoinColumn(name="BOARD_SEQ", updatable = false, insertable = false)
	public Board board;
	
	public void updateContentAndTitle(String content, String title) {
		this.title = title;
		this.content = content;
	}
}
