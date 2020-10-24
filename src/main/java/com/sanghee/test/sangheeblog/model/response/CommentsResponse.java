package com.sanghee.test.sangheeblog.model.response;

import com.sanghee.test.sangheeblog.model.BasicVo;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsResponse extends BasicVo {
	private int commentSeq;
	private int contentSeq;
	private String comments;
	private int writer;
	private String status;
	private Date createDate;
	private Date updateDate;
	private MemberResponse member;
}
