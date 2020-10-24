package com.sanghee.test.sangheeblog.service.comment;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.entity.Comments;
import com.sanghee.test.sangheeblog.model.request.CommentRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.repository.CommentRepository;
import com.sanghee.test.sangheeblog.service.ServiceRunnerInterface;
import com.sanghee.test.sangheeblog.util.HtmlValueChecker;
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
public class CommentUpdateServiceRunner implements ServiceRunnerInterface<ResponseData> {

	private final CommentRepository commentRepository;
	private final HtmlValueChecker htmlChecker;

	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		CommentRequest commentRequest = (CommentRequest) request.getRequest();
		int memberSeq = commentRequest.getMemberSeq();
		int commentSeq = commentRequest.getCommentSeq();
		String content = commentRequest.getComment();
		log.info("CommentUpdateServiceRunner Start."+ "member:"+memberSeq + " comment:"+commentSeq );
		try {
			ResponseData checkResult = check(content);
			if (!checkResult.getCode().equals(ResponseResult.OK.getStrCode())) return checkResult;
			boolean isExist = commentRepository.existsByCommentSeqAndWriter(commentSeq, memberSeq);
			if (!isExist) return ResponseData.FAIL("댓글이 존재 하지 않거나, 본인이 작성한 댓글이 아닙니다."); 
			Optional<Comments> optional = commentRepository.findById(commentSeq);
			Comments comment = optional.orElseGet(() -> new Comments());
			comment.setComments(content);
			log.info("CommentUpdateServiceRunner End."+ "member:"+memberSeq + " comment:"+commentSeq );
		} catch (Exception e) {
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "comment 삭제 실패 했습니다.", null);
		}
		return ResponseData.SUCCESS();
	}
	
	private ResponseData check(String contents) throws AddressException, MessagingException  {
		if (htmlChecker.check(contents)) return ResponseData.FAIL("내용에 금지된 문자열 <script>가 포함 되어 있습니다.");
		return ResponseData.SUCCESS();
	}


}
