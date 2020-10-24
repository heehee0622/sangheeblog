package com.sanghee.test.sangheeblog.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sanghee.test.sangheeblog.model.BasicVo;
import com.sanghee.test.sangheeblog.model.DataInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class PageableResponse extends BasicVo {
	private Page<?> pageData;
	private DataInterface data;
}