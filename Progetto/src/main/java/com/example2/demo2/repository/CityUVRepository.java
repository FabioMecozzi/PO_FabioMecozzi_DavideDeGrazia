package com.example2.demo2.repository;

import org.springframework.transaction.annotation.Transactional;

import com.example2.demo2.model.CityUV;

/**
 * Repository dei CityUV
 * @author Fabio Mecozzi & Davide De Grazia
 *
 */
@Transactional
public interface CityUVRepository extends CityRepository<CityUV>{
	
}
