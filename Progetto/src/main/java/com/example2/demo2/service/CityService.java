package com.example2.demo2.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public List<City> getCityOpenWeatherMapList() { 
		List<City> result = new ArrayList<City>();
		for (CityUV city : repository.findAll()) {
			result.add(city);
		}
		return result;
	}
	
	// TODO: implementare questa funzione con le queries alla repository
	public ArrayList<City> getMonitoredCities() { 
		ArrayList<City> result = new ArrayList<City>();
		for (CityUV city : repository.findAll()) {
			if(city.isMonitored()) {
				result.add(city);
			}
		}
		return result;
	}

	// TODO: implementare questa funzione con le queries alla repository
	public void updateMonitoredCities(CollectionUpdate<Long> update) {  
		if (update.isDelteAll()) {
			for (CityUV city : repository.findAll()) {
				city.setMonitored(false);
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
	
	private void setMonitoredByIds(Iterable<Long> IDs, boolean valueToSet ) { 
		for(CityUV cityUV : repository.findAllById(IDs)){
			cityUV.setMonitored(valueToSet);
		}
	}

	//TODO: implementare con i filtri
	public void loadOpenWeatherCityList() {
		File source = new File(openWeathercityListSrcPath);
		CityListParser parser = new CityListParser();
		@SuppressWarnings("unchecked")
		List<CityUV> openWeatherCityList = (List<CityUV>)(List<?>) parser.parse(source);
		repository.saveAll(openWeatherCityList);
	}

	public void deleteByIds(Iterable<Long> ids) throws IllegalArgumentException {
		for (long id : ids) {
			try {
				repository.deleteById(id);
			} catch (Exception e) {
				throw e;
			}

		}
	}

	
}
