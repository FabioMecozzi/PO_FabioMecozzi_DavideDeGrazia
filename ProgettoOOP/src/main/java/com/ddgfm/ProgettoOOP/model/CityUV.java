package com.ddgfm.ProgettoOOP.model;

import java.util.Date;
import java.util.TreeSet;

import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

//Questa classe rappresenta il modello dei dati contenuti nell'archivio.
//Ci sta solo il problema che bisogna passare per parametri le coordinate,
//mentre sarebbe preciso se passassimo il nome della città

public class CityUV extends City {

	private class Uv {

		private Date dataeora;
		private long uVvalue;

		public Uv(Date dataeora, long uVvalue) {
			super();
			this.dataeora = dataeora;
			this.uVvalue = uVvalue;
		}

	}

	private static double accouracy = 0.0;
	private static int goodForecast = 0;
	private static int totalForecast = 0;

	public static double getAccouracy() {
		return accouracy;
	}

	public static void setAccouracy(double accouracy) {
		CityUV.accouracy = accouracy;
	}

	public static int getGoodForecast() {
		return goodForecast;
	}

	public static void setGoodForecast(int goodForecast) {
		CityUV.goodForecast = goodForecast;
	}

	public static int getTotalForecast() {
		return totalForecast;
	}

	public static void setTotalForecast(int totalForecast) {
		CityUV.totalForecast = totalForecast;
	}

	private long uVvalue;
	private Date dataeora;

	public CityUV(int id, String name, Date dataeora, long uVvalue) {
		super(id, name);
		this.dataeora = dataeora;
		this.uVvalue = uVvalue;
	}

	private TreeSet<Long> uVlist;


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
			// manca il passaggio da nome a coordinate e da coordinate a nome

			this.dataeora = (Date) obj.get("date_iso");
			this.uVvalue = (long) obj.get("value");
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}