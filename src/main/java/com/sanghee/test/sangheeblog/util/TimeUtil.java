package com.sanghee.test.sangheeblog.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeUtil {

	public static  Date convertFromDateTimeToDate(LocalDateTime dateTime) {
		return Date.from(
				dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
