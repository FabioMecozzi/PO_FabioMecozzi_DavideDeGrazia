package com.ddgfm.ProgettoOOP.model;

import java.io.File;
import java.util.Date;

//Questa classe rappresenta il modello dei dati contenuti nell'archivio.
//Ci sta solo il problema che bisogna passare per parametri le coordinate,
//mentre sarebbe preciso se passassimo il nome della città

public class City implements FromJson{

	private String name;
	private  Coord coord;
	private String id;

	public City() {
		super();
	}

	public City(String name, Coord coord) {
		this.name = name;
		this.coord = coord;
	}
	
	public City(File json) {
		JSONtoJava(json);
	}
	
	protected class Coord{
		public double lat;
		public double lon;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void JSONtoJava(File json) {
		//da implementare
	}
}