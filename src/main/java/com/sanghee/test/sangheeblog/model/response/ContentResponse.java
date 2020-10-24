package com.sanghee.test.sangheeblog.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sanghee.test.sangheeblog.model.BasicVo;
import com.sanghee.test.sangheeblog.model.entity.Content;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_EMPTY)
public class ContentResponse extends BasicVo {
	private int contentSeq;
	private int boardSeq;
	private String content;
	private String title;
	private int writer;
	private String status;
	private Date createDate;
	private Date updateDate;
	private String boardName;
	private List<CommentsResponse> commentList;
	private MemberResponse member;
	
	public ContentResponse (Content content, ModelMapper modelMapper) {
		this.contentSeq = content.getContentSeq();
		this.boardSeq = content.getBoardSeq();
		this.content = content.getContent();
		this.title = content.getTitle();
		this.writer = content.getWriter();
		this.status = content.getStatus();
		this.createDate = content.getCreateDate();
		this.updateDate = content.getUpdateDate();
		this.boardName = content.getBoard().getName();
		this.member = modelMapper.map(content.getMember(), MemberResponse.class);
	}
}
