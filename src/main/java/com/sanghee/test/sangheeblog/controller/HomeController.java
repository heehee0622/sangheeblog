package com.sanghee.test.sangheeblog.controller;


import com.sanghee.test.sangheeblog.model.BasicVo;
import com.sanghee.test.sangheeblog.model.DataInterface;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.service.HackerNewsService;
import com.sanghee.test.sangheeblog.service.ServiceRunnerExecutor;
import com.sanghee.test.sangheeblog.service.board.BoardGetServiceRunner;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.List;

@RestController
@Slf4j
public class HomeController {
	@Autowired
	private ServiceRunnerExecutor runnerExecutor;

	@GetMapping({ "", "/" })
	public ModelAndView index() throws AddressException, MessagingException {
		log.info("This is User Blog Page Request");
		ServiceRequest boardRequest = ServiceRequest.builder().clz(BoardGetServiceRunner.class).request(new BasicVo())
				.build();
		ServiceRequest hackerRequest = ServiceRequest.builder().clz(HackerNewsService.class).request(new BasicVo())
				.build();
		ResponseData boardData = runnerExecutor.execute(boardRequest);
		ResponseData hackerData = runnerExecutor.execute(hackerRequest);
		List<? extends DataInterface> boards = boardData.getData();
		List<? extends DataInterface> hackers = hackerData.getData();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("boards", boards);
		mav.addObject("hackers", hackers);
		log.info("This is User Blog Page Completed");
		return mav;
	}

}
