package com.ddgfm.ProgettoOOP.model;

import java.io.File;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javassist.NotFoundException;

/**
 * Classe che rappresenta una città a cui è legato uno storico di valori di uv
 * 
 * @author meefa
 *
 */

@Entity
@Table(name="cityUVs")
public class CityUV extends City implements FromJson {

	/**
	 * Indica se la città deve essere monitorata
	 */
	boolean monitored = false;
	
	void setGoodForecast(int goodForecast) {
		this.goodForecast = goodForecast;
	}
	void setTotalForecast(int totalForecast) {
		this.totalForecast = totalForecast;
	}

	/**
	 * Map che contiene i dati dei raggi uv legati al giorno
	 */
	private LinkedHashMap<Date, Double> record;

	private int goodForecast;
	private int totalForecast;
	private double accouracy = -1.0;

	public CityUV(Long ID,String name, String Lon, String Lat) {
		super(ID, name, Lon, Lat);
	}
	public CityUV(City city) {
		super(city.getId(), city.getName(), city.getLon(), city.getLat() );
	}
	
	@Id
	public long getId() {
		return super.getId();
	}

	public double getAccouracy() {
		return accouracy;
	}

	public int getGoodForecast() {
		return goodForecast;
	}

	public int getTotalForecast() {
		return totalForecast;
	}

	public boolean isMonitored() {

		return monitored;
	}

	@Override
	public void JSONtoJava(File json) {
		// TODO Auto-generated method stub

	}

	public void setAccouracy(double accouracy) {
		this.accouracy = accouracy;
	}


	public void setMonitored(boolean monitored) {
		if (monitored == true && record == null) {
			record = new LinkedHashMap<>();
		}
		this.monitored = monitored;
	}


	/*
	 * CityUV(File json){ JSONtoJava(json); } //funzione che bisogna
	 * implementare---->
	 * 
	 * @Override public void JSONtoJava(File json) {
	 * 
	 * @SuppressWarnings("deprecation") JSONParser parser = new JSONParser();
	 * JSONObject obj = null; RestTemplate restTemplate = new RestTemplate(); String
	 * apirisultato =
	 * restTemplate.getForObject("http://api.openweathermap.org/data/2.5/uvi?lat=" +
	 * this.lat + "&lon=" + this.lon + "&appid=58b2755bcc79abb83cce4474b0a795b8",
	 * String.class);
	 * 
	 * System.out.println(apirisultato);
	 * 
	 * try { obj = (JSONObject) parser.parse(apirisultato); // manca il passaggio da
	 * nome a coordinate e da coordinate a nome
	 * 
	 * this.dataeora = (Date) obj.get("date_iso"); this.uVvalue = (long)
	 * obj.get("value"); } catch (ParseException e) { e.printStackTrace();
	 * 
	 * }
	 * 
	 * }
	 */

	LinkedHashMap<Date, Double> getRecord() {
		return record;
	}

	public void setRecord(LinkedHashMap<Date, Double> record) {
		this.record = record;
	}

	/**
	 * 
	 * @param todayValue    valore dei raggi uv di oggi
	 * @param tomorrowValue valore della previsione del valore dei raggi uv di
	 *                      domani
	 * @param trasholdError perc
	 * @throws NotFoundException
	 */
	public void updateRecord(double todayValue, double tomorrowValue, double trasholdError) throws NotFoundException, IllegalArgumentException {
		Calendar c = Calendar.getInstance();
		c.setTime(java.sql.Date.valueOf(LocalDate.now()));
		c.add(Calendar.DATE, 1);
		Date tomorrow = c.getTime();
		this.record.put(tomorrow, todayValue);
		try {
			veriryTodayForecast(todayValue, trasholdError);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param newCity CityUV contenente i valori da unire con quelli già presenti
	 */
	public void updateRecord(CityUV newCityUV, double trasholdError) {
		Date today = java.sql.Date.valueOf(LocalDate.now());
		if(newCityUV.getRecord().containsKey(today)) {
			try {
				////////da implementare
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public boolean veriryTodayForecast(double actualValue, double trasholdError)
			throws NotFoundException, IllegalArgumentException {
		Date today = java.sql.Date.valueOf(LocalDate.now());

		if (record.containsKey(today)) {
			try {
				this.totalForecast++;
				if (valueIsGood(today, actualValue, trasholdError) == true) {
					this.goodForecast++;
					this.accouracy = this.goodForecast / this.totalForecast;
					return true;
				}
				this.accouracy = this.goodForecast / this.totalForecast;
				return false;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new NotFoundException("Il registro non contiene il valore di oggi");
		}
	}

	private boolean valueIsGood(Date day, double actualValue, double trasholdError) throws IllegalArgumentException {
		if (trasholdError == 0) {
			throw new IllegalArgumentException("trasholdError deve essere maggiore di 0");
		}
		return ((Math.abs(this.record.get(day) - actualValue) / actualValue) <= trasholdError);
	}
	
	//TODO: da implementare (per tests)
	@Override
	public String toString() {
		String resultString= super.toString()+" monitored: "+ monitored+ " goodForecast: "+ goodForecast + " totalForecast: "+ totalForecast + " accouracy: "+ accouracy + " record: ";
		try {
			resultString+= record.toString();
		} catch (NullPointerException e) {
			resultString+= "null";
		}
		return resultString;
	}
}
