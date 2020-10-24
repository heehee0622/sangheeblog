package com.sanghee.test.sangheeblog.service.content;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.entity.Comments;
import com.sanghee.test.sangheeblog.model.entity.Content;
import com.sanghee.test.sangheeblog.model.request.ContentRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.model.response.CommentsResponse;
import com.sanghee.test.sangheeblog.model.response.ContentResponse;
import com.sanghee.test.sangheeblog.repository.ContentRepository;
import com.sanghee.test.sangheeblog.service.ServiceRunnerInterface;
import com.sanghee.test.sangheeblog.type.StatusType;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContentGetServiceRunner implements ServiceRunnerInterface<ResponseData> {
	@Autowired
	private final ContentRepository contentRepository;
	@Autowired
	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		ContentRequest contenRequest = (ContentRequest) request.getRequest();
		int memberSeq = contenRequest.getMemberSeq();
		int contentSeq = contenRequest.getContentSeq();
		log.info("ContentGetServiceRunner Start." + "member:"+memberSeq + " content:"+ contentSeq);
		try {
			Optional<Content> contentOptional = contentRepository.findById(contentSeq);
			Content content = contentOptional.orElseThrow(
					() -> BoardBasicException.create(memberSeq, ResponseResult.FAIL, "게시글 조회 실패 했습니다.", null));
			
			List<CommentsResponse> comments = filterByStatusY(content.getCommentList());
			 modelMapper.typeMap(Content.class, ContentResponse.class).addMappings(mapper -> {
			        mapper.skip(ContentResponse::setCommentList);
			    });
			ContentResponse contentResponse = modelMapper.map(content, ContentResponse.class);
			contentResponse.setCommentList(comments);
			log.info("ContentGetServiceRunner End." + "member:"+memberSeq + " content:"+ contentSeq);
			return ResponseData.SUCCESS(contentResponse);
		} catch (Exception e) {
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "게시글 조회 실패 했습니다.", e);
		}
	}

	private List<CommentsResponse> filterByStatusY(List<Comments> comments) {
		List<CommentsResponse> commentResponse = comments.stream()
				.map(p -> modelMapper.map(p, CommentsResponse.class))
				.filter(p -> p.getStatus().equals(StatusType.SHOW.getName())).collect(Collectors.toList());
		return commentResponse;
	}

}
