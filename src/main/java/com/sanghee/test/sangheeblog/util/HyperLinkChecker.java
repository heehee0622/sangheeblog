package com.sanghee.test.sangheeblog.util;


import com.sanghee.test.sangheeblog.exception.BoardBasicException;
import com.sanghee.test.sangheeblog.util.response.ResponseResult;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HyperLinkChecker {

	private Pattern linkPattern;
	private Pattern hrefPattern;
	private Pattern urlPattern;

	private static final String A_LINK_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
	private static final String A_HREF_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
	private static final String URL_PATTERN = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
	

	public HyperLinkChecker() {
		linkPattern = Pattern.compile(A_LINK_PATTERN);
		hrefPattern = Pattern.compile(A_HREF_PATTERN);
		urlPattern = Pattern.compile(URL_PATTERN);
	}

	public void check(final String html) {
		Matcher tagMatcher = linkPattern.matcher(html);
		while (tagMatcher.find()) {
			String href = tagMatcher.group(1);
			Matcher linkMatcher = hrefPattern.matcher(href);
			while (linkMatcher.find()) {
				String url = linkMatcher.group(1);
				Matcher m = urlPattern.matcher(url.toString());
				boolean check = m.find();
				if (check) {
					boolean checkExist = false;
					String urlName = linkMatcher.group(1);
					checkExist = urlName.matches("(?i).*naver.*");
					if (checkExist)
						throw BoardBasicException.create(-1, ResponseResult.FAIL, "네이버 링크는 포함 될수 없습니다.", null);
					checkExist = urlName.matches("(?i).*daum.*");
					if (checkExist)
						throw BoardBasicException.create(-1, ResponseResult.FAIL, "다음 링크는 포함 될수 없습니다.", null);
				}
			}
		}
	}
}