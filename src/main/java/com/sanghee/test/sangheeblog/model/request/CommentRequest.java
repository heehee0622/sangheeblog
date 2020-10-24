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
public class CommentRequest extends BasicVo {
	@Min(0)
	private int contentSeq;
	@Min(0)
	private int commentSeq;
	@NotBlank(message = "댓글을 작성해 주세요.")
	@NotEmpty(message = "댓글을 작성해 주세요.")
	@Length(min = 1, max = 100)
	private String comment;
	private String status;
}
