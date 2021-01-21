package com.example2.demo2.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example2.demo2.exception.DownloadException;
import com.example2.demo2.model.CityUV;


@Component
public class OpenWeatherDownloader {

	
	/**
	 * attributo che serve per passare l'argomento forecastDays della classe LinkedHashSet alle classi downloadForecastData
	 * e downloadCurrentData
	 */
    private int days;
	

    /**
     * 
     * @param collection: di città di cui vogliamo scaricare i dati 
     * @param forecastDays: numero di giorni per cui vogliamo i dati (0 per i dati attuali, 1 per i dati attuali e quelli del giorno dopo, ecc...)
     * @return una collection di CityUV popolata con i dati delle previsioni
     * @throws DownloadException
     */
	public Iterable<CityUV> download(Iterable<CityUV> collection, int forecastDays) throws DownloadException {
	            
		this.days = forecastDays;
		if( forecastDays <0 || forecastDays >7 ) throw new IllegalArgumentException("Il numero di giorni per le previsioni non può essere negativo");
		
		        for( CityUV c : collection) {
		       	downloadCurrentData(c);
		       	
		       	if(forecastDays == 0) 
		       	      downloadCurrentData(c);
		       		 
		       	
		       	else downloadForecastData(c);
		       	
		        }
				return collection;
		    
	}
	
	

/**
 * Classe che viene chiamata solo se ForecastDay == 0, e attraverso l'url di Postman, ottiene un JSON con i dati solo attuali 
 * di cui poi viene fatto il parsing tramite la classe JSONtoJava
 * @param c: oggetto della classe CityUV
 */

private void downloadForecastData(CityUV c) {
     
	    String url = "http://api.openweathermap.org/data/2.5/uvi?lat="+ c.getLat() + "&lon=" +
		c.getLon() + "&cnt=" + days + "&appid=2958fcca248795caf7494bb7bd32ea27";
		
		try {
			URLConnection openConnection = new URL(url).openConnection();
			@SuppressWarnings("unused")
			InputStream in = openConnection.getInputStream();
		} catch (IOException e) {
			
			System.out.println("Errore di Input");
		}
		
		RestTemplate connection = new RestTemplate();
		File data = connection.getForObject(url, File.class);

    c.JSONtoJava(data); 
			
	}


/**
 * Classe che viene chiamata solo se ForecastDay > 0, e attraverso l'url di Postman, ottiene un JSON con i dati forecast
 * di cui poi viene fatto il parsing tramite la classe JSONtoJava
 * @param c: oggetto della classe CityUV
 */

private void downloadCurrentData(CityUV c) {
	
	String url = "http://api.openweathermap.org/data/2.5/uvi?lat="+ c.getLat() + "&lon=" +
			c.getLon() + "&appid=2958fcca248795caf7494bb7bd32ea27";
	try {
		URLConnection openConnection = new URL(url).openConnection();
		@SuppressWarnings("unused")
		InputStream in = openConnection.getInputStream();
	} catch (IOException e) {
		
		System.out.println("Errore di Input");
	}
	
	RestTemplate connection = new RestTemplate();
	File data = connection.getForObject(url, File.class);

	
	 c.JSONtoJava(data); 		
	}

}