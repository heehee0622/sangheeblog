package com.sanghee.test.sangheeblog.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Builder
public class Comments extends BaseDateEntity {
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentSeq;
	@Column(name = "CONTENT_SEQ")
	private int contentSeq;
	private String comments;
	private int writer;
	private String status;
	@ManyToOne
	@JoinColumn(name="CONTENT_SEQ", updatable = false, insertable = false)
	private Content content;
	@ManyToOne
	@JoinColumn(name="WRITER", updatable = false, insertable = false)
	private Member member;
}
