package com.sanghee.test.sangheeblog.exception;

public interface BoardExceptionInterface<T, V> {

	public T handleException(Exception ex)  throws BoardBasicException;
	
	 void handleLog(V ex);
}
