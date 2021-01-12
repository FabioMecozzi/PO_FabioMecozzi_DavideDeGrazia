package com.example2.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example2.demo2.repository.CityUVRepository;

@Service
public class CityUVService {
	@Autowired
	CityUVRepository repository;
	
	
}
