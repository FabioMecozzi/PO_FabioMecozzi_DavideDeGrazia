package com.ddgfm.ProgettoOOP.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class City {
	
	private int id;
	private String name;
	
	public City (String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private class Coord {

		public String lat;
		public String lon;

		@SuppressWarnings("unused")
		public Coord( String lat, String lon) {
			super();
			this.lat = lat;
			this.lon = lon;
		}
	
		
		@SuppressWarnings("unused")
		public String getLat() {
			return lat;
		}
		@SuppressWarnings("unused")
		public void setLat(String lat) {
			this.lat = lat;
		}
		@SuppressWarnings("unused")
		public String getLon() {
			return lon;
		}
		@SuppressWarnings("unused")
		public void setLon(String lon) {
			this.lon = lon;
		}
		
	} 

	
public void ParserCity () {	
	@SuppressWarnings("deprecation")
	JSONParser parser = new JSONParser();
	
	try {
		Object obj = parser.parse(new FileReader("city.list.json"));
		JSONObject jsonObject = (JSONObject) obj;
			
			this.name = (String) jsonObject.get("name");
			@SuppressWarnings("unused")
			JSONObject coord = (JSONObject) jsonObject.get("coord");
			this.lat = (String) jsonObject.get("lat");
			this.lon = (String) jsonObject.get("lon");
			
	} catch (FileNotFoundException | ParseException e) {
		e.printStackTrace();
	}
		

     }
}   		

