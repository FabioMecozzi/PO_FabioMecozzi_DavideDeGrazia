package com.ddgfm.ProgettoOOP.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ddgfm.ProgettoOOP.exception.IdsNotFoundException;
import com.ddgfm.ProgettoOOP.model.City;
import com.ddgfm.ProgettoOOP.model.CollectionUpdate;
import com.ddgfm.ProgettoOOP.repository.CityRepsitory;
import com.ddgfm.ProgettoOOP.repository.OpenWeatherCityRepository;
import com.ddgfm.ProgettoOOP.utils.CityListParser;
/**
 * @author Fabio Mecozzi & Davide De Grazia Classe che gestisce le richieste per
 *         modificare e consultare le repository che contengono liste di città
 */
public class CityService {
	private final static String openWeathercityListSrcPath = "/src/main/resources/openWeatherMap.city.list.json";

	@Autowired
	OpenWeatherCityRepository openWeatherCityRepository;

	@Autowired
	CityRepsitory cityRepsitory;

	public List<City> getCitOpenWeatherMapList() {
		List<City> result = new ArrayList<City>();
		for (City city : openWeatherCityRepository.findAll()) {
			result.add(city);
		}
		return result;
	}

	public List<City> getCityList() {
		List<City> result = new ArrayList<City>();
		for (City city : cityRepsitory.findAll()) {
			result.add(city);
		}
		return result;
	}

	public Iterable<City> getAll() {
		return cityRepsitory.findAll();
	}

	public void update(CollectionUpdate<Long> update) throws IdsNotFoundException {
		if (update.isDelteAll()) {
			cityRepsitory.deleteAll();
		} else {
			try {
				deleteByIds(update.deletingIDList);
			} catch (IdsNotFoundException e) {
				throw e;
			} catch (IllegalArgumentException e) {
				throw e;
			}
		}
		add(update.savingList);
	}

	private void add(Iterable<Long> ids) throws IllegalArgumentException {
		List<Long> idsNotFound = new ArrayList<Long>();
		for (long id : ids) {
			Optional<City> city = openWeatherCityRepository.findById(id);
			if (city.isPresent()) {
				try {
					cityRepsitory.save(city.get()); // prende la città dalla lista che openweather fornisce
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException("L'id non può essere nullo");
				}
			} else {
				idsNotFound.add(id);
			}
		}
		if (!idsNotFound.isEmpty()) {
			throw new IdsNotFoundException(idsNotFound);

		}
	}

	public void loadOpenWeatherCityList() {
		File source = new File(openWeathercityListSrcPath);
		CityListParser parser = new CityListParser();
		List<City> openWeatherCityList = parser.parseIT(source);
		openWeatherCityRepository.saveAll(openWeatherCityList);
	}

	private void deleteByIds(Iterable<Long> ids) throws IdsNotFoundException {
		for (long id : ids) {
			try {
				cityRepsitory.deleteById(id);
			} catch (IllegalArgumentException e) {
				throw e;
			}

		}
	}
}
