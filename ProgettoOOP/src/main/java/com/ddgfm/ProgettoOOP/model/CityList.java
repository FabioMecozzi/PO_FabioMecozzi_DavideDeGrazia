package com.ddgfm.ProgettoOOP.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class CityList {
	
	private String cityname; 
	private long lat;
	private long lon;
	
	public String getCityname() {
		return cityname;
	}
 
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public long getLat() {
		return lat;
	}
	
	public long getLon() {
		return lon;
	}

	public void setLateLon(long lat, long lon) {
		this.lat = lat;
		this.lon = lon;
	}



public void Parser() {
    
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray) parser.parse(new FileReader("city.list.json"));
		} catch (FileNotFoundException | ParseException e1) {
			
			e1.printStackTrace();
		}

        try {
			for (Object o : jsonArray) {
				if ( City.get("country")== "IT") {
				
			    JSONObject city = (JSONObject) o;

			    String strName = (String) city.get("name");
			    System.out.println("Name: " + strName);
			    this.cityname = strName; 

			    JSONArray arrays = (JSONArray) city.get("coord");
			    for (Object object : arrays) {
			    	
			    	
			    }
				}     
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           

        }

    }

