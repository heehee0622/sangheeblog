package com.sanghee.test.sangheeblog.model.response;

import com.sanghee.test.sangheeblog.model.BasicVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HackerNewResponse extends BasicVo {
	private String by;
	private int id;
	private String type;
	private String title;
	private String url;
}
