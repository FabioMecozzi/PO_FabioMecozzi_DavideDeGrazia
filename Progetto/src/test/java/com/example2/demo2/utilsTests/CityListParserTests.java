package com.example2.demo2.utilsTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example2.demo2.model.City;
import com.example2.demo2.model.CityWithCountry;
import com.example2.demo2.utils.CityListParser;

class CityListParserTests {
	String path = System.getProperty("user.dir")+"\\src\\main\\resources\\city.list.json";
	File file= new File(path);
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		ArrayList<CityWithCountry> list= CityListParser.parse(file);
		assertTrue(list.size()>100);
	}
	
	@Test
	void test2() {
		HashSet<City> collection= CityListParser.parseIT(file);
		assertTrue(collection.size()>100);
	}

}
