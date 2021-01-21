package com.example2.demo2.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example2.demo2.model.City;
import com.example2.demo2.model.CityUV;
import com.example2.demo2.model.CollectionUpdate;
import com.example2.demo2.repository.CityUVRepository;
import com.example2.demo2.utils.CityListParser;

/**
 * Service che gestisce la repository CityUVRepository a prescindere dai valori UV registrati nelle singole città
 * @author Fabio Mecozzi & davide De Grazia
 *
 */
@Service
public class CityService {
	private static String relativeOpenWeatherCityListSrcPath = "/src/main/resources/city.list.json";
	/**
	 * Mapppa in cui sono registrati i nomi delle città legati ai rispettivi id
	 */
	private HashMap<String, Long> namesMap= new HashMap<>();
	/**
	 * Repository gestita da Spring
	 */
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
	 * Metodo che aggiorna il campo monitored delle città relative agli id passati
	 * con il parametro
	 * 
	 * @param IDs        Id delle città da settare
	 * @param valueToSet valore da settare
	 */
	private void setMonitoredByIds(Iterable<Long> IDs, boolean valueToSet) {
		for (CityUV cityUV : repository.findAllById(IDs)) {
			cityUV.setMonitored(valueToSet);
		}
	}

	/**
	 * Carica all'interno della repository la lista delle città all'indirizzo
	 * indicato da relativeOpenWeatherCityListSrcPath, inserendo solamente le città italiane
	 */
	public void loadOpenWeatherCityList() {
		File source = new File(System.getProperty("user.dir")+ relativeOpenWeatherCityListSrcPath);
		Set<City> openWeatherCityList =  CityListParser.parseIT(source);
		HashSet<CityUV> castedCollection= new HashSet<>();
		for (City city : openWeatherCityList) {
			castedCollection.add(new CityUV(city));
		}
		repository.saveAll(castedCollection);
		for (CityUV cityUV : castedCollection) {
			namesMap.put(cityUV.getName(), cityUV.getId());
		}
	}

	/**
	 * Elimina dalla repository le città relative agli id indicati
	 * 
	 * @param ids id delle città da eliminare
	 * @throws IllegalArgumentException se uno degli id è null
	 */
	public void deleteByIds(Iterable<Long> ids) throws IllegalArgumentException {
		for (long id : ids) {
			repository.deleteById(id);
		}
	}
	/**
	 * Metodo che cerca elementi nella repository attraverso il nome
	 * @param namesList lista dei nomi da cercare
	 * @return la lista delle città trovate
	 */
	public ArrayList<CityUV> findByNames(List<String> namesList) {
		ArrayList<CityUV> result = new ArrayList<CityUV>();
		for (String name : namesList) {
			if (namesMap.containsKey(name)) {
				Optional<CityUV> city=repository.findById(namesMap.get(name));
				if(repository.findById(namesMap.get(name)).isPresent()) result.add(city.get());			
			}
		}
		return result;
	}

}
