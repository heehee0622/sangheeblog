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
public class BoardRequest extends BasicVo {
	@Min(0)
	private int seq;
	@NotBlank(message = "게시판 이름을 작성해 주세요.")
	@NotEmpty(message = "게시판 이름을 작성해 주세요.")
	@Length(min = 1, max = 20)
	private String name;
	@NotBlank(message = "게시판 설명을 작성해 주세요.")
	@NotEmpty(message = "게시판 설명을 작성해 주세요.")
	@Length(min = 1, max = 200)
	private String description;
	private String status;
}
