package com.sanghee.test.sangheeblog.util.valdate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultValidator {
	private int errorCount;
	private String message;
	private String fields;
}
