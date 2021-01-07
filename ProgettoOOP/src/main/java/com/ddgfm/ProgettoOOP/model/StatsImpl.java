package com.ddgfm.ProgettoOOP.model;

/**
 * classe che rappresenta una statistica effettuata su un set di CityUV
 * 
 * @author Fabio Mecozzi & Davide De Grazia
 *
 */

public class StatsImpl<T, VALUE> {
	private float max;
	private float min;
	private float avarage;
	private float variance;

	/**
	 * Costruttore
	 * 
	 * @param max      valore massimo del set
	 * @param min      valore minimo del set
	 * @param avarage  media del set
	 * @param variance varianza del set
	 */
	public StatsImpl(float max, float min, float avarage, float variance) {
		super();
		this.max = max;
		this.min = min;
		this.avarage = avarage;
		this.variance = variance;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getAvarage() {
		return avarage;
	}

	public void setAvarage(float avarage) {
		this.avarage = avarage;
	}

	public float getVariance() {
		return variance;
	}

	public void setVariance(float variance) {
		this.variance = variance;
	}
}
