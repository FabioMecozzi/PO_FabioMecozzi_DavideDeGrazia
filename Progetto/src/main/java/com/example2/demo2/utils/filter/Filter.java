package com.example2.demo2.utils.filter;

/**
 * 
 * @author meefa
 *
 * @param <T>
 * @param <V>
 */
public interface Filter<T, V>  {
	
	/**
	 * Metodo astratto che permette di satbilire se l'oggetto comparabile toFilter è nella relazione descritta da operator rispetto ai valori  contenuti in values 
	 * @param toFilter Oggetto da filtrare
	 * @param operator Operatore
	 * @param values Array di valori
	 * @return Vero se l'oggetto rispetta i parametri secondo l'operatore, falso altrimenti
	 * @throws IllegalArgumentException se i parametri non rispettano delle caratteristiche necessarie
	 */
	public boolean filter(T toFilter, String condition,  V[] values) throws IllegalArgumentException;
	

}