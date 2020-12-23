package com.ddgfm.ProgettoOOP.model;


import java.util.Date;

import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

//Questa classe rappresenta il modello dei dati contenuti nell'archivio.
//Ci sta solo il problema che bisogna passare per parametri le coordinate,
//mentre sarebbe preciso se passassimo il nome della città


public class City {
	
	private String cityname; 
	private Date dataeora;
	private long lat;
	private long lon;
	private long uVvalue;
	
	public City(String cityname, Date dataeora) {
		super();
		this.cityname = cityname;
		this.dataeora = dataeora;
	}

	public String getCityname() {
		return cityname;
	}
 
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

    public Date getDataeora() {
		return dataeora;
	}

	public long getLat() {
		return lat;
	}

	public void setLateLon(long lat, long lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public long getLon() {
		return lon;
	}

	public long getuVvalue() {
		return uVvalue;
	}

	

	
	public void JSONtoJava() {
		@SuppressWarnings("deprecation")
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		RestTemplate restTemplate = new RestTemplate(); 
		String apirisultato = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/uvi?lat=" + this.lat
				+ "&lon=" + this.lon + "&appid=58b2755bcc79abb83cce4474b0a795b8", String.class);
	
		System.out.println(apirisultato); 
	
		try {
			obj = (JSONObject) parser.parse(apirisultato);
				//manca il passaggio da nome a coordinate e da coordinate a nome
			
				this.dataeora = (Date) obj.get("date_iso");
				this.uVvalue = (long) obj.get("value");
		} catch (ParseException e) {
		  e.printStackTrace();
		}
			
	}
}