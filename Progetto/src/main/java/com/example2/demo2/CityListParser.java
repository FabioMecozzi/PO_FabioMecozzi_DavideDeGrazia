package com.example2.demo2.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.example2.demo2.model.CityWithCountry;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 * 
 * @author Fabius Mecozzi & Davide De Grazia
 * Classe che fa il parsing  del file "city.list.json" e restituisce una Lista con i dati ovvero "id", "name", "country", e le coordinate "lat" e "lon".
 *
 */

public class CityListParser { 

	/**
	 * 
	 * @param source di tipo File
	 * @return cities: ovvero un ArreyList
	 */
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
	         
	         for(int i=0; i< array.size(); i++) {
	         JSONObject object=(JSONObject) array.get(i);
	         CityWithCountry city = new CityWithCountry();
	         city.setId((Long) object.get("id"));
	         city.setName((String) object.get("name"));
	         city.setCountry((String) object.get("country"));
	         
	         JSONObject coord = (JSONObject) object.get("coord");
	         city.setLat((String)coord.get("lat"));
			 city.setLon((String)coord.get("lon"));
		
			    cities.add(city);
	         }
		
	         return cities;
     
	}
}
