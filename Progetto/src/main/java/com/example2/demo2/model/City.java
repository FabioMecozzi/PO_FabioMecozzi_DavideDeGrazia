package com.example2.demo2.model;

import java.io.Serializable;

import com.example2.demo2.utils.NumberUtils;

/**
 * Classe astratta che rappresenta un città senza il paese di appartenenza
 * 
 * @author meefa
 *
 */
public abstract class City implements Serializable {

	/**
	 * Identificativo unico di versione
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * nome della città
	 */
	private String name;
	/**
	 * Latitudine della città fornita da OpenWeatherMap
	 */
	private String lat;
	/**
	 * Longitudine della città fornita da OpenWeatherMap
	 */
	private String lon;
	/**
	 * ID della città fornito da OpenWeatherMap
	 */
	private long id;

	/**
	 * Costruttore
	 */
	public City() {
		super();
	}

	/**
	 * Costruttore con parametri
	 * 
	 * @param id   id della città
	 * @param name nome della città
	 * @param lon  longitudine della città, deve rappresentare un numero di tipo
	 *             double
	 * @param lat  latitudine della città, deve rappresentare un numero di tipo
	 *             double
	 * @throws NumberFormatException se l'id inserito è negativo o se lot o lat non
	 *                               rappresentano un numero di tipo double
	 */
	public City(long id, String name, String lon, String lat) throws NumberFormatException {
		this(name, lon, lat);
		setId(id);
	}

	/**
	 * Costruttore con parametri
	 * 
	 * @param name nome della città
	 * @param lon  longitudine della città, deve rappresentare un numero di tipo
	 *             double
	 * @param lat  latitudine della città, deve rappresentare un numero di tipo
	 *             double
	 * @throws NumberFormatException
	 */
	public City(String name, String lon, String lat) throws NumberFormatException {
		this.name = name;
		try {
			setLat(lat);
			setLon(lon);
		} catch (NumberFormatException e) {
			throw e;
		}

	}

	/**
	 * @return la latitudine
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat latitudine della città, deve rappresentare un numero di tipo
	 *            double
	 * @throws NumberFormatException se il parametro non rappresenta un numero di
	 *                               tipo double
	 */
	public void setLat(String lat) throws NumberFormatException {
		if (NumberUtils.isNumeric(lat)) {
			this.lat = lat;
		} else {
			throw new NumberFormatException();
		}
	}

	/**
	 * @return la Longitudine
	 */
	public String getLon() {
		return lon;
	}

	/**
	 * @param lon longitudine della città, deve rappresentare un numero di tipo
	 *            double
	 * @throws NumberFormatException se il parametro non rappresenta un numero di
	 *                               tipo double
	 */
	public void setLon(String lon) throws NumberFormatException {
		if (NumberUtils.isNumeric(lon)) {
			this.lon = lon;

		} else {
			throw new NumberFormatException();
		}
	}

	/**
	 * 
	 * @return il nome della città
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name il nome della città
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return l'ID della città
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id l'id della città
	 * @throws NumberFormatException se l'id non è positivo
	 */
	public void setId(long id) throws NumberFormatException {
		if (id > 0) {
			this.id = id;
		} else {
			throw new NumberFormatException("L'id deve essere positivo");
		}
	}

	@Override
	public String toString() {
		return ("" + "ID: " + this.id + "Nnome: " + this.name + " Longitudine: " + this.lon + " Latitudine: "
				+ this.lat);
	}

}
