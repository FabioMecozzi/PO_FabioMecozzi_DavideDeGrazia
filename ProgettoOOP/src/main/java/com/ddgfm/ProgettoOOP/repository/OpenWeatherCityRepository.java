package com.ddgfm.ProgettoOOP.repository;

import org.springframework.data.repository.CrudRepository;

import com.ddgfm.ProgettoOOP.model.City;

/*
 * interfaccia che estende CrudRepository<City, Long> per gestire il set delle città che possono essere passate all'api open weather map per ricevere indietro i valori dei raggi uv
 */
public interface OpenWeatherCityRepository extends CrudRepository<City, Long> {

}
