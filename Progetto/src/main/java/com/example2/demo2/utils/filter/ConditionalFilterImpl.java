/**
 * 
 */
package com.example2.demo2.utils.filter;

import java.util.Vector;

/**
 * Classe che rappresenta un singolo filtro caratterizzato da un operatore condizionale applicabile su una città
 * @author Fabio Mecozzi & Davide De Grazia
 *
 * @param <T> Classe che estende Comaparble<T>
 */
public class ConditionalFilterImpl<T extends Comparable<T>> implements Filter<T, T> {
	/**
	 * 
	 * @param operator stringa che descrive l'operatore
	 * @param values   valori che caratterizzano il filtro
	 * @throws IllegalArgumentException se i valori non sono compatibili con il
	 *                                  filtro oppure se la string non corrisponde a
	 *                                  nessun operatore ammissibile
	 */
	public ConditionalFilterImpl(String operator, Vector<T> values) throws IllegalArgumentException {
		if (isAdmissibleOperator(operator))
			this.operator = operator;
		this.setValues(values);
	}

	/**
	 * Array contenente le stringhe che possono essere usate come operatori------
	 * "$lt"-> LowerThen - vedi metodo lt()--------------------------------------
	 * "$lte"-> LowerThen/Equals - vedi metodo lte()-----------------------------
	 * "$eq": Equals - vedi metodo eq()------------------------------------------
	 * "$gt": GreatherThen - vedi metodo gt()------------------------------------
	 * "$gte": GreatherThen - vedi metodo gte()----------------------------------
	 * "$bt": Between - vedi metodo bt()-----------------------------------------
	 * "$not": NotEquals - vedi metodo not()-------------------------------------
	 * "$in"-> In - vedi metodo in()---------------------------------------------
	 * "$nin"-> NotIn - vedi metodo nin()----------------------------------------
	 */
	private final static String[] admissibleOperators = { "$lt", "$lte", "$eq", "$gt", "$gte", "$bt", "$not", "$in",
			"$nin" };
	/**
	 * Campo che contiene l'oggetto da filtrare
	 */
	private T toBeFiltered;
	/**
	 * Valori necessari per il filtraggio
	 */
	private Vector<T> values;
	/**
	 * Operatore condizionale che descrive il filtro
	 */
	String operator;

	/**
	 * 
	 * @return l'oggetto che può essere filtrato attualmente
	 */
	T getToBeFiltered() {
		return toBeFiltered;
	}

	/**
	 * Metodo utile per riutilizzare lo stessso filtro per diversi oggetti
	 * 
	 * @param toBeFiltered l'oggetto da filtrare
	 */
	void setToBeFiltered(T toBeFiltered) {
		this.toBeFiltered = toBeFiltered;
	}

	/**
	 * 
	 * @return i valori che caratterizzano il filtro
	 */
	Vector<T> getValues() {
		return values;
	}

	/**
	 * 
	 * @param values nuovi valori che caratterizzano il filtro
	 * @throws IllegalArgumentException se la dimensione dell'array è minore del
	 *                                  numero minimo di valori richiesti
	 *                                  dall'operatore
	 */
	void setValues(Vector<T> values) throws IllegalArgumentException {
		if (this.values.size() == 0)
			throw new IllegalArgumentException("Il filtro deve contenere dei valori con cui confrontare gli oggetti");
		if (this.operator == "$bt" && values.size() == 1)
			throw new IllegalArgumentException("Per l'operatore \"$bt\" occorrono 2 valori");
		this.values = values;
	}

	/**
	 * 
	 * @return Una stringa che descrive il filtro
	 */
	String getOperator() {
		return operator;
	}

	/**
	 * 
	 * @param operator Stringa che descrive il filtro (deve corispondere ad una
	 *                 delle stringhe contenute in admissibleOperators)
	 * @throws IllegalArgumentException se il nuovo operatore richiede più valori di
	 *                                  quelli attualmente presenti in values
	 */
	void setOperator(String operator) throws IllegalArgumentException {
		if (!isAdmissibleOperator(operator))
			throw new IllegalArgumentException("L'operatore \"" + operator + "\" non è ammissibile");
		if (operator == "$bt" && this.values.size() < 2)
			throw new IllegalArgumentException(
					"L'operatore between richiede almeno due valori con cui confrontare gli oggetti");
		this.operator = operator;
	}
	@Override
	public boolean filter(T toBeFiltered, String operator, Vector<T> values) throws IllegalArgumentException {
		try {
			this.toBeFiltered = toBeFiltered;
			this.values = values;
			switch (operator) {
			case "$lt":
				return lt();
			case "$lte":
				return lte();
			case "$eq":
				return eq();
			case "$gt":
				return gt();
			case "$gte":
				return gte();
			case "$bt":
				return bt();
			case "$not":
				return not();
			case "$in":
				return in();
			case "$nin":
				return nin();
			default:
				throw new IllegalArgumentException("Nessun operatore corrisponde a " + operator);
			}
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("La dimensione dell'array è troppo piccola per l'operatore utilizzato");
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Nessuno degli argomenti può esssere nullo");
		}

	}

	/**
	 * LowerThen
	 * 
	 * @return vero se il valore da filtrare è minore del primo valore in values,
	 *         falso altrimenti
	 */
	private boolean lt() {
		return toBeFiltered.compareTo(values.firstElement()) < 0;
	}

	/**
	 * LowerThen/Equals
	 * 
	 * @return vero se il valore da filtrare è minore o uguale del primo valore in
	 *         values, falso altrimenti
	 */
	private boolean lte() {
		return toBeFiltered.compareTo(values.firstElement()) <= 0;
	}

	/**
	 * equals
	 * 
	 * @return vero se il valore da filtrare è uguale del primo valore in values,
	 *         falso altrimenti
	 */
	private boolean eq() {
		return toBeFiltered.compareTo(values.firstElement()) == 0;
	}

	/**
	 * GreatherThen
	 * 
	 * @return vero se il valore da filtrare è maggiore del primo valore in values,
	 *         falso altrimenti
	 */
	private boolean gt() {
		return toBeFiltered.compareTo(values.firstElement()) > 0;
	}

	/**
	 * GreatherThen/Equals
	 * 
	 * @return vero se il valore da filtrare è maggiore o uguale del primo valore in
	 *         values, falso altrimenti
	 */
	private boolean gte() {
		return toBeFiltered.compareTo(values.firstElement()) >= 0;
	}

	/**
	 * Between
	 * 
	 * @return vero se il valore da filtrare compreso tra i primi due valori in
	 *         values, falso altrimenti
	 */
	private boolean bt() {
		return (gt() && toBeFiltered.compareTo(values.get(1)) < 0);
	}

	/**
	 * NotEquals
	 * 
	 * @return vero se il valore da filtrare è diverso del primo valore in values,
	 *         falso altrimenti
	 */
	private boolean not() {
		return toBeFiltered.compareTo(values.firstElement()) != 0;
	}

	/**
	 * In
	 * 
	 * @return vero se il valore da filtrare contenuto in uno dei valori in values,
	 *         falso altrimenti
	 */
	private boolean in() {
		for (T value : values) {
			if (toBeFiltered.equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * NotIn
	 * 
	 * @return vero se il valore da filtrare non è uguale a nessuno dei valori in
	 *         values, falso altrimenti
	 */
	private boolean nin() {
		for (T value : values) {
			if (toBeFiltered.equals(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Filtra il nuovo oggetto toBeFiltered secondo i valori dei campi contenuti
	 * nell'istanza di questa classe, così da poter riutilizzare lo stesso filtro
	 * per diversi oggetti
	 */
	@Override
	public boolean filter(T toBeFiltered) throws IllegalArgumentException {
		return this.filter(toBeFiltered, this.operator, this.values);
	}

	/**
	 * 
	 * @return un array di valori ammissibili per l'operatore condizionale
	 */
	public static String[] getAdmissibleOperators() {
		return admissibleOperators;
	}

	/**
	 * Metodo per verificare se una stringa è un operatore ammissibile
	 * 
	 * @param operator string da verificare
	 * @return vero se la stringa rappresenta un operatore ammissibile, falso
	 *         altrimenti
	 */
	public static boolean isAdmissibleOperator(String operator) {
		for (String s : ConditionalFilterImpl.getAdmissibleOperators()) {
			if (operator.equals(s))
				return true;
		}
		return false;
	}
}
