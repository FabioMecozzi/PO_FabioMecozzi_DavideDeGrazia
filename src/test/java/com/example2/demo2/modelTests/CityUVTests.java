package com.example2.demo2.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example2.demo2.model.City;
import com.example2.demo2.model.CityUV;


class CityUVTests {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		CityUV citta = new CityUV(668737L, "Roma", "47.83328", "26.6");
		System.out.println(citta.toString());
		CityUV citta2 = new CityUV((City)citta);
		assertEquals("ID: 668737 nome: Roma Longitudine: 47.83328 Latitudine: 26.6 monitored: false goodForecast: 0 totalForecast: 0 accouracy: -1.0 record: null", "ID: 668737 nome: Roma Longitudine: 47.83328 Latitudine: 26.6 monitored: false goodForecast: 0 totalForecast: 0 accouracy: -1.0 record: null");
		
	}

}