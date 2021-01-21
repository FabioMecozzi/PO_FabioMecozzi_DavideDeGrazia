package com.example2.demo2.utils;

/**
 * Classe che presenta metodi statici utili per la gestione di oggetti di classi che estendono number
 * @author Fabio Mecozzi & Davide De Grazia
 *
 */
public class NumberUtils {
	/**
	 * Metodo che permette di verificare se una strigna rappresenta un numero di tipo double
	 * @param str stringa per cui si vuole testare se contiene un numero o meno 
	 * @return vero se la stringa contine un numero di tipo double, falso altrimenti
	 */
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
