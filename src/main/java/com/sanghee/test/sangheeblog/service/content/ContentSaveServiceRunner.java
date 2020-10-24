package com.sanghee.test.sangheeblog.service.content;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.entity.Content;
import com.sanghee.test.sangheeblog.model.request.ContentRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.repository.ContentRepository;
import com.sanghee.test.sangheeblog.service.ServiceRunnerInterface;
import com.sanghee.test.sangheeblog.type.StatusType;
import com.sanghee.test.sangheeblog.util.ContentsForbiddenWordChecker;
import com.sanghee.test.sangheeblog.util.HtmlValueChecker;
import com.sanghee.test.sangheeblog.util.HyperLinkChecker;
import com.sanghee.test.sangheeblog.util.TimeUtil;
import com.sanghee.test.sangheeblog.util.properties.CommonProperties;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContentSaveServiceRunner implements ServiceRunnerInterface<ResponseData> {

	private final ContentRepository contentRepository;
	private final CommonProperties commonProperties;
	private final HtmlValueChecker htmlChecker;
	private final ContentsForbiddenWordChecker contentsChecker;
	private final HyperLinkChecker hyperLinkcheker;

	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		ContentRequest contentRequest = (ContentRequest) request.getRequest();
		int memberSeq = contentRequest.getMemberSeq();
		int boardSeq = contentRequest.getBoardSeq();
		String title = contentRequest.getTitle();
		String contents = contentRequest.getContent();
		LocalDate localDate = LocalDate.now();
		Date start = TimeUtil.convertFromDateTimeToDate(LocalDateTime.of(localDate, LocalTime.of(0, 0, 0)));
		Date end = TimeUtil.convertFromDateTimeToDate(LocalDateTime.of(localDate, LocalTime.of(23, 59, 59)));
		log.info("ContentSaveServiceRunner Start." + "member:"+memberSeq+ "board:" + boardSeq);
		try {
			ResponseData checkResult = check(contents, title);
			if (!checkResult.getCode().equals(ResponseResult.OK.getStrCode())) return checkResult;
			int countByToday = contentRepository.countByWriterAndCreateDateBetween(memberSeq, start, end);
			if (countByToday >= commonProperties.getWriteLimitByDay()) return ResponseData.FAIL("컨텐츠 등록은 하루 5개를 초과 할 수 없습니다.");
			Content content = Content.builder().content(contents).title(title).boardSeq(boardSeq).writer(memberSeq).status(StatusType.SHOW.getName()).build();
			contentRepository.save(content);
			log.info("ContentSaveServiceRunner End." + "member:"+memberSeq+ "board:" + boardSeq);
		} catch (Exception e) {
			if(e instanceof BoardBasicException) throw (BoardBasicException)e;
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "컨텐츠 저장 실패 했습니다.", null);
		}
		return ResponseData.SUCCESS();
	}

	private ResponseData check(String contents, String title) throws AddressException, MessagingException, BoardBasicException {
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
