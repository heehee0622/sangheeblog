package com.sanghee.test.sangheeblog.service.comment;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.entity.Comments;
import com.sanghee.test.sangheeblog.model.entity.Content;
import com.sanghee.test.sangheeblog.model.entity.Member;
import com.sanghee.test.sangheeblog.model.request.CommentRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.repository.CommentRepository;
import com.sanghee.test.sangheeblog.repository.ContentRepository;
import com.sanghee.test.sangheeblog.service.ServiceRunnerInterface;
import com.sanghee.test.sangheeblog.type.StatusType;
import com.sanghee.test.sangheeblog.util.HtmlValueChecker;
import com.sanghee.test.sangheeblog.util.MailSender;
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
public class CommentSaveServiceRunner implements ServiceRunnerInterface<ResponseData> {

	private final CommentRepository commentRepository;
	private final ContentRepository contentRepository;
	private final MailSender mailSender;
	private final HtmlValueChecker htmlChecker;

	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		CommentRequest commentRequest = (CommentRequest) request.getRequest();
		int memberSeq = commentRequest.getMemberSeq();
		int contentSeq = commentRequest.getContentSeq();
		String contents = commentRequest.getComment();
		log.info("CommentSaveServiceRunner Start." + "member:"+memberSeq );
		try {
			ResponseData checkResult = check(contents);
			if (!checkResult.getCode().equals(ResponseResult.OK.getStrCode())) return checkResult;
			Optional<Content>  optional = contentRepository.findById(contentSeq);
			Content content = optional.orElseThrow(() -> BoardBasicException.create(memberSeq, ResponseResult.FAIL, "게시글이 존재 하지 않습니다.", null));
			Member member = content.getMember();
			Comments comments=Comments.builder().comments(contents).contentSeq(contentSeq).writer(memberSeq).status(StatusType.SHOW.getName()).build();
			commentRepository.save(comments);
			String title = content.getTitle();
			String email = member.getEmail();
			mailSender.send("<게시판 서비스>"+ title + "에 댓글이 등록되었습니다.", "댓글 내용 입니다. \r\n" + contents +"\r\n"+"감사합니다.", email );
			log.info("CommentSaveServiceRunner End." + "member:"+memberSeq );
		} catch (Exception e) {
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "comment 저장 실패 했습니다.", null);
		}
		return ResponseData.SUCCESS();
	}
	
	private ResponseData check(String contents) throws AddressException, MessagingException  {
		log.info("contents: " + contents);
		if (htmlChecker.check(contents)) return ResponseData.FAIL("내용에 금지된 문자열 <script>가 포함 되어 있습니다.");
		return ResponseData.SUCCESS();
	}

}
