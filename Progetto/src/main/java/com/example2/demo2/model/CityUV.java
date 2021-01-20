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
	 * identificativo unico di versione
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Indica se la città è monitorata
	 */
	boolean monitored = false;
	
	/**
	 * Data relativa a una previsione UV
	 */
	LocalDate forecastDate= null;
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
	 * LinkedHashMap che contiene i dati dei raggi uv legati al giorno
	 */
	private LinkedHashMap<LocalDate, Double> record;

	/**
	 * Numero di previsioni corrette per la città
	 */
	private int goodForecast;
	/**
	 * Numero di previsioni totali per la città
	 */
	private int totalForecast;
	/**
	 * Precisione delle previsioni per la città
	 */
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

	/**
	 * Metodo che parametrizza alcuni valori dell'oggetto effettuando il parsing di
	 * un file
	 */
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

	public LinkedHashMap<LocalDate, Double> getRecord() {
		return record;
	}

	public void setRecord(LinkedHashMap<LocalDate, Double> record) {
		this.record = record;
	}

	/**
	 * metodo che si occupa di aggiornare i valori del record
	 * 
	 * @param todayValue    valore dei raggi uv di oggi
	 * @param tomorrowForecast valore della previsione del valore dei raggi uv di
	 *                      domani
	 * @param trasholdError percentuale di tolleranza per la quale il valore viene
	 *                      accetttato come corretto (deve essere diverso da 0)
	 * @throws IllegalArgumentException       se viene inserito un tresholdError
	 *                                        uguale a zero
	 * @throws ForecastNotRegisteredException se il registro non contiene nessun
	 *                                        valore registrato per oggi
	 */
	public void updateRecord(double todayValue, double tomorrowForecast, double trasholdError)
			throws IllegalArgumentException, ForecastNotRegisteredException {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		this.record.put(tomorrow, tomorrowForecast);
		try {
			verifyTodayForecast(todayValue, trasholdError);
		}catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			record.put(today, todayValue);
		} finally {
			this.forecastDate = tomorrow ;
		}

	}

	/**
	 * metodo che si occupa di aggiornare i valori del record
	 * 
	 * @param newCity CityUV contenente i valori da unire con quelli già presenti,
	 *                deve contenere il valore di oggi e una PREVISIONE per domani e
	 *                deve essere monitored
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
			throw new IllegalArgumentException(
					"La città \"" + this.getName() + "\" deve essere monitored per essere aggiornata");
		}
	}

	public boolean verifyTodayForecast(double actualValue, double trasholdError)
			throws NotFoundException, IllegalArgumentException, ForecastNotRegisteredException {
		LocalDate today = LocalDate.now();
		boolean result = false;
		if (record.containsKey(today) && getForecastDate()!=null  && today.equals(getForecastDate())) {
			
			try {
				if (valueIsGood(today, actualValue, trasholdError) == true) {
					
					this.goodForecast++;
					this.accouracy = this.goodForecast / this.totalForecast;
					result = true;
				}
				this.totalForecast++;
				record.put(today, actualValue);
				this.accouracy = this.goodForecast / this.totalForecast;
				return result;
			} catch (Exception e) {
				throw e;
			}
		} else {
			if (getForecastDate()==null || !today.equals(getForecastDate())) {
				throw new ForecastNotRegisteredException("La data di oggi non contiene una previsione");
			}
			throw new NotFoundException("Il registro non contiene il valore di oggi");
		}
	}
	/**
	 * 
	 * @param day 
	 * @param actualValue
	 * @param trasholdError
	 * @return se il valore è distante in percentuale per meno del trasholdError
	 * @throws IllegalArgumentException
	 */
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
						&& cityUV.getRecord() == this.getRecord()) {
					result = true;
				}
			} catch (NullPointerException e) {
				int exceptionCounter = 0;
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
				if (exceptionCounter == 2) {
					result = true;
				}
			}
		}
		return result;

	}
}
