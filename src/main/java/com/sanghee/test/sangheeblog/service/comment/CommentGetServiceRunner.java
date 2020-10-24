package com.sanghee.test.sangheeblog.service.comment;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.entity.Comments;
import com.sanghee.test.sangheeblog.model.request.CommentRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.model.response.CommentsResponse;
import com.sanghee.test.sangheeblog.repository.CommentRepository;
import com.sanghee.test.sangheeblog.service.ServiceRunnerInterface;
import com.sanghee.test.sangheeblog.type.StatusType;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentGetServiceRunner implements ServiceRunnerInterface<ResponseData> {

	private final CommentRepository commentRepository;
	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		CommentRequest boardRequest = (CommentRequest) request.getRequest();
		int memberSeq = boardRequest.getMemberSeq();
		int contentSeq = boardRequest.getContentSeq();
		log.info("CommentGetServiceRunner Start." + "member:"+memberSeq + " content: " + contentSeq);
		try {
			List<Comments> comments = commentRepository.findByContentSeqAndStatus(contentSeq, StatusType.SHOW.getName());
			List<CommentsResponse> responseList = comments.stream()
					.map(entitiy ->modelMapper.map(entitiy, CommentsResponse.class))
					.collect(Collectors.toList());
			log.info("CommentGetServiceRunner End." + "member:"+memberSeq + " content: " + contentSeq);
			return ResponseData.SUCCESS(responseList);
		} catch (Exception e) {
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "댓글 조회 실패 했습니다.", e);
		}
	}

}
