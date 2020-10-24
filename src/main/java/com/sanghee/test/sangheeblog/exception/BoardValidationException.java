package com.sanghee.test.sangheeblog.exception;


import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import com.sanghee.test.sangheeblog.util.valdate.RequestValidatior;
import com.sanghee.test.sangheeblog.util.valdate.ResultValidator;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class BoardValidationException extends BoardBasicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3928153520175911400L;
	private int errorCount;
	private String fields;
	public BoardValidationException(int memberSeq, ResponseResult code, ResultValidator result) {
		super(memberSeq, code, result.getMessage());
		this.errorCount = result.getErrorCount();
		this.fields = result.getFields();
	}

	public static BoardValidationException create(int memberSeq, ResponseResult code, BindingResult errorResult) {
		RequestValidatior valdate = new RequestValidatior();
		ResultValidator result = valdate.validate(errorResult);
		return new BoardValidationException(memberSeq, code, result);
	}

}
