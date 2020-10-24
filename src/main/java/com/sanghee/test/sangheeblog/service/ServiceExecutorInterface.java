package com.sanghee.test.sangheeblog.service;


import com.sanghee.test.sangheeblog.model.request.ServiceRequest;

public interface ServiceExecutorInterface<T> {
	public T execute(ServiceRequest request);
}
