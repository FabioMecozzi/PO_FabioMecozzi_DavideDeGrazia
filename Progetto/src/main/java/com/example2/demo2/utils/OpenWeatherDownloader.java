package com.example2.demo2.utils;

import java.io.File;
import java.util.LinkedHashSet;

import org.springframework.stereotype.Component;

import com.example2.demo2.exception.DownloadException;
import com.example2.demo2.model.CityUV;

@Component
public class OpenWeatherDownloader {

	/**
	 * 
	 * @param collection una lista di città di cui scaricare i dati
	 * @return la stessa lista di città con valori aggiunti (a meno di eccezioni)
	 * @throws DownloadException
	 */
	public LinkedHashSet<CityUV> download(LinkedHashSet<CityUV> collection) throws DownloadException{
		for(CityUV c: collection) {
			c.JSONtoJava(download(c));
			
		}
		return collection;
	}
	public LinkedHashSet<CityUV> download(Iterable<CityUV> collection) throws DownloadException{
		for(CityUV c: collection) {
			c.JSONtoJava(download(c));
			
		}
		return null;
	}
	private File download(CityUV c) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
