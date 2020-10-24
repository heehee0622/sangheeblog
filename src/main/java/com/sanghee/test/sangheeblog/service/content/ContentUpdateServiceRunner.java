package com.sanghee.test.sangheeblog.service.content;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.entity.Content;
import com.sanghee.test.sangheeblog.model.request.ContentRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.repository.ContentRepository;
import com.sanghee.test.sangheeblog.service.ServiceRunnerInterface;
import com.sanghee.test.sangheeblog.util.ContentsForbiddenWordChecker;
import com.sanghee.test.sangheeblog.util.HtmlValueChecker;
import com.sanghee.test.sangheeblog.util.HyperLinkChecker;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContentUpdateServiceRunner implements ServiceRunnerInterface<ResponseData> {

	private final ContentRepository contentRepository;
	private final HtmlValueChecker htmlChecker;
	private final ContentsForbiddenWordChecker contentsChecker;
	private final HyperLinkChecker hyperLinkcheker;
	
	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		ContentRequest contentRequest = (ContentRequest) request.getRequest();
		int memberSeq = contentRequest.getMemberSeq();
		int contenSeq = contentRequest.getContentSeq();
		String contents = contentRequest.getContent();
		String title = contentRequest.getTitle();
		log.info("ContentUpdateServiceRunner Start."+ "member:"+memberSeq + " content:"+contenSeq );
		try {
			ResponseData checkResult = check(contents, title);
			if (!checkResult.getCode().equals(ResponseResult.OK.getStrCode())) return checkResult;
			boolean isExist = contentRepository.existsByContentSeqAndWriter(contenSeq, memberSeq);
			if (!isExist) return ResponseData.FAIL("요청한 사용자가 작성한 켄텐츠가 아닙니다.");
			Optional<Content> contentOptional = contentRepository.findById(contenSeq);
			Content content = contentOptional.orElseThrow(
					() -> BoardBasicException.create(memberSeq, ResponseResult.FAIL, "켄텐츠가 존재 하지 않습니다.", null));
			content.updateContentAndTitle(contents, title);
			log.info("ContentUpdateServiceRunner End."+ "member:"+memberSeq + " content:"+contenSeq );
		} catch (Exception e) {
			if(e instanceof BoardBasicException) throw (BoardBasicException)e;
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "컨텐츠 삭제 실패 했습니다.", e);
		}
		return ResponseData.SUCCESS();
	}

	private ResponseData check(String contents, String title) throws AddressException, MessagingException {
		if (htmlChecker.check(contents)) return ResponseData.FAIL("내용에 금지된 문자열 <script>가 포함 되어 있습니다.");
		if (htmlChecker.check(title)) return ResponseData.FAIL("제목에 금지된 문자열 <script>가 포함 되어 있습니다.");
		if (contentsChecker.check(contents)) return ResponseData.FAIL("내용에 금지된 단어(도박,코로나,자금,계좌,부동산)가 포함 되어 있습니다.");
		if (contentsChecker.check(title)) return ResponseData.FAIL("제목에 금지된 단어(도박,코로나,자금,계좌,부동산)가 포함 되어 있습니다.");
		checkHyperLink(contents);
		return ResponseData.SUCCESS();
	}
	
	private void checkHyperLink(String value) throws BoardBasicException{
		hyperLinkcheker.check(value);
	}
}
