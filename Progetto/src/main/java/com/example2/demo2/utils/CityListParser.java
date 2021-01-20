package com.example2.demo2.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import com.example2.demo2.model.City;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class CityListParser { 

	@SuppressWarnings({ "unchecked", "null" })
	public static List<City> parse(File source) {
		
		
		@SuppressWarnings("unused")
		JSONObject jsonObject;
		
		
		City city = null ;
		

		@SuppressWarnings("deprecation")
		JSONParser parser = new JSONParser();
		
		 
		Object obj = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader(source));
		} catch (FileNotFoundException | ParseException e) {
		
			e.printStackTrace();
		}
		
		city.setName((String) ((HashMap<String,Object>) obj).get("name"));
		city.setCountry((String) ((HashMap<String,Object>) obj).get("country"));
		JSONObject coord = (JSONObject) ((HashMap<String,Object>) obj).get("coord");
		city.setLat((String) coord.get("lat"));
		city.setLon((String) coord.get("lon"));

		
		
		LinkedHashSet<City> set = new LinkedHashSet<City>();
		Iterator<City> it = set.iterator();
		
		while (it.hasNext()) {
			set.add(city);
			it.next();
			
		}
		
		return (List<City>) set;
	}



	//Da implementare?
	public List<City> parseIT(File source) {
		return null;
	}

	public List<City> parse(File source) {
		return null;
	}
}
