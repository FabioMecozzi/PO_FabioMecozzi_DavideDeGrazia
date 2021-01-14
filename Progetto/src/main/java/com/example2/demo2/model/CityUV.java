package com.example2.demo2.model;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example2.demo2.exception.ForecastNotRegisteredException;

import javassist.NotFoundException;

/**
 * Classe che rappresenta una città a cui è legato uno storico di valori di uv
 * 
 * @author meefa
 *
 */

@Entity
@Table(name = "cityUVs")
public class CityUV extends City implements FromJson {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Indica se la città deve essere monitorata
	 */
	boolean monitored = false;
	LocalDate forecastDate;

	LocalDate getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(LocalDate forecastDate) {
		this.forecastDate = forecastDate;
	}

	public void setGoodForecast(int goodForecast) {
		this.goodForecast = goodForecast;
	}

	public void setTotalForecast(int totalForecast) {
		this.totalForecast = totalForecast;
	}

	/**
	 * Map che contiene i dati dei raggi uv legati al giorno
	 */
	private LinkedHashMap<LocalDate, Double> record;

	private int goodForecast;
	private int totalForecast;
	private double accouracy = -1.0;

	public CityUV(Long ID, String name, String Lon, String Lat) {
		super(ID, name, Lon, Lat);
	}

	public CityUV(City city) {
		super(city.getId(), city.getName(), city.getLon(), city.getLat());
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
	 * this.dataeora = (LocalDate) obj.get("date_iso"); this.uVvalue = (long)
	 * obj.get("value"); } catch (ParseException e) { e.printStackTrace();
	 * 
	 * }
	 * 
	 * }
	 */

	public LinkedHashMap<LocalDate, Double> getRecord() {
		return record;
	}

	public void setRecord(LinkedHashMap<LocalDate, Double> record) {
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
	public void updateRecord(double todayValue, double tomorrowValue, double trasholdError)
			throws NotFoundException, IllegalArgumentException, ForecastNotRegisteredException {
		LocalDate today= LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		this.record.put(tomorrow, tomorrowValue);
		if(this.forecastDate.equals(today)) {
			try {
			verifyTodayForecast(todayValue, trasholdError);
		} catch (Exception e) {
			throw e;
		} finally {
			this.forecastDate = tomorrow;
		}
		} else {record.put(today, todayValue);}
		
		
	}

	/**
	 * 
	 * @param newCity CityUV contenente i valori da unire con quelli già presenti, deve contenere il valore di oggi e una PREVISIONE per domani e deve essere monitored
	 */
	public void updateRecord(CityUV newCityUV, double trasholdError)
			throws NotFoundException, IllegalArgumentException, ForecastNotRegisteredException {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		
		try {
			if (newCityUV.getRecord().containsKey(today) && newCityUV.getRecord().containsKey(tomorrow)) {
				try {
					updateRecord(newCityUV.getRecord().get(today), newCityUV.getRecord().get(tomorrow), trasholdError);
				} catch (Exception e) {
					throw e;
				}
			} else {
				throw new IllegalArgumentException(
						"La città non contiene uno tra il valore di oggi o la previsione di domani");
			}
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("La città \""+ this.getName()+  "\" deve essere monitored per essere aggiornata");
		}
	}

	public boolean verifyTodayForecast(double actualValue, double trasholdError)
			throws NotFoundException, IllegalArgumentException, ForecastNotRegisteredException {
		LocalDate today = LocalDate.now();
		boolean result = false;
		if (record.containsKey(today) && today.equals(forecastDate)) {
			try {
				this.totalForecast++;
				if (valueIsGood(today, actualValue, trasholdError) == true) {
					this.goodForecast++;
					this.accouracy = this.goodForecast / this.totalForecast;
					result= true;
				}
				record.put(today, actualValue);
				this.accouracy = this.goodForecast / this.totalForecast;
				return result;
			} catch (Exception e) {
				throw e;
			}
		} else {
			if (!today.equals(forecastDate)) {
				throw new ForecastNotRegisteredException("La data di oggi non contiene una previsione");
			}
			throw new NotFoundException("Il registro non contiene il valore di oggi");
		}
	}

	private boolean valueIsGood(LocalDate day, double actualValue, double trasholdError)
			throws IllegalArgumentException {
		if (trasholdError == 0) {
			throw new IllegalArgumentException("trasholdError deve essere maggiore di 0");
		}
		return ((Math.abs(this.record.get(day) - actualValue) / actualValue) <= trasholdError);
	}

	// TODO: da implementare (per tests)
	@Override
	public String toString() {
		String resultString = super.toString() + " monitored: " + monitored + " goodForecast: " + goodForecast
				+ " totalForecast: " + totalForecast + " accouracy: " + accouracy + " record: ";
		try {
			resultString += record.toString();
		} catch (NullPointerException e) {
			resultString += "null";
		}
		return resultString;
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof CityUV) {
			CityUV cityUV = (CityUV) o;
			try {
				if (cityUV.getId() == this.getId() && cityUV.getAccouracy() == this.accouracy
						&& cityUV.getName().equals(this.getName()) && cityUV.getLon().equals(this.getLon())
						&& cityUV.getLat().equals(this.getLon()) && cityUV.getGoodForecast() == this.getGoodForecast()
						&& cityUV.getTotalForecast() == this.getTotalForecast()
						&& cityUV.getRecord() == this.getRecord()) {result = true;}
			} catch (NullPointerException e) {
				int exceptionCounter=0;
					try {
						cityUV.getRecord();
					} catch (Exception e2) {
						exceptionCounter++;
					}
					try {
						this.getRecord();
					} catch (Exception e2) {
						exceptionCounter++;
					}
					if(exceptionCounter==2) {result=true;}
			}		
		}
		return result;

	}
}
