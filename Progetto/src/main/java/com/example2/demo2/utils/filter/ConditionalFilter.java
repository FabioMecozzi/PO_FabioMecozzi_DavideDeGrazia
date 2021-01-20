package com.example2.demo2.utils.filter;

/**
 * Classe astratta che rappresenta un filtro condizionale
 * @author meefa
 *
 * @param <T>
 */
public abstract class ConditionalFilter<T extends Comparable<T>> implements Filter<T, T> {
	protected static String[] admissibleOperators;
	/**
	 * Campo che contiene l'oggetto da filtrare
	 */
	 protected  T toFilter;
	/**
	 * Valori necessari per il filtraggio
	 */
	protected T[] values;
	/**
	 * Operatore condizionale che descrive il filtro
	 */
	String operator;
	
	
	static String[] getAdmissibleOperators() {
		return admissibleOperators;
	}
	abstract T getToFilter();
	abstract void setToFilter(T toFilter);
	abstract T[] getValues();
	abstract void setValues(T[] values);
	abstract String getOperator();
	abstract void setOperator(String operator);
	
	public abstract boolean filter(T toFilter);
	
}
