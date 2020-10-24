package com.sanghee.test.sangheeblog.service;

import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRunnerExecutor implements ServiceExecutorInterface<ResponseData> {

	private final ServiceRunnerFactory serviceRunnerFactory;

	@Autowired
	public ServiceRunnerExecutor(ServiceRunnerFactory serviceRunnerFactory) {
		this.serviceRunnerFactory = serviceRunnerFactory;
	}

	@Override
	public ResponseData execute(ServiceRequest request) {
		return  (ResponseData) serviceRunnerFactory.findRunner(request.getClz()).runService(request);
	}
}