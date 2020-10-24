package com.sanghee.test.sangheeblog.controller;


import com.sanghee.test.sangheeblog.exception.BoardValidationException;
import com.sanghee.test.sangheeblog.model.request.BoardRequest;
import com.sanghee.test.sangheeblog.model.request.CommentRequest;
import com.sanghee.test.sangheeblog.model.request.ContentRequest;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.model.response.PageableResponse;
import com.sanghee.test.sangheeblog.service.ServiceRunnerExecutor;
import com.sanghee.test.sangheeblog.service.comment.CommentDisableServiceRunner;
import com.sanghee.test.sangheeblog.service.comment.CommentSaveServiceRunner;
import com.sanghee.test.sangheeblog.service.comment.CommentUpdateServiceRunner;
import com.sanghee.test.sangheeblog.service.content.*;
import com.sanghee.test.sangheeblog.type.StatusType;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

@RequestMapping("/v1/board")
@RestController
@Slf4j
public class BoardController {

	@Autowired
	private ServiceRunnerExecutor runnerExecutor;
	
	@GetMapping(value = "/{boardSeq}/{memberSeq}")
	public ModelAndView board(@PathVariable int boardSeq, @RequestParam String name, @PathVariable int memberSeq, @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) throws AddressException, MessagingException {
		log.info("User:"+memberSeq + " board contents get list request: board :"+ boardSeq);
		BoardRequest boardRequest = new BoardRequest();
		boardRequest.setName(name);
		boardRequest.setPageable(pageable);
		boardRequest.setMemberSeq(memberSeq);
		boardRequest.setSeq(boardSeq);
		ServiceRequest serviceRequest = ServiceRequest.builder().clz(ContentGetListServiceRunner.class).request(boardRequest).build();
		 ResponseData contentData = runnerExecutor.execute(serviceRequest);
		 PageableResponse pageResponse = (PageableResponse) contentData.getSingleData();
		 Page<?> contents = pageResponse.getPageData();
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("/board/board");
		 mav.addObject("contents", contents);
		 mav.addObject("boardName", name);
		 mav.addObject("boardSeq", boardSeq);
		 log.info("User:"+memberSeq + " board contents get list completed: board :"+ boardSeq);
		return mav;
	}
	
	@GetMapping(value = "{boardSeq}/content/save")
	public ModelAndView saveView(@PathVariable int boardSeq, @RequestParam String boardName ) throws AddressException, MessagingException {
		log.info(" board content wirte view request: board :"+ boardSeq); 
		ModelAndView mav = new ModelAndView();
		 mav.setViewName("/board/writeForm");
		 mav.addObject("boardSeq", boardSeq);
		 mav.addObject("boardName", boardName);
		 log.info(" board content wirte view complted: board :"+ boardSeq); 
		return mav;
	}
	
	@PostMapping(value = "/content")
	public ResponseData saveContent(@RequestBody @Valid ContentRequest contentRequest, BindingResult bindingResult ) throws AddressException, MessagingException {
		log.info("User:"+contentRequest.getMemberSeq()+ " board content save  request: board :"+ contentRequest.getBoardSeq()); 
		ServiceRequest serviceRequest = ServiceRequest.createServiceRequest(contentRequest, ContentSaveServiceRunner.class);
		int memberSeq = contentRequest.getMemberSeq();
		boolean hasErrors = bindingResult.hasErrors();
		if (hasErrors) {
			throw BoardValidationException.create(memberSeq, ResponseResult.BAD_REQUEST, bindingResult);
		}
		log.info("User:"+contentRequest.getMemberSeq()+ " board content save  completed: board :"+ contentRequest.getBoardSeq()); 
		return runnerExecutor.execute(serviceRequest);
	}
	
	@PostMapping(value = "/content/{contentSeq}/comment")
	public ResponseData saveComment(@RequestBody @Valid CommentRequest commentRequest, BindingResult bindingResult ) throws AddressException, MessagingException {
		log.info("User:"+commentRequest.getMemberSeq()+ " board comment save  request"); 
		ServiceRequest serviceRequest = ServiceRequest.createServiceRequest(commentRequest, CommentSaveServiceRunner.class);
		int memberSeq = commentRequest.getMemberSeq();
		boolean hasErrors = bindingResult.hasErrors();
		if (hasErrors) {
			throw BoardValidationException.create(memberSeq, ResponseResult.BAD_REQUEST, bindingResult);
		}
		log.info("User:"+commentRequest.getMemberSeq()+ " board comment save  completed"); 
		return runnerExecutor.execute(serviceRequest);
	}
	
	@GetMapping(value = "/{boardSeq}/content/{contentSeq}")
	public ModelAndView updateView(@PathVariable int contentSeq, @PathVariable int boardSeq, @RequestParam String boardName,@RequestParam int memberSeq ) throws AddressException, MessagingException {
		log.info(" board content update  view request: content:"+ contentSeq); 
		ContentRequest contentRequest= new ContentRequest();
		contentRequest.setMemberSeq(memberSeq);
		contentRequest.setContentSeq(contentSeq);
		ServiceRequest serviceRequest = ServiceRequest.createServiceRequest(contentRequest, ContentGetServiceRunner.class);
		ResponseData responseData = runnerExecutor.execute(serviceRequest);
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("/board/updateForm");
		 mav.addObject("content", responseData.getSingleData());
		 mav.addObject("boardSeq",boardSeq);
		 mav.addObject("boardName",boardName);
		 log.info(" board content update view completed: content:"+ contentSeq); 
		return mav;
	}
	
	@PutMapping(value = "/content/{content}")
	public ResponseData updateContent(@PathVariable int content, @RequestBody @Valid ContentRequest contentRequest, BindingResult bindingResult ) throws AddressException, MessagingException {
		log.info("User:"+ contentRequest.getMemberSeq() + " board content update  request: content:"+ content); 
		ServiceRequest serviceRequest = ServiceRequest.createServiceRequest(contentRequest, ContentUpdateServiceRunner.class);
		int memberSeq = contentRequest.getMemberSeq();
		boolean hasErrors = bindingResult.hasErrors();
		if (hasErrors) {
			throw BoardValidationException.create(memberSeq, ResponseResult.BAD_REQUEST, bindingResult);
		}
		log.info("User:"+ contentRequest.getMemberSeq() + " board content update  completed: content:"+ content); 
		return runnerExecutor.execute(serviceRequest);
	}
	
	@PutMapping(value = "/content/comment/{comment}")
	public ResponseData updateComment(@PathVariable int comment, @RequestBody @Valid CommentRequest commentRequest, BindingResult bindingResult ) throws AddressException, MessagingException {
		log.info("User:"+ commentRequest.getMemberSeq() + " board comment update  request: comment:"+ comment);
		ServiceRequest serviceRequest = ServiceRequest.createServiceRequest(commentRequest, CommentUpdateServiceRunner.class);
		int memberSeq = commentRequest.getMemberSeq();
		boolean hasErrors = bindingResult.hasErrors();
		if (hasErrors) {
			throw BoardValidationException.create(memberSeq, ResponseResult.BAD_REQUEST, bindingResult);
		}
		log.info("User:"+ commentRequest.getMemberSeq() + " board comment update  completed: comment:"+ comment);
		return runnerExecutor.execute(serviceRequest);
	}
	
	@DeleteMapping(value = "/content/{content}")
	public ResponseData deleteContent(@PathVariable int content, @RequestParam int memberSeq) throws AddressException, MessagingException {
		log.info("User:"+ memberSeq + " board content delete  request: content:"+ content);
		ContentRequest contentRequest = new ContentRequest();
		contentRequest.setContentSeq(content);
		contentRequest.setMemberSeq(memberSeq);
		contentRequest.setStatus(StatusType.HIDE.getName());
		ServiceRequest serviceRequest = ServiceRequest.createServiceRequest(contentRequest, ContentDisableServiceRunner.class);
		log.info("User:"+ memberSeq + " board content delete  completed: content:"+ content);
		return runnerExecutor.execute(serviceRequest);
	}
	
	@DeleteMapping(value = "/content/{content}/comment/{comment}")
	public ResponseData deleteComment(@PathVariable int content, @PathVariable int comment, @RequestParam int memberSeq) throws AddressException, MessagingException {
		log.info("User:"+ memberSeq + " board comment delete  request: content:"+ content);
		CommentRequest commentRequest = new CommentRequest();
		commentRequest.setContentSeq(content);
		commentRequest.setMemberSeq(memberSeq);
		commentRequest.setCommentSeq(comment);
		commentRequest.setStatus(StatusType.HIDE.getName());
		ServiceRequest serviceRequest = ServiceRequest.createServiceRequest(commentRequest, CommentDisableServiceRunner.class);
		log.info("User:"+ memberSeq + " board comment delete  request: content:"+ content);
		return runnerExecutor.execute(serviceRequest);
	}
	
	@GetMapping(value = "/{boardSeq}/content/{contentSeq}/detail")
	public ModelAndView defailView(@PathVariable int contentSeq, @PathVariable int boardSeq, @RequestParam String boardName,@RequestParam int memberSeq ) throws AddressException, MessagingException {
		log.info("User:"+ memberSeq + " board content detail view  request: content:"+ contentSeq);
		ContentRequest contentRequest= new ContentRequest();
		contentRequest.setMemberSeq(memberSeq);
		contentRequest.setContentSeq(contentSeq);
		ServiceRequest serviceRequest = ServiceRequest.createServiceRequest(contentRequest, ContentGetServiceRunner.class);
		ResponseData responseData = runnerExecutor.execute(serviceRequest);
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("/board/view");
		 mav.addObject("content", responseData.getSingleData());
		 mav.addObject("boardSeq",boardSeq);
		 mav.addObject("boardName",boardName);
		 log.info("User:"+ memberSeq + " board content detail view  completed: content:"+ contentSeq);
		return mav;
	}
	
}
