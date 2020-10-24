package com.sanghee.test.sangheeblog.service.content;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.entity.Content;
import com.sanghee.test.sangheeblog.model.request.ContentRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.repository.ContentRepository;
import com.sanghee.test.sangheeblog.service.ServiceRunnerInterface;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContentDisableServiceRunner implements ServiceRunnerInterface<ResponseData> {

	private final ContentRepository contentRepository;

	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		ContentRequest contentRequest = (ContentRequest) request.getRequest();
		int memberSeq = contentRequest.getMemberSeq();
		int contenSeq = contentRequest.getContentSeq();
		String status = contentRequest.getStatus();
		log.info("ContentDisableServiceRunner Start." + "member:"+memberSeq + " comment: " + contenSeq);
		try {
			Optional<Content> contentOptional = contentRepository.findById(contenSeq);
			boolean isExist = contentRepository.existsByContentSeqAndWriter(contenSeq, memberSeq);
			if (!isExist) return ResponseData.FAIL("요청한 사용자가 작성한 켄텐츠가 아닙니다.");
			Content content = contentOptional.orElseThrow(
					() -> BoardBasicException.create(memberSeq, ResponseResult.FAIL, "켄텐츠가 존재 하지 않습니다.", null));
			content.setStatus(status);
			log.info("ContentDisableServiceRunner End." + "member:"+memberSeq + " comment: " + contenSeq);
		} catch (Exception e) {
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "컨텐츠 삭제 실패 했습니다.", e);
		}
		return ResponseData.SUCCESS();
	}

}
