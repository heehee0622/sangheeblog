package com.sanghee.test.sangheeblog.util.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseValidationResult  {
private int code;
private int errorCount;
private String errorFields;
private String message;
}
