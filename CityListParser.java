package com.ddgfm.ProgettoOOP.utils;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.ddgfm.ProgettoOOP.model.City;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class CityListParser {
	
	private String name;
	private String country;
	private String lat;
	private String lon;
	
	
private class CityWithCountry extends City {

		public CityWithCountry() {
			super();
			
		}
    	
		public String country;
		
    }
		
	public ArrayList<CityWithCountry> parse (File file) {
		@SuppressWarnings("deprecation")
		    
		    JSONParser parser = new JSONParser();
			Object obj = null;
			
			try {
				obj = parser.parse(new FileReader(file));
			} catch (FileNotFoundException | ParseException e) {
				
				e.printStackTrace();
			}
			JSONObject jsonObject = (JSONObject) obj;
				
                
			
				this.name = (String) jsonObject.get("name");
				@SuppressWarnings("unused")
				JSONObject coord = (JSONObject) jsonObject.get("coord");
				this.country = (String) jsonObject.get("county");
				this.lat = (String) jsonObject.get("lat");
				this.lon = (String) jsonObject.get("lon");
		
								 
					 ArrayList <CityWithCountry> list = new ArrayList <>();
					 Iterator <CityWithCountry> it = list.iterator();
					 
					 while (it.hasNext() == false)
					 {
						list.add((CityWithCountry) obj);
						it.next();
					 }
					 
					 for (CityWithCountry o : list) {
						 //o.name = this.name;
						 //o.lat = this.lat
						 //o.lon = this.lon;
					 }

				    return list;
	     }

	
}
