package com.example2.demo2.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example2.demo2.model.CityWithCountry;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class CityListParser { 

	
	public static List<CityWithCountry> parse(File source) {
		
	   ArrayList<CityWithCountry> cities = new ArrayList<CityWithCountry>();
		
		@SuppressWarnings("deprecation")
		JSONParser parser = new JSONParser();
	
		
			JSONObject jsonObject = null;
			try {
				Object obj = parser.parse(new FileReader(source));
				jsonObject = (JSONObject) obj;
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
					
	         
		
		
		JSONArray array = (JSONArray) jsonObject.get("array");
		Iterator<Object> iter = array.iterator();
     
		while (iter.hasNext()) {
			CityWithCountry city = new CityWithCountry();
			city.setName((String) jsonObject.get("name"));
			city.setCountry((String) jsonObject.get("country"));
			JSONObject coord = (JSONObject) jsonObject.get("coord");
			city.setLat((String) coord.get("lat"));
			city.setLon((String) coord.getAsString("lon"));
			
			cities.add(city);
		}
		
	
		return cities;
	}



}
