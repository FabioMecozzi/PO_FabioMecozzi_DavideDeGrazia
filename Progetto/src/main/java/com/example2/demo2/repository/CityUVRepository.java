package com.example2.demo2.repository;

import org.springframework.transaction.annotation.Transactional;

import com.example2.demo2.model.CityUV;

@Transactional
public interface CityUVRepository extends CityRepository<CityUV>{
	
}
