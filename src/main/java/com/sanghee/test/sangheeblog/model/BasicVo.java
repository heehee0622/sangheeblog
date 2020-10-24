package com.sanghee.test.sangheeblog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor
@Getter
@Setter
public class BasicVo implements DataInterface {
	private int memberSeq;
	private Pageable pageable;
	@Override
	public int getMemberSeq() {
		return memberSeq;
	}
	
}
