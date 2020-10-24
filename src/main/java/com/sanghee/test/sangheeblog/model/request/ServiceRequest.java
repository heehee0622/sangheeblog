package com.sanghee.test.sangheeblog.model.request;

import com.sanghee.test.sangheeblog.model.BasicVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {
	@NonNull
	private BasicVo request;
	@NonNull
	private Class<?>  clz;
	
	public static ServiceRequest createServiceRequest(BasicVo request, Class<?> clz) {
		if (null == request)  request = new BasicVo();
		return ServiceRequest.builder().request(request).clz(clz).build();
	}
}
