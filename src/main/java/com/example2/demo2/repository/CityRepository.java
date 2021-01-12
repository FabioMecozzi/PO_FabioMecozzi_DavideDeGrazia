package com.example2.demo2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example2.demo2.model.City;

@NoRepositoryBean
public interface CityRepository<T extends City> extends CrudRepository<T, Long> {

}
