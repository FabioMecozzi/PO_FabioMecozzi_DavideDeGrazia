package com.ddgfm.ProgettoOOP.repository;

import org.springframework.data.repository.CrudRepository;

import com.ddgfm.ProgettoOOP.model.City;

/*
 * Interfaccia che estende CrudRepository<City, Long> di Spring per la gestione dei dati riguardo le città campionabili
 */
public interface CityRepsitory extends CrudRepository<City, Long> {

}
