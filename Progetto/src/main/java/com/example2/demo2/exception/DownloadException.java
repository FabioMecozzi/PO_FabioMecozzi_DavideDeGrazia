package com.example2.demo2.exception;

import java.util.LinkedHashSet;

import com.example2.demo2.model.CityUV;

/**
 * Eccezione personalizzata che indica un errore nel download (attraverso l'API di OpenWeatherMap) dei dati relativi a una città o a una collection di città
 * @author Fabio Mecozzi & Davide De Grazia
 *
 */
public class DownloadException extends Exception {

	/**
	 * Unic identifier
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Costruttore
	 */
	public DownloadException() {
		super();
	}
	/**
	 * Costruttore
	 * @param message messaggio contenente informazioni sulla natura dell'errore
	 */
	public DownloadException(String message) {
		super(message);
	}
	
	/**
	 * Costruttore
	 * @param citiesFailed
	 */
	public DownloadException(LinkedHashSet<CityUV> citiesFailed) {
		super();
		this.citiesFailed = citiesFailed;
	}
	
	/**
	 * LinkedHasSet di città che non sono state scaricate
	 */
	private LinkedHashSet<CityUV> citiesFailed= new LinkedHashSet<CityUV>();
	/**
	 * Se true i
	 */
	boolean solved=false;
	
	/**
	 * 
	 * @return LinkedHasSet dellle città che non sono state scaricate
	 */
	public LinkedHashSet<CityUV> getCitiesFailed() {
		return citiesFailed;
	}
	
	/**
	 * 
	 * @param citiesFailed LinkedHasSet di città che non sono state scericate
	 */
	public void setCitiesFailed(LinkedHashSet<CityUV> citiesFailed) {
		this.citiesFailed = citiesFailed;
	}
	
	/**
	 * Aggiunge una città al LinkedHasSet di città che non sono state scericate
	 * @param city città da aggiungere
	 */
	public void AddCity(CityUV city) {
		
		citiesFailed.add(city);
	}

}
