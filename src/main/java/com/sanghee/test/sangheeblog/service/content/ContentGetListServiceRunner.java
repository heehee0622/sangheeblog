package com.sanghee.test.sangheeblog.service.content;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.entity.Content;
import com.sanghee.test.sangheeblog.model.request.BoardRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.model.response.ContentResponse;
import com.sanghee.test.sangheeblog.model.response.PageableResponse;
import com.sanghee.test.sangheeblog.repository.ContentRepository;
import com.sanghee.test.sangheeblog.service.ServiceRunnerInterface;
import com.sanghee.test.sangheeblog.type.StatusType;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContentGetListServiceRunner implements ServiceRunnerInterface<ResponseData> {
	@Autowired
	private final ContentRepository commentRepository;
	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		BoardRequest boardRequest = (BoardRequest) request.getRequest();
		int memberSeq = boardRequest.getMemberSeq();
		int boardSeq = boardRequest.getSeq();
		Pageable pageable = boardRequest.getPageable();
		log.info("ContentGetListServiceRunner Start." + "member:"+memberSeq + " board: " + boardSeq);
		try {
			Page<Content> boardList = commentRepository.findAllByBoardSeqAndStatus(boardSeq, StatusType.SHOW.getName(),
					pageable);
			Page<ContentResponse> responseList = boardList.map(value -> new ContentResponse(value, modelMapper));
			PageableResponse pageResponse= new PageableResponse();
			pageResponse.setPageData(responseList);
			log.info("ContentGetListServiceRunner End." + "member:"+memberSeq + " board: " + boardSeq);
			return ResponseData.SUCCESS(pageResponse);
		} catch (Exception e) {
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "게시글 조회 실패 했습니다.", e);
		}
	}

}
