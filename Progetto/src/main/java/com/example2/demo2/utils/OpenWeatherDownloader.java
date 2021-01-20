package com.example2.demo2.utils;

import java.io.File;
import java.util.LinkedHashSet;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example2.demo2.exception.DownloadException;
import com.example2.demo2.model.CityUV;


@Component
public class OpenWeatherDownloader {

	
    private int days;
	
	
	
	public LinkedHashSet<CityUV> download(Iterable<CityUV> collection, int forecastDays) throws DownloadException {
	            
		this.days = forecastDays;
		if( forecastDays <0 || forecastDays >7 ) throw new IllegalArgumentException("Il numero di giorni per le previsioni non può essere negativo");
		
		        for( CityUV c : collection) {
		       	downloadCurrentData(c);
		       	
		       	if(forecastDays == 0) 
		       	      downloadCurrentData(c);
		       		 
		       	
		       	else downloadForecastData(c);
		       	
		        }
				return (LinkedHashSet<CityUV>) collection;
		    
	}
	
	



private void downloadForecastData(CityUV c) {
	RestTemplate restTemplate = new RestTemplate(); 
    File result = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/uvi?lat="+ c.getLat() + "&lon=" +
		c.getLon() + "&cnt=" + days + "&appid=2958fcca248795caf7494bb7bd32ea27", File.class);

    c.JSONtoJava(result); 
			
	}




private void downloadCurrentData(CityUV c) {
	RestTemplate restTemplate = new RestTemplate(); 
	File result = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/uvi?lat="+ c.getLat() + "&lon=" +
			c.getLon() + "&appid=2958fcca248795caf7494bb7bd32ea27", File.class);
	
	 c.JSONtoJava(result); 		
	}

	}






























