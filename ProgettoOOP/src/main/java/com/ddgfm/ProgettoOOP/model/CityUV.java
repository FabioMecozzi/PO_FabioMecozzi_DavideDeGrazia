package com.ddgfm.ProgettoOOP.model;

import java.io.File;
import java.util.Date;
import java.util.TreeSet;

import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 * Classe che rappresenta una città a cui è legato uno storico di valori di uv
 * @author meefa
 *
 */
public class CityUV extends City {
	private TreeSet<UV> uvSet;
	private int goodForecast;
	private int totalForecast;
	private double accouracy=-1.0;
	
	/**
	 * cosrtuttore
	 * @param uvSet storico degli uv per la città
	 */
	CityUV(TreeSet<UV> uvSet){
		this.uvSet=uvSet;
		
	}
	
	/*
	CityUV(File json){
		JSONtoJava(json);
	}
	//funzione che bisogna implementare---->
	@Override
	public void JSONtoJava(File json) {
		@SuppressWarnings("deprecation")
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		RestTemplate restTemplate = new RestTemplate();
		String apirisultato = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/uvi?lat=" + this.lat
				+ "&lon=" + this.lon + "&appid=58b2755bcc79abb83cce4474b0a795b8", String.class);

		System.out.println(apirisultato);

		try {
			obj = (JSONObject) parser.parse(apirisultato);
			// manca il passaggio da nome a coordinate e da coordinate a nome

			this.dataeora = (Date) obj.get("date_iso");
			this.uVvalue = (long) obj.get("value");
		} catch (ParseException e) {
			e.printStackTrace();

		}

	}*/
	
	/*
	 * Storico di valori uv
	 */
	private class UV{
		public double value;
		public Date date;
	}
}

