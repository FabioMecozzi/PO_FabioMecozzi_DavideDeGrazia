package com.example2.demo2.service;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example2.demo2.model.City;
import com.example2.demo2.model.CityUV;
import com.example2.demo2.utils.filter.PredicatesUtils;

/**
 * Classe che si occupa di filtrare collezioni di CityUV
 * @author meefa
 *
 */
@Service
public class FilterService {
	/**
	 * Metodo che filtra un Set<CityUV> secondo un filtro descritto in un file json e secondo un numero di giorni precedenti
	 * @param collection collezione da filtrare
	 * @param jsonFilter file che descrive i filtri da applicare
	 * @param dateFilter numero di giorni precedenti da considerare
	 * @return una nuova collezione filtrata
	 */
	public Set<CityUV> filterCityUVs(LinkedHashSet<CityUV> collection, File jsonFilter, int dateFilter) {
		return filterCityUVs(filterRecords(collection, dateFilter), jsonFilter);
	}
	/**
	 * Metodo che filtra un Set<CityUV> secondo un filtro descritto in un file json
	 * @param collection collezione da filtrare
	 * @param jsonFilter file che descrive i filtri da applicare
	 * @return una nuova collezione filtrata
	 */
	public Set<CityUV> filterCityUVs(LinkedHashSet<CityUV> collection, File jsonFilter) {
		Set<CityUV> result = collection.stream().filter(PredicatesUtils.fromJson(jsonFilter))
				.collect(Collectors.toSet());
		return result;
	}
	/**
	 * Metodo che filtra un LinkedHashSet<CityUV> secondo un numero di giorni precedenti da considerare
	 * @param collection collezione da filtrare
	 * @param dateFilter numero di giorni precedenti da considerare
	 * @return una nuova collezione contenente dei CityUV della quale i CityUV contenuti hanno i record di UV senza i giorni che precedono i giorni di oggi meno dateFilter
	 */
	private LinkedHashSet<CityUV> filterRecords(LinkedHashSet<CityUV> collection, int dateFilter) {
		LinkedHashSet<CityUV> newSet = new LinkedHashSet<>();
		CityUV newCityUV;
		for (CityUV cityUV : collection) {
			newCityUV = new CityUV((City) cityUV);
			newCityUV.setRecord(filterRecord(cityUV, dateFilter));
			newCityUV.setAccouracy(cityUV.getAccouracy());
			newSet.add(newCityUV);
		}
		return newSet;
	}
	/**
	 * Metodo che filtra un LinkedHashMap<LocalDate, Double> secondo il numero di giorni contenuti in dateFilter
	 * @param cityUV collezione da filtrare
	 * @param dateFilter numero di giorni da considerare
	 * @return la collezione ccontenente solo i valori relativi ai giorni successivi a oggi meno dateFilter
	 */
	private LinkedHashMap<LocalDate, Double> filterRecord(CityUV cityUV, int dateFilter) {
		LinkedHashMap<LocalDate, Double> result = cityUV.getRecord().entrySet().stream()
				.filter(d -> d.getKey().isAfter(LocalDate.now().minusDays(dateFilter)))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
		return result;
	}

}
