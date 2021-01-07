package com.ddgfm.ProgettoOOP.model;

import java.io.File;
import java.util.Date;
import java.util.TreeSet;

import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 * 
 * @author davidedegrazia
 *
 */

public class CityUV extends City {

	private static double accouracy = 0.0;
	private static int goodForecast = 0;
	private static int totalForecast = 0;
	
	private class Uv {
         private Date dataeora;
		 private double uVvalue;
    }
/**
 * 
 * @return l'accuratezza 
 */
	public static double getAccouracy() {
		return accouracy;
	}

/**
 * Si setta l'accuratezza
 * @param accouracy
 */
	public static void setAccouracy(double accouracy) {
		CityUV.accouracy = accouracy;
	}

/**
 * 
 * @return il conteggio delle previsioni azzeccate
 */
	public static int getGoodForecast() {
		return goodForecast;
	}

	
/** 
 * 
 * @return il numero totale delle previsioni fatte
 */
	public static int getTotalForecast() {
		return totalForecast;
	}




//	public CityUV (String name) {
		// Trova nella lista il pezzetto di città corrispondente al 
	    // nome e riempie i campi
		
	//}

/**
 * E' una lista ordinata popolata da valori UV
 */
	private TreeSet<Long> uVlist;
	private Date dataeora;
	private long uVvalue;
	
/**
 * Il seguente metodo si interfaccia con l'api OpenWeather,
 * prende il blocco di codice in JSON, fa il parsing e dopodichè
 * riempie i campi 
 * @param json
 */

	public void JSONtoJava(File json) {
		@SuppressWarnings("deprecation")
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		RestTemplate restTemplate = new RestTemplate();

		String apirisultato = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/uvi?lat=" + this.lat
				 + "&lon=" + this.lon  + "&appid=58b2755bcc79abb83cce4474b0a795b8", String.class);

		System.out.println(apirisultato);

		try {
			obj = (JSONObject) parser.parse(apirisultato);
			         
			this.dataeora = (Date) obj.get("date_iso");
			this.uVvalue = (long) obj.get("value");
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}