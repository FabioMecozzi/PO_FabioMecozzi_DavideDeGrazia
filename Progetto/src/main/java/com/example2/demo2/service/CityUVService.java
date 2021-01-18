package com.example2.demo2.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example2.demo2.exception.IdNotFoundException;
import com.example2.demo2.model.CityUV;
import com.example2.demo2.repository.CityUVRepository;
import com.example2.demo2.utils.CollectionFilter;

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
	CollectionFilter filter;

	public LinkedHashMap<LocalDate, Double> getRecordById(Long id)
			throws IdNotFoundException, IllegalArgumentException {
		Optional<CityUV> optionalCity = repository.findById(id);
		if (optionalCity.isPresent()) {
			return optionalCity.get().getRecord();
		} else {
			throw new IdNotFoundException();
		}
	}

	public ArrayList<LinkedHashMap<LocalDate, Double>> getRecordsByIds(Iterable<Long> ids)
			throws IllegalArgumentException {
		ArrayList<LinkedHashMap<LocalDate, Double>> resultArrayList = new ArrayList<>();
		for (CityUV cityUV : repository.findAllById(ids)) {
			resultArrayList.add(cityUV.getRecord());
		}
		return resultArrayList;
	}

	public void update(LinkedHashSet<CityUV> collection) {
		collection.stream()
		.forEach((c)-> this.update(c));
	}
	
	public void update(CityUV cityUV) {
		Optional<CityUV> toUpdate = repository.findById(cityUV.getId());
		if(toUpdate.isPresent()) {
			repository.save(toUpdate.get());
		}
	}

}
