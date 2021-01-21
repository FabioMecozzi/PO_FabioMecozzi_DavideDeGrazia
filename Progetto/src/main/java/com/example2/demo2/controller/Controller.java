package com.example2.demo2.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.ClassMetadata;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example2.demo2.exception.ControllerException;
import com.example2.demo2.model.CityUV;
import com.example2.demo2.model.CollectionUpdate;
import com.example2.demo2.model.Log;
import com.example2.demo2.model.StatsImpl;
import com.example2.demo2.service.CityService;
import com.example2.demo2.service.CityUVService;
import com.example2.demo2.service.FilterService;
import com.example2.demo2.utils.OpenWeatherDownloader;
import com.example2.demo2.utils.StatsCalculator;


/**
 * @author Fabio Mecozzi
 * @author Davide De Grazia Classe controller che gestisce tutte le rotte del
 *         servizio
 */
@RestController
public class Controller {
	@Autowired
	CityService cityService;
	@Autowired
	CityUVService cityUVService;
	@Autowired
	OpenWeatherDownloader downloader;
	@Autowired
	Log log;
	@Autowired
	FilterService filterService;
	
	/**
	 * Rotta per ottenere i dati dei raggi UV attuali o di forecasting
	 * 
	 * @param forecastingDays numero di giorni per i quali si vuole la previsione
	 *                        (max 7 giorni), se uguale a zero (valore di default)
	 *                        si stanno richiedendo i dati attuali
	 * @param citiesArray Body contenente l'array di nomi di città per le quali si
	 *                        vogliono conoscere i dati
	 * @return array passato nel body popolato dai valori dei raggiu uv
	 * @throws ControllerException 
	 */
	@PostMapping("/GetData")
	public ArrayList<CityUV> GetData(@RequestBody ArrayList<String> citiesArray,
			@RequestParam(name = "forecastingDays", defaultValue = "0") int forecastingDays) throws ControllerException {	
		ArrayList<CityUV> resultArrayList= new ArrayList<>();
		for (CityUV cityUV : cityService.findByNames(citiesArray)) {
			resultArrayList.add(new CityUV(cityUV));
		}
		try {
			downloader.download(resultArrayList, forecastingDays);
		} catch (Exception e) {
			log.addException(e);
			throw new ControllerException("Si è verificato un errore nel download dei dati");
		}
		
		return resultArrayList;
	}

	/**
	 * Rotta per ottenere le statistiche, se nel File jsonFilter vengono specificate delle città e nel path è presente una città il path viene considerata la città indicata nel path
	 * @param jsonFilter filtri da applicare alle statistiche, contenente filtri su città e precisione delle previsioni
	 * @param cityName nome della città di cui si vogliono conoscere le statistiche
	 * @return statistiche filtrate
	 */
	@PostMapping("/GetStatistics/{city}")
	public StatsImpl GetStatistics(@RequestBody(required = false) File jsonFilter, @PathVariable(required = false) String cityName,
			@RequestParam(name = "days", required = false, defaultValue = "0") String daysString) { 
		int days= Integer.parseInt(cityName);
		Set<CityUV> filtered;
		if (cityName==null) {
				filtered= filterService.filterCityUVs(cityService.getAllMonitored(), jsonFilter, days);
		} else {
			
			filtered= filterService.filterCityUVs(cityService.getAllMonitored(), jsonFilter, days);
		}
		return StatsCalculator.CalculateStats(filtered);
		
	}
	@GetMapping("/MonitoredCities")
	public LinkedHashSet<CityUV> getMonitoredCities(){
		return cityService.getAllMonitored();
	}
	
	@PostMapping("/MonitoredCities")
	public LinkedHashSet<CityUV> setMonitoredCities( @RequestBody(required = true) CollectionUpdate<Long> update, @RequestParam(name = "key", required = true) String key) throws ControllerException {
		if(key!=null) {
			if (update!=null) {
				if (keyIsGoog(key)) {
					cityService.updateMonitoredCities(update);
				}else {
					throw new ControllerException("La chiave inserita non risulta corretta");

				}
				
			} else {
				throw new ControllerException("Inserire un body valido");
			}
			
		}
		return cityService.getAllMonitored();
	}

	@GetMapping("/getErrorLog")
	public LinkedHashMap<LocalDate, Exception> getErrorLog(@RequestParam(name = "key") String key) throws ControllerException {
		if(keyIsGoog(key)) {
			return log.getExceptionLog();
		} else {
			throw new ControllerException("La chiave di autenticazione non risulta essere corretta");
		}
		
	}
	
	private boolean keyIsGoog(String key) {
		if ("${application.adminKey}"==key) {
			return true;
		}
		return false;
	}

	@ExceptionHandler(ControllerException.class)
	public ModelAndView ControllerExceptionHandler(ControllerException e) {
		log.addException(e);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("error");
	    modelAndView.addObject("message", e.getMessage());
	    return modelAndView;
	}
	
}
