package com.sanghee.test.sangheeblog.service;

import com.sanghee.test.sangheeblog.model.request.ServiceRequest;

public interface ServiceRunnerInterface<T> {

	public T runService(ServiceRequest request);
}
