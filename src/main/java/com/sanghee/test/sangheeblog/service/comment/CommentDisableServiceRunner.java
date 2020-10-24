package com.sanghee.test.sangheeblog.service.comment;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.entity.Comments;
import com.sanghee.test.sangheeblog.model.request.CommentRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.repository.CommentRepository;
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
public class CommentDisableServiceRunner implements ServiceRunnerInterface<ResponseData> {

	private final CommentRepository commentRepository;

	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		CommentRequest commentRequest = (CommentRequest) request.getRequest();
		int memberSeq = commentRequest.getMemberSeq();
		int commentSeq = commentRequest.getCommentSeq();
		String status = commentRequest.getStatus();
		log.info("CommentDisableServiceRunner Start." + "member:"+memberSeq + " comment: " + commentSeq);
		try {
			boolean isExist = commentRepository.existsByCommentSeqAndWriter(commentSeq, memberSeq);
			if (!isExist) return ResponseData.FAIL("댓글이 존재 하지 않거나, 본인이 작성한 댓글이 아닙니다."); 
			Optional<Comments> optional = commentRepository.findById(commentSeq);
			Comments comment = optional.orElseThrow(() -> BoardBasicException.create(memberSeq, ResponseResult.FAIL, "게시글이 존재 하지 않습니다.ContentSaveServiceRunnerTest.java", null));
			comment.setStatus(status);
			log.info("CommentDisableServiceRunner End." + "member:"+memberSeq + " comment: " + commentSeq);
		} catch (Exception e) {
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "comment 삭제 실패 했습니다.", null);
		}
		return ResponseData.SUCCESS();
	}

}
