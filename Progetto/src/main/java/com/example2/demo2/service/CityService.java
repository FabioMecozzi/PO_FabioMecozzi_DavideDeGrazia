package com.example2.demo2.service;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example2.demo2.model.City;
import com.example2.demo2.model.CityUV;
import com.example2.demo2.model.CollectionUpdate;
import com.example2.demo2.repository.CityUVRepository;
import com.example2.demo2.utils.CityListParser;

@Service
public class CityService {
	private final static String openWeathercityListSrcPath = "/src/main/resources/openWeatherMap.city.list.json";

	@Autowired
	CityUVRepository repository;

	/**
	 * 
	 * @return Tutte le città della repository
	 */
	public LinkedHashSet<City> getCityOpenWeatherMapList() {
		Iterable<CityUV> iterable = repository.findAll();
		return (StreamSupport.stream(iterable.spliterator(), false)
				.collect(Collectors.toCollection(LinkedHashSet::new)));
	}

	/**
	 * 
	 * @return Le città monitorate
	 */
	// TODO: implementare questa funzione con le queries alla repository
	public LinkedHashSet<CityUV> getAllMonitored() {
		Iterable<CityUV> iterable = repository.findAll();
		return (StreamSupport.stream(iterable.spliterator(), false).filter((c) -> c.isMonitored())
				.collect(Collectors.toCollection(LinkedHashSet::new)));
	}

	/**
	 * Aggiorna le città da monitorare
	 * 
	 * @param update CollectionUpdate<Long> contenente le informazioni su quali
	 *               città eliminare dalle città monitorate e quali città aggiungere
	 *               alle città monitorate attraverso gli id
	 */
	// TODO: implementare questa funzione con le queries alla repository
	public void updateMonitoredCities(CollectionUpdate<Long> update) {
		if (update.isDelteAll()) {
			try {
				for (CityUV city : repository.findAll()) {
					city.setMonitored(false);
				}
			} catch (Exception e) {
			}

		} else {
			if (update.deletingList != null) {
				setMonitoredByIds(update.deletingList, false);
			}
		}
		if (update.getSavingList() != null) {
			setMonitoredByIds(update.savingList, true);
		}
	}
	/**
	 * Metodo che aggiorna il campo monitored delle città relative agli id passati con il parametro
	 * @param IDs Id delle città da settare
	 * @param valueToSet valore da settare
	 */
	private void setMonitoredByIds(Iterable<Long> IDs, boolean valueToSet) {
		for (CityUV cityUV : repository.findAllById(IDs)) {
			cityUV.setMonitored(valueToSet);
		}
	}

	/**
	 * Carica all'interno della repository la lista delle città all'indirizzo indicato da source
	 */
	// TODO: implementare con i filtri
	public void loadOpenWeatherCityList() {
		File source = new File(openWeathercityListSrcPath);
		CityListParser parser = new CityListParser();
		@SuppressWarnings("unchecked")
		List<CityUV> openWeatherCityList = (List<CityUV>) (List<?>) parser.parse(source);
		repository.saveAll(openWeatherCityList);
	}

	/**
	 * Elimina dalla repository le città relative agli id indicati
	 * @param ids id delle città da eliminare
	 * @throws IllegalArgumentException se uno degli id è null
	 */
	public void deleteByIds(Iterable<Long> ids) throws IllegalArgumentException {
		for (long id : ids) {
			repository.deleteById(id);
		}
	}

}
