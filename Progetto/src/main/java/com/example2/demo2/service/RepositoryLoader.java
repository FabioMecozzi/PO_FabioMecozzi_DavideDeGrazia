package com.example2.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Classe che implementa l'interfaccia Spring CommandLineRunner, che indica che
 * il metodo "run(String... args)" all'interno della classe deve essere
 * eseeguito all'avvio dell'applicazione
 * 
 * @author Fabio Mecozzi & Davide De Grazia
 *
 */
@Component
public class RepositoryLoader implements CommandLineRunner {
	@Autowired
	CityService service;
	@Autowired
	Scheduler scheduler;
	@Override
	public void run(String... args) throws Exception {
		service.loadOpenWeatherCityList();
		scheduler.sampleMonitored();
	}

}
