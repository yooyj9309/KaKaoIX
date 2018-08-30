package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.example.demo.exception.ServerErrorException;

public class TimeUtil {

	private static final int END_STATUS = 0;

	public static Date getTime(int period){
		String currentTime = LocalDateTime.now().plusDays(period).withHour(0).withMinute(0).withSecond(0)
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(currentTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new ServerErrorException("서버 에러 입니다.");
		}
		return date;
	}

	public static int getPeriod(Date endTime) {
		LocalDate currentTime = LocalDate.now();

		Instant instant = endTime.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate endDateTime = zdt.toLocalDate();

		if (currentTime.isAfter(endDateTime)) {
			return END_STATUS;
		} else {
			Period period = Period.between(currentTime, endDateTime);
			return period.getDays();
		}
	}
}
