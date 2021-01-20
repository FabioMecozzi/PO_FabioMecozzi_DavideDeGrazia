package com.example2.demo2.serviceTests;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example2.demo2.model.CityUV;
import com.example2.demo2.service.FilterService;

class FilterServiceTests {
	FilterService service;
	ArrayList<Long> arrayId;
	LinkedHashSet<CityUV> arrayCity;
	void setUp() throws Exception {
		service=new FilterService();
		arrayCity = new LinkedHashSet<CityUV>();
		arrayId = new ArrayList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		service=new FilterService();
		arrayCity = new LinkedHashSet<CityUV>();
		CityUV roma = new CityUV(668737L, "Roma", "47.83328", "26.6");
		CityUV zeddiani = new CityUV(2522685L,"Zeddiani", "8.59528", "39.990829");
		CityUV zaccanopoli = new CityUV(2523685L,"Zaccanopoli", "15.92979", "38.66478");
		CityUV viboValentia = new CityUV(2522776L, "Vibo Valentia", "16.0951", "38.674278");
		CityUV sassuolo = new CityUV(3167053L, "Sassuolo", "10.7847", "44.550861");
		CityUV ancona = new CityUV(3183089L, "Ancona", "13.51008", "43.59816");
		
		LocalDate ld1 = LocalDate.now();
		LocalDate ld2 = ld1.minusDays(1);
		LocalDate ld3 = ld1.minusDays(2);
		LocalDate ld4 = ld1.minusDays(3);
		LocalDate ld5 = ld1.minusDays(4);
		LinkedHashMap<LocalDate, Double> recordRoma= new LinkedHashMap<>();
		LinkedHashMap<LocalDate, Double> recordZeddiani= new LinkedHashMap<>();
		LinkedHashMap<LocalDate, Double> recordZaccanopoli= new LinkedHashMap<>();
		LinkedHashMap<LocalDate, Double> recordViboValentia= new LinkedHashMap<>();
		LinkedHashMap<LocalDate, Double> recordSassuolo= new LinkedHashMap<>();
		LinkedHashMap<LocalDate, Double> recordAncona= new LinkedHashMap<>();
		recordRoma.put(ld1, 1.1); recordRoma.put(ld2, 1.2); recordRoma.put(ld3, 1.3); recordRoma.put(ld4, 1.4); recordRoma.put(ld5, 1.5); 
		recordZeddiani.put(ld1, 2.1); recordZeddiani.put(ld2, 2.2); recordZeddiani.put(ld3, 2.3); recordZeddiani.put(ld4, 2.4); recordZeddiani.put(ld5, 2.5);
		recordZaccanopoli.put(ld1, 3.1); recordZaccanopoli.put(ld2, 3.2); recordZaccanopoli.put(ld3, 3.3); recordZaccanopoli.put(ld4, 3.4); recordZaccanopoli.put(ld5, 3.5);
		recordViboValentia.put(ld1, 100.1);
		recordSassuolo.put(ld1, 100.1);
		recordAncona.put(ld1, 100.1);
		roma.setRecord(recordRoma); zeddiani.setRecord(recordZeddiani); zaccanopoli.setRecord(recordZaccanopoli);viboValentia.setRecord(recordViboValentia);sassuolo.setRecord(recordSassuolo);ancona.setRecord(recordAncona);
		roma.setAccouracy(1);
		zeddiani.setAccouracy(0.8);
		zaccanopoli.setAccouracy(0.6);
		viboValentia.setAccouracy(0.4);
		sassuolo.setAccouracy(0.2);
		ancona.setAccouracy(0);
		arrayCity.add(roma); arrayCity.add(zeddiani); arrayCity.add(zaccanopoli); arrayCity.add(viboValentia); arrayCity.add(sassuolo); arrayCity.add(ancona); 
		Set<CityUV> result= service.filterCityUVs(arrayCity, null, 3);
		assertTrue(result.size()==3);
		roma.getRecord().remove(ld4); roma.getRecord().remove(ld5);
		assertTrue(result.contains(roma)); 
		
	}

}
