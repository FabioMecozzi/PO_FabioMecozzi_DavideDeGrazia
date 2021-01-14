package com.example2.demo2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example2.demo2.exception.IdNotFoundException;
import com.example2.demo2.model.CityUV;
import com.example2.demo2.repository.CityUVRepository;

/**
 * Classe che gestisce i record degli UV negli elementi della repository CityUVRepository
 * @author meefa
 *
 */
@Service
public class CityUVService {
	@Autowired
	CityUVRepository repository;
	
	public LinkedHashMap<Date, Double> getRecordById(Long id) throws IdNotFoundException, IllegalArgumentException{
		Optional<CityUV> optionalCity = repository.findById(id);
		if(optionalCity.isPresent()) {
			return optionalCity.get().getRecord();
		} else {throw new IdNotFoundException();}
	}
	
	public ArrayList<LinkedHashMap<Date, Double>> getRecordsByIds(Iterable<Long> ids) throws IllegalArgumentException{
		ArrayList<LinkedHashMap<Date, Double>> resultArrayList =new ArrayList<>();
		for(CityUV cityUV : repository.findAllById(ids)) {
			resultArrayList.add(cityUV.getRecord());
		}
		return resultArrayList;
	}
	
	public void deleteAllDataBefore(Date date){
		
	}
}
