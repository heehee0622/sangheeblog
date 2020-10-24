package com.sanghee.test.sangheeblog.service.board;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.model.BasicVo;
import com.sanghee.test.sangheeblog.model.entity.Board;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.model.response.BoardResponse;
import com.sanghee.test.sangheeblog.repository.BoardRepository;
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
public class BoardGetServiceRunner implements ServiceRunnerInterface<ResponseData> {

	private final BoardRepository boardRepository;
	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseData runService(ServiceRequest request) {
		BasicVo boardRequest = request.getRequest();
		int memberSeq = boardRequest.getMemberSeq();
		log.info("BoardGetServiceRunner Start." + "member:"+memberSeq);
		try {
			List<Board> boardList = boardRepository.findAllByStatusOrderByBoardSeqDesc(StatusType.SHOW.getName());
			List<BoardResponse> responseList = boardList.stream()
			.map(entitiy ->modelMapper.map(entitiy, BoardResponse.class))
			.collect(Collectors.toList());
			log.info("BoardGetServiceRunner End." + "member:"+memberSeq);
			return ResponseData.SUCCESS(responseList);
		} catch (Exception e) {
			throw BoardBasicException.create(memberSeq, ResponseResult.FAIL, "게시판 조회 실패 했습니다.", e);
		}
	}

}
