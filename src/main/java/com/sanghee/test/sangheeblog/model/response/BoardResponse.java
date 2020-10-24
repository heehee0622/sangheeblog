package com.sanghee.test.sangheeblog.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sanghee.test.sangheeblog.model.BasicVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class BoardResponse extends BasicVo {
	private int boardSeq;
	private String name;
	private String description;
	private String status;
}