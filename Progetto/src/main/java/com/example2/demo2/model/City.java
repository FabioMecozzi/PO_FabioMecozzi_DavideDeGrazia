package com.example2.demo2.model;

import java.io.File;
import java.io.Serializable;

import com.example2.demo2.utils.NumberUtils;

//Questa classe rappresenta il modello dei dati contenuti nell'archivio.
//Ci sta solo il problema che bisogna passare per parametri le coordinate,
//mentre sarebbe preciso se passassimo il nome della città


public abstract class City implements FromJson,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String lat;
	private String lon;
	private long id;

	public City() {
		super();
	}

	public City(long id, String name, String lon, String lat) throws NumberFormatException {
		this(name, lon, lat);
		try {
			setId(id);
		} catch (NumberFormatException e) {
			throw e;
		}
	}

	public City(String name, String lon, String lat) throws NumberFormatException {
		this.name = name;
		try {
			setLat(lat);
			setLon(lon);
		} catch (NumberFormatException e) {
			throw e;
		}

	}

	String getLat() {
		return lat;
	}

	void setLat(String lat) throws NumberFormatException {
		if (NumberUtils.isNumeric(lat)) {
			this.lat = lat;
		} else {
			throw new NumberFormatException();
		}
	}

	String getLon() {
		return lon;
	}

	void setLon(String lon) {
		if (NumberUtils.isNumeric(lon)) {
			this.lon = lon;

		} else {
			throw new NumberFormatException();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if (id > 0) {
			this.id = id;
		} else {
			throw new NumberFormatException("L'id deve essere positivo");
		}
	}

	@Override
	public void JSONtoJava(File json) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return(""+ "ID: "+ this.id+ " nome: "+ this.name+" Longitudine: "+ this.lon +" Latitudine: "+ this.lat);
	}

}
