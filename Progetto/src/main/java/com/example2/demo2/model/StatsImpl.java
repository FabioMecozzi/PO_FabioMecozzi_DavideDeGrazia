package com.example2.demo2.model;

/**
 * classe che rappresenta una statistica effettuata su un set di CityUV
 * 
 * @author Fabio Mecozzi & Davide De Grazia
 *
 */

public class StatsImpl {
	/**
	 * Valore massimo contenuto nella collezione
	 */
	private double max;
	/**
	 * valore minimo contenuto nella collezione
	 */
	private double min;
	/**
	 * Media dei valori nella collezione
	 */
	private double mean;
	/**
	 * varianza dei valori nella collezione
	 */
	private double variance;
	/**
	 * Totale dei valori processati, se uguale a -1 il dato è mancante
	 */
	private int valuesProcessed = -1;
	/**
	 * Totale delle città processate, se uguale a -1 il dato è mancante
	 */
	private int citiesProcessed = -1;

	/**
	 * Costruttore
	 * 
	 * @param max      valore massimo dei dati analizzati
	 * @param min      valore minimo dei dati analizzati
	 * @param mean     media dei dati analizzati
	 * @param variance varianza dei dati analizzati
	 */
	public StatsImpl(double max, double min, double mean, double variance) {
		super();
		this.max = max;
		this.min = min;
		this.mean = mean;
		this.variance = variance;
	}

	/**
	 * Costruttore
	 * 
	 * @param max2            valore massimo dei dati analizzati
	 * @param min2            valore minimo dei dati analizzati
	 * @param mean2           media dei dati analizzati
	 * @param variance2       varianza dei dati analizzati
	 * @param valuesProcessed totale dei dati analizzati
	 * @param citiesProcessed totale delle città analizzate
	 */
	public StatsImpl(double max2, double min2, double mean2, double variance2, int valuesProcessed,
			int citiesProcessed) {
		super();
		this.max = max2;
		this.min = min2;
		this.mean = mean2;
		this.variance = variance2;
		this.valuesProcessed = valuesProcessed;
		this.citiesProcessed = citiesProcessed;
	}

	/**
	 * @return il valore massimo
	 */
	public double getMax() {
		return max;
	}

	/**
	 * @param max il valore massimo
	 */
	public void setMax(double max) {
		this.max = max;
	}

	/**
	 * 
	 * @return il valore minimo
	 */
	public double getMin() {
		return min;
	}

	/**
	 * @param min il valore minimo
	 */
	public void setMin(double min) {
		this.min = min;
	}

	/**
	 * @return al media
	 */
	public double getMean() {
		return mean;
	}

	/**
	 * @param mean la media
	 */
	public void setMean(double mean) {
		this.mean = mean;
	}

	/**
	 * @return la varianza
	 */
	public double getVariance() {
		return variance;
	}

	/**
	 * @param variance la varianza
	 */
	public void setVariance(double variance) {
		this.variance = variance;
	}

	/**
	 * @return il numero dei valori processati
	 */
	int getValuesProcessed() {
		return valuesProcessed;
	}

	/**
	 * 
	 * @param valuesProcessed il numero dei valori processati
	 */
	void setValuesProcessed(int valuesProcessed) {
		this.valuesProcessed = valuesProcessed;
	}

	/**
	 * @return il numero delle città processate
	 */
	double getCitiesProcessed() {
		return citiesProcessed;
	}

	/**
	 * @param citiesProcessed il numero delle città processate
	 */
	void setCitiesProcessed(int citiesProcessed) {
		this.citiesProcessed = citiesProcessed;
	}
}
