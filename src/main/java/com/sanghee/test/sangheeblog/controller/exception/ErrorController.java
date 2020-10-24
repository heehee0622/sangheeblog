package com.sanghee.test.sangheeblog.controller.exception;

import com.sanghee.test.sangheeblog.util.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ErrorController {

	@GetMapping("/v1/errors/{error}")
	public ResponseData error(@PathVariable int error) {
		switch (error) {
		case 400:
			log.info("error occured: " + error + "Page Not Found.");
			return ResponseData.FAIL(400,"Page Not Found.");
		case 404:
			log.info("error occured: " + error + "Bad Request.");
			return ResponseData.FAIL(404, "Bad Request.");
		case 500:
			log.info("error occured: " + error + "Inter Server Error.");
			return ResponseData.FAIL(500, "Inter Server Error.");
		default:
			log.info("error occured: " + error + "Inter Server Error.");
			return ResponseData.FAIL(500, "Inter Server Error.");
		}
	}
}
