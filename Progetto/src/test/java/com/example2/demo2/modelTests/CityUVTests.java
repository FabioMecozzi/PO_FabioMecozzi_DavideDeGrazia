package com.example2.demo2.modelTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example2.demo2.model.City;
import com.example2.demo2.model.CityUV;


class CityUVTests {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Costruttore")
	void test() {
		CityUV citta = new CityUV(668737L, "Roma", "47.83328", "26.6");
		CityUV citta2 = new CityUV((City)citta);
		assertTrue(citta.equals(citta2));
		
	}
	
	@Test
	@DisplayName("update (previsione sbagliata)")
	void test2() {
		LinkedHashMap<LocalDate, Double> datiOggi = new LinkedHashMap<>(); // deve contenere il dato di oggi e la previsione di domani
		LinkedHashMap<LocalDate, Double> datiDomani = new LinkedHashMap<>();
		LocalDate oggi = LocalDate.now().plusDays(-1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		System.out.println(oggi.format(formatter));
		LocalDate domani= oggi.plusDays(1);
		
		datiOggi.put(oggi, 67.864);
		datiOggi.put(domani, 77.3);
		CityUV roma = new CityUV(668737L, "Roma", "47.83328", "26.6");
		CityUV romaCampionata = new CityUV(668737L, "Roma", "47.83328", "26.6");
		roma.setMonitored(true);
		romaCampionata.setMonitored(true);
		
		
		
		
		try {
			roma.setRecord(datiOggi);
			roma.setForecastDate(domani);
			
			oggi=domani; //il giorno dopo
			domani=domani.plusDays(1);
			datiDomani.put(oggi, 60.1); // la previsione era SBAGLIATA
			datiDomani.put(domani, 70.2);

			romaCampionata.setRecord(datiDomani);
			roma.updateRecord(romaCampionata, 0.05);
		} catch (Exception e) {
			System.out.println("update (previsione sbagliata): ");
			e.printStackTrace();
		}
		assertEquals(0, roma.getGoodForecast());
		assertEquals(1, roma.getTotalForecast());
		assertTrue(roma.getRecord().containsValue(60.1));
		assertTrue(roma.getRecord().containsValue(70.2));
		
	}
	@Test
	@DisplayName("update (previsione esatta)")
	void test3() {
		LinkedHashMap<LocalDate, Double> datiOggi = new LinkedHashMap<>(); // deve contenere il dato di oggi e la previsione di domani
		LinkedHashMap<LocalDate, Double> datiDomani = new LinkedHashMap<>();
		LocalDate oggi = LocalDate.now().plusDays(-1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		System.out.println(oggi.format(formatter));
		LocalDate domani= oggi.plusDays(1);
		
		datiOggi.put(oggi, 67.864);
		datiOggi.put(domani, 77.3);
		CityUV roma = new CityUV(668737L, "Roma", "47.83328", "26.6");
		CityUV romaCampionata = new CityUV(668737L, "Roma", "47.83328", "26.6");
		roma.setMonitored(true);
		romaCampionata.setMonitored(true);
		
		
		
		
		try {
			roma.setRecord(datiOggi);
			roma.setForecastDate(domani);
			
			oggi=domani; //il giorno dopo
			domani=domani.plusDays(1);
			datiDomani.put(oggi, 77.301); // la previsione era ESATTA
			datiDomani.put(domani, 70.2);

			romaCampionata.setRecord(datiDomani);
			roma.updateRecord(romaCampionata, 0.05);
		} catch (Exception e) {
			System.out.println("update (previsione sbagliata): ");
			e.printStackTrace();
		}
		assertEquals(1, roma.getGoodForecast());
		assertEquals(1, roma.getTotalForecast());
		assertTrue(roma.getRecord().containsValue(77.301));
		assertTrue(roma.getRecord().containsValue(70.2));
	}

}