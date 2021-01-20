package com.example2.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RepositoryLoader implements CommandLineRunner {
	@Autowired
	CityService service;

	@Override
	public void run(String... args) throws Exception {
		service.loadOpenWeatherCityList();
	}

}
