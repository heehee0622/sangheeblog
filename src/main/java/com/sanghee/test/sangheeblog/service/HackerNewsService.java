package com.sanghee.test.sangheeblog.service;


import com.sanghee.test.sangheeblog.model.BasicVo;
import com.sanghee.test.sangheeblog.model.request.ServiceRequest;
import com.sanghee.test.sangheeblog.model.response.HackerNewResponse;
import com.sanghee.test.sangheeblog.util.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HackerNewsService implements ServiceRunnerInterface<ResponseData> {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseData runService(ServiceRequest request) {
		BasicVo requestData = request.getRequest();
		int memberSeq = requestData.getMemberSeq();
		log.info("HackerNewsService Start." + " member: "+memberSeq);
		List<HackerNewResponse> responseList = new ArrayList<HackerNewResponse>();
		try {
			List<Integer> list = restTemplate
					.getForObject("https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty", List.class);
			List<Integer> sublist = list.subList(0, list.size() < 10 ? list.size() : 10);
			log.info("new story total count : " + list.size());
			for (Integer id : sublist) {
				String url = "https://hacker-news.firebaseio.com/v0/item/" + id + ".json?print=pretty";
				HackerNewResponse news = restTemplate.getForObject(url, HackerNewResponse.class);
				responseList.add(news);
			}
			log.info("HackerNewsService End." + " member: "+memberSeq);
			return ResponseData.SUCCESS(responseList);
		} catch (Exception e) {
			return ResponseData.SUCCESS(responseList);
		}
	}

}
