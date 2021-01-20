package com.example2.demo2.serviceTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example2.demo2.model.CityUV;
import com.example2.demo2.model.CollectionUpdate;
import com.example2.demo2.repository.CityUVRepository;
import com.example2.demo2.service.CityService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CityUVServiceTests {
	@MockBean
	CityUVRepository repository;
	@Autowired
	CityService service;
	
	ArrayList<Long> arrayId = new ArrayList<>();
	ArrayList<Long> arrayId1 = new ArrayList<>();
	ArrayList<Long> arrayId2 = new ArrayList<>();
	LinkedHashSet<CityUV> arrayCity = new LinkedHashSet<CityUV>();//tutte le città (città di arrayId)
	LinkedHashSet<CityUV> arrayCity1 = new LinkedHashSet<CityUV>();//città di arrayId1
	LinkedHashSet<CityUV> arrayCity2 = new LinkedHashSet<CityUV>();//città di arrayId2
	CollectionUpdate<Long> update;
	CollectionUpdate<Long> addAll;

	@BeforeEach
	void setUp() throws Exception {
		//contiene le prime 3 città
		arrayId1.add(668737L);//Roma
		arrayId1.add(2522685L);//Zeddiani
		arrayId1.add(2523685L);//Zaccanopoli
		
		//contiene le successive 2 città e una non esistente
		arrayId2.add(2522776L);//Vibo Valentia
		arrayId2.add(3167053L);//Sassuolo
		arrayId1.add(1L);//INESISTENTE
		
		//contiene tutte le città
		arrayId.addAll(arrayId1);
		arrayId.addAll(arrayId2);
		arrayId.remove(1L); //rimuovo perchè non è associato a nessuna città
		arrayId.add(3183089L);
		
		CityUV roma = new CityUV(668737L, "Roma", "47.83328", "26.6");
		CityUV zeddiani = new CityUV(2522685L,"Zeddiani", "8.59528", "39.990829");
		CityUV zaccanopoli = new CityUV(2523685L,"Zaccanopoli", "15.92979", "38.66478");
		CityUV viboValentia = new CityUV(2522776L, "Vibo Valentia", "16.0951", "38.674278");
		CityUV sassuolo = new CityUV(3167053L, "Sassuolo", "10.7847", "44.550861");
		CityUV ancona = new CityUV(3183089L, "Ancona", "13.51008", "43.59816");
		arrayCity1.add(roma); arrayCity1.add(zeddiani); arrayCity1.add(zaccanopoli); arrayCity2.add(viboValentia); arrayCity2.add(sassuolo);
		arrayCity.addAll(arrayCity1); arrayCity.addAll(arrayCity2);
		arrayCity.add(ancona);  
		
		addAll= new CollectionUpdate<>(true, arrayId, null);
		update= new CollectionUpdate<>(false, arrayId1, arrayId2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("No exceptions for empty repository")
	void test() {
		when(repository.findAll()).thenReturn(null);
		service.deleteByIds(arrayId1);
		assertTrue(service.getAllMonitored().isEmpty());
		assertTrue(true);
	}

	@Test
	@DisplayName("")
	void test2() {
		when(repository.findAllById(arrayId1)).thenReturn(arrayCity1);
		when(repository.findAllById(arrayId2)).thenReturn(arrayCity2);	
		when(repository.findAll()).thenReturn(arrayCity);
		
		service.updateMonitoredCities(addAll);
		System.out.println('\n'+repository.findAll().toString());
		System.out.println('\n'+"SIZE" + service.getAllMonitored().size());
		assertTrue(service.getAllMonitored().size()==arrayCity.size());
		
		service.updateMonitoredCities(update);
	//	System.out.println(service.getAllMonitored());
		assertEquals(service.getAllMonitored(), arrayId1);
	}

}
