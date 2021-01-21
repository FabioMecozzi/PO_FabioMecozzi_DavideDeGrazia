package com.example2.demo2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example2.demo2.model.City;

/**
 * Repository delle sottoclassi di City, di cui non è inizializzato il bean
 * @author Fabio Mecozzi & Davide De Grazia
 * @param <T> tipo degli oggetti contenuti nell repository
 */
@NoRepositoryBean
public interface CityRepository<T extends City> extends CrudRepository<T, Long> {

}
