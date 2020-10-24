package com.sanghee.test.sangheeblog.model.request;


import com.sanghee.test.sangheeblog.model.BasicVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class ContentRequest extends BasicVo {
	@Min(0)
	private int boardSeq;
	@Min(0)
	private int contentSeq;
	@NotBlank(message = "게시글 제목을 작성해 주세요.")
	@NotEmpty(message = "게시글 제목을 작성해 주세요.")
	@Length(min = 1, max = 20)
	private String title;
	@NotBlank(message = "게시글을 작성해 주세요.")
	@NotEmpty(message = "게시글을 작성해 주세요.")
	@Length(min = 1, max = 300)
	private String content;
	private String status;
}
