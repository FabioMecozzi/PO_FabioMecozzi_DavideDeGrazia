package com.example2.demo2.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CityRepository<T extends City> extends CrudRepository<T, Long> {

}
