package com.example2.demo2.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example2.demo2.exception.ForecastNotRegisteredException;

import javassist.NotFoundException;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

/**
 * Classe che rappresenta una città a cui è legato uno storico di valori di uv,
 * in cui puuò anche essere registrata una previsione e che gestisce inoltre la
 * statistica sulla precisione delle previsioni relative alla città stessa
 * 
 * @author Fabio Mecozzi & Davide De Grazia
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
	LocalDate forecastDate = null;

	/**
	 * @return la relativa all'ultima previsione UV
	 */
	LocalDate getForecastDate() {
		return forecastDate;
	}

	/**
	 * 
	 * @param forecastDate la data dell'ultima previsione
	 */
	public void setForecastDate(LocalDate forecastDate) {
		this.forecastDate = forecastDate;
	}

	/**
	 * 
	 * @param goodForecast la data dell'ultima previsione uv
	 */
	public void setGoodForecast(int goodForecast) {
		this.goodForecast = goodForecast;
	}

	public void setTotalForecast(int totalForecast) {
		this.totalForecast = totalForecast;
	}

	/**
	 * LinkedHashMap che contiene i dati dei raggi uv legati al giorno
	 */
	private LinkedHashMap<LocalDate, Double> record = new LinkedHashMap<LocalDate, Double>();;

	/**
	 * Numero di previsioni corrette per la città
	 */
	private int goodForecast;
	/**
	 * Numero di previsioni totali per la città
	 */
	private int totalForecast;
	/**
	 * Precisione delle previsioni per la città in questione, non calcolata se
	 * uguale a -1.0
	 */
	private double accouracy = -1.0;

	/** 
	 * Costruttore con parametri
	 * @param id ID della città
	 * @param name nome della città
	 * @param Lon longitudine della città
	 * @param Lat latitudine della città
	 * @throws NumberFormatException se l'id inserito è negativo o se lot o lat nonrappresentano un numero di tipo double
	 */
	public CityUV(Long id, String name, String Lon, String Lat) throws NumberFormatException{
		super(id, name, Lon, Lat);
	}
	/**
	 * Costruttore attraverso oggetto della superclasse
	 * @param city oggetto con il quale vengono inizializzati i campi ereditati dalla superclasse City
	 */
	public CityUV(City city) {
		super(city.getId(), city.getName(), city.getLon(), city.getLat());
	}

	/**
	 * @return l'id della città
	 */
	@Id
	public long getId() {
		return super.getId();
	}
	/**
	 * @return La precisione delle previsioni relative alla città in questione
	 */
	public double getAccouracy() {
		return accouracy;
	}
	/**
	 * @return il numero delle previsioni che sono risultate giuste secondo l'aggiornamento nel tempo di questa classe
	 */
	public int getGoodForecast() {
		return goodForecast;
	}
	/**
	 * @return il numero totale delle previsioni veerificate per questa classe
	 */
	public int getTotalForecast() {
		return totalForecast;
	}
	/**
	 * @return vero se i dati di questa classe devono essere monitorati, falso altrimenti
	 */
	public boolean isMonitored() {
		return monitored;
	}

	/**
	 * Metodo che parametrizza alcuni campi dell'oggetto effettuando il parsing di
	 * un file
	 */
	@Override
	public void JSONtoJava(File json) {

		LocalDate dataeora;
		Double uvvalue;

		@SuppressWarnings("deprecation")
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(json));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (net.minidev.json.parser.ParseException e) {

			e.printStackTrace();
		}
		JSONObject jsonObject = (JSONObject) obj;

		dataeora = (LocalDate) jsonObject.get("date_iso");
		uvvalue = (Double) jsonObject.get("value");

		this.record.put(dataeora, uvvalue);

	}
	/**
	 * @param accouracy la precisione delle previsioni relative alla città
	 */
	public void setAccouracy(double accouracy) {
		this.accouracy = accouracy;
	}
	/**
	 * @param monitored vero se la città in questione deve essere monitorata
	 */
	public void setMonitored(boolean monitored) {
		if (monitored == true && record == null) {
			record = new LinkedHashMap<>();
		}
		this.monitored = monitored;
	}
	/**
	 * @return LinkedHashMap che contiene i dati dei raggi uv legati al giorno
	 */
	public LinkedHashMap<LocalDate, Double> getRecord() {
		return record;
	}
	/**
	 * @param record LinkedHashMap che contiene i dati dei raggi uv legati al giorno
	 */
	public void setRecord(LinkedHashMap<LocalDate, Double> record) {
		this.record = record;
	}

	/**
	 * metodo che si occupa di aggiornare i valori del record
	 * 
	 * @param todayValue       valore dei raggi uv di oggi
	 * @param tomorrowForecast valore della previsione del valore dei raggi uv di
	 *                         domani
	 * @param trasholdError    percentuale di tolleranza per la quale il valore
	 *                         viene accetttato come corretto (deve essere diverso
	 *                         da 0)
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
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			record.put(today, todayValue);
		} finally {
			this.forecastDate = tomorrow;
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
		if (record.containsKey(today) && getForecastDate() != null && today.equals(getForecastDate())) {

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
			if (getForecastDate() == null || !today.equals(getForecastDate())) {
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
