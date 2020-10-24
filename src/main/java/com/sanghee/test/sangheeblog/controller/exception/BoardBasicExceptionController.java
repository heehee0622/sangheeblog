package com.sanghee.test.sangheeblog.controller.exception;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.exception.BoardExceptionInterface;
import com.sanghee.test.sangheeblog.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BoardBasicExceptionController implements BoardExceptionInterface<String, BoardBasicException> {
	@ExceptionHandler(value = { BoardBasicException.class})
	@Override
	public String handleException(Exception ex) throws BoardBasicException {
		BoardBasicException wepe = (BoardBasicException) ex;
		handleLog(wepe);
		return Response.FAIL(wepe.getCode().getCode(), wepe.getMessage());
	}

	@Override
	public void handleLog(BoardBasicException ex) {
		String className = ex.getStackTrace()[0].getClassName();
		String methodName = ex.getStackTrace()[0].getMethodName();
		int line = ex.getStackTrace()[0].getLineNumber();
		int memberSeq = ex.getMemberSeq();
		String message = ex.getMessage();
		Throwable cause = ex.getCause();
		log.error("[USERSEQ:"+memberSeq +" "+className+":" + methodName+":"+line+ ": " + message + "]");
		if (null != cause) 
			log.error("[USERSEQ:"+ memberSeq +" CAUSE : ", cause);
	}
}