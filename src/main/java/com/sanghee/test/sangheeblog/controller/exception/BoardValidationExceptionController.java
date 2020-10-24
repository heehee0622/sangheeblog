package com.sanghee.test.sangheeblog.controller.exception;

import com.sanghee.test.sangheeblog.exception.BoardExceptionInterface;
import com.sanghee.test.sangheeblog.exception.BoardValidationException;
import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import com.sanghee.test.sangheeblog.util.response.ResponseValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

;

@Slf4j
@RestControllerAdvice
public class BoardValidationExceptionController
		implements BoardExceptionInterface<ResponseValidationResult, BoardValidationException> {
	@ExceptionHandler(value = { BoardValidationException.class })
	@Override
	public ResponseValidationResult handleException(Exception ex) throws BoardValidationException {
		BoardValidationException bve = (BoardValidationException) ex;
		ResponseValidationResult validationResult = ResponseValidationResult.builder()
				.code(ResponseResult.FAIL.getCode()).errorCount(bve.getErrorCount()).errorFields(bve.getFields())
				.message(bve.getMessage()).build();
		handleLog(bve);
		return validationResult;
	}

	@Override
	public void handleLog(BoardValidationException ex) {
		String className = ex.getStackTrace()[0].getClassName();
		String methodName = ex.getStackTrace()[0].getMethodName();
		int line = ex.getStackTrace()[0].getLineNumber();
		int memberSeq = ex.getMemberSeq();
		String message = ex.getMessage();
		Throwable cause = ex.getCause();
		log.error("[USERSEQ:" + memberSeq + " " + className + ":" + methodName + ":" + line + ": " + message + "]");
		if (null != cause)
			log.error("[USERSEQ:" + memberSeq + " CAUSE : ", cause);
	}
}