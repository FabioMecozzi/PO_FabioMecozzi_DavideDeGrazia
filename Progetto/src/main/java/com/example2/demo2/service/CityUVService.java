package com.example2.demo2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example2.demo2.exception.IdNotFoundException;
import com.example2.demo2.model.CityUV;
import com.example2.demo2.repository.CityUVRepository;

/**
 * Classe che gestisce i record degli UV negli elementi della repository
 * CityUVRepository
 * 
 * @author meefa
 *
 */
@Service
public class CityUVService {
	@Autowired
	CityUVRepository repository;
	@Autowired
	FilterService filter;
	/**
	 * Percentuale per la quale se una previsione si discosta in percentuale dal valore reale per più di questo valore, la previsione viene considerata errata
	 */
	private final double trasholdError=0.05;
	/**
	 * Metodo che restituisce il record delle previsioni relative ad una città dall'id della città stessa
	 * @param id id della cità da cercare
	 * @return il record della città
	 * @throws IdNotFoundException se la città non viene trovata
	 * @throws IllegalArgumentException se l'id è null
	 */
	public LinkedHashMap<LocalDate, Double> getRecordById(Long id)
			throws IdNotFoundException, IllegalArgumentException {
		Optional<CityUV> optionalCity = repository.findById(id);
		if (optionalCity.isPresent()) {
			return optionalCity.get().getRecord();
		} else {
			throw new IdNotFoundException();
		}
	}
	/**
	 * Metodo che restituisce un arraylist di record di città a partire da un arraylist di id
	 * @param ids id delle città delle quali si vuole ottenere il record
	 * @return l'arraylist dei record delle città
	 * @throws IllegalArgumentException se uno o più id sono null
	 */
	public ArrayList<LinkedHashMap<LocalDate, Double>> getRecordsByIds(ArrayList<Long> ids)
			throws IllegalArgumentException {
		ArrayList<LinkedHashMap<LocalDate, Double>> resultArrayList = new ArrayList<>();
		for (CityUV cityUV : repository.findAllById(ids)) {
			resultArrayList.add(cityUV.getRecord());
		}
		return resultArrayList;
	}
	/**
	 * Metodo che aggiorna gli uv nelle città delle repository 
	 * @param collection da cui attingere i valori
	 */
	public void update(Collection<CityUV> collection) {
		collection.stream()
		.forEach((c)-> this.update(c));
	}
	
	/**
	 * Metodo che aggiorna gli uv in una singola città delle repository
	 * @param cityUV CityUV contenente i valori
	 */
	public void update(CityUV cityUV) {
		Optional<CityUV> toUpdate = repository.findById(cityUV.getId());
		if(toUpdate.isPresent()) {
			try {
				toUpdate.get().updateRecord(cityUV, trasholdError);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			repository.save(toUpdate.get());
		}
	}

}
