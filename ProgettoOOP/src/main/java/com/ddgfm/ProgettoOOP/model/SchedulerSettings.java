package com.ddgfm.ProgettoOOP.model;

import java.io.File;
import java.sql.Time;
import java.util.Collection;

/**
 * Classe che rappresenta le impostazioni di un oggetto della classe Scheduler
 * 
 * @author meefa
 *
 */
public class SchedulerSettings implements FromJson {
	private Collection<City> cityCollection;
	private Time sampleIstant;
	private double trasholdError;

	public Collection<City> getCityCollection() {
		return cityCollection;
	}

	public void setCityCollection(Collection<City> cityCollection) {
		this.cityCollection = cityCollection;
	}

	public Time getSampleIstant() {
		return sampleIstant;
	}

	public void setSampleIstant(Time sampleIstant) {
		this.sampleIstant = sampleIstant;
	}

	public double getTrasholdError() {
		return trasholdError;
	}

	public void setTrasholdError(double trasholdError) {
		this.trasholdError = trasholdError;
	}

	public SchedulerSettings(Collection<City> cityCollection, Time sampleIstant, double trasholdError) {
		super();
		this.cityCollection = cityCollection;
		this.sampleIstant = sampleIstant;
		this.trasholdError = trasholdError;
	}

	@Override
	public void JSONtoJava(File json) {
		// da implementare
	}

}
