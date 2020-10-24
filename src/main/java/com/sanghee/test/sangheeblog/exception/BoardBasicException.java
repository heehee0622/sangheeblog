package com.sanghee.test.sangheeblog.exception;

import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import lombok.Getter;

@Getter
public class BoardBasicException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3152133632517887419L;
	private ResponseResult code;
	private int memberSeq;

	public  BoardBasicException(int memberSeq, ResponseResult code, String message) {
		super(message);
		this.code = code;
		this.memberSeq = memberSeq;
	}

	public  BoardBasicException(int memberSeq, ResponseResult code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.memberSeq = memberSeq;
	}

	 public static  BoardBasicException create(int memberSeq, ResponseResult code, String message, Throwable cause) {
		 if (null != cause ) 
			return new BoardBasicException(memberSeq, code, message, cause);
		 else
			 return new BoardBasicException(memberSeq, code, message);
	 }



}
