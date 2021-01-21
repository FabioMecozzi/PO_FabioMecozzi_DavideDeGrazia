package com.example2.demo2.model;

/**
 * Classe che rappresenta le città presenti in /source/resurces/city.list.json
 * @author meefa
 *
 */
public class CityWithCountry extends City {

	/**
	 * Identificativo unico di versione
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Paese di apparteneza della città
	 */
	private String country;
	/**
	 * Costruttore da superclasse
	 */
	public CityWithCountry() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return il paese di appartenenza
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 
	 * @param country il nuovo paese di appartenenza
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	String getLat() {
		return super.getLat();
	}

	public void setLat(String lat) throws NumberFormatException {
		super.setLat(lat);
	}

	String getLon() {
		return super.getLon();
	}

	public void setLon(String lon) throws NumberFormatException{
		super.setLon(lon);
	}

	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
	}

	public long getId() {
		return super.getId();
	}

	public void setId(long id) throws NumberFormatException{
		super.setId(id);
	}


}
