package com.example2.demo2.controller;

import java.util.ArrayList; 

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.ClassMetadata;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example2.demo2.model.City;
import com.example2.demo2.model.CityUV;
import com.example2.demo2.model.SchedulerSettings;
import com.example2.demo2.model.StatsImpl;
import com.example2.demo2.repository.CityUVRepository;

import ch.qos.logback.core.filter.Filter;

/**
 * @author Fabio Mecozzi
 * @author Davide De Grazia Classe controller che gestisce tutte le rotte del
 *         servizio
 */
@RestController
public class Controller {

	/**
	 * Rotta di benvenuto: Fornisce all'utente informazioni e link utili
	 * 
	 * @return Json con informazioni utili per l'utilizzo dell'API
	 */
	@GetMapping("/")
	public String Home() {
		String responseString = "";
		// da implementare
		return responseString;
	}

	@GetMapping("/getMetadata")
	public ClassMetadata getMetadata() {
		ClassMetadata metadata = null;
		// implementare
		return metadata;
	}

	/**
	 * Rotta per ottenere i dati dei raggi UV attuali o di forecasting
	 * 
	 * @param forecastingDays numero di giorni per i quali si vuole la previsione
	 *                        (max 7 giorni), se uguale a zero (valore di default)
	 *                        si stanno richiedendo i dati attuali
	 * @param arrayToPopulate Body contenente l'array di città per le quali si
	 *                        vogliono conoscere i dati
	 * @return array passato nel body popolato dai valori dei raggiu uv
	 */
	@GetMapping("/GetData")
	public ArrayList<City> GetData(@RequestBody ArrayList<City> arrayToPopulate,
			@RequestParam(name = "forecastingDays", defaultValue = "0") int forecastingDays) {

		// da implementare

		return arrayToPopulate;
	}

	/**
	 * Rotta per ottenere le statistiche
	 * 
	 * @param filter filtri da applicare alle statistiche
	 * @param key    chiave per usare filtri di default
	 * @return statistiche filtrate
	 */
	@GetMapping("/GetStatistics")
	public StatsImpl<CityUV, Double> GetStatistics(@RequestBody Filter<Double> filter,
			@RequestParam(name = "key") String key) { // filter da modificare, completare dopo aver studiato la classe
														// filter della libreria standars

		// da implementare

		return new StatsImpl<CityUV, Double>(0, 0, 0, 0);
	}

	@GetMapping("/getSchedulerSettings")
	public SchedulerSettings getSchedulerSettings(@RequestBody SchedulerSettings settings,
			@RequestParam(name = "key") String key) {
		return settings;
	}

	@GetMapping("/getErrorLog")
	public Log getErrorLog(@RequestParam(name = "key") String key) {
		Log exceptionLog = null;
		return exceptionLog;
	}
	
	//////prova per junit
	@Autowired
	CityUVRepository repository;
	
	
	
	
	
	/////////////////////
}
