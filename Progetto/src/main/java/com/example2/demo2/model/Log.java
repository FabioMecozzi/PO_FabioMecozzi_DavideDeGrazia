package com.example2.demo2.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

@Component
public class Log {

	private LinkedHashMap<LocalDate, Exception>exceptionLog;

	public void addException(Exception e) {
		exceptionLog.put(LocalDate.now(), e);
	}

}
