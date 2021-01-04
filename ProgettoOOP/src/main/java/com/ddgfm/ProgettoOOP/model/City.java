package com.ddgfm.ProgettoOOP.model;

import java.io.File;
import java.util.Date;

//Questa classe rappresenta il modello dei dati contenuti nell'archivio.
//Ci sta solo il problema che bisogna passare per parametri le coordinate,
//mentre sarebbe preciso se passassimo il nome della città

public class City implements FromJson{

	private String cityname;
	private Date dataeora;

	public City() {
		super();
	}

	public City(String cityname, Date dataeora) {
		super();
		this.cityname = cityname;
		this.dataeora = dataeora;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public Date getDataeora() {
		return dataeora;
	}
	
	protected class coord{
		public double lat;
		public double lon;
	}
	
	@Override
	public void JSONtoJava(File json) {
		//da implementare
	}
}