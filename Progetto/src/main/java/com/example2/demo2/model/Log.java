package com.example2.demo2.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

/**
 * Classe che rappresenta unostorage di informazioni utili per verificare il
 * corretto funzionamento del servizio
 * 
 * @author meefa
 *
 */
@Component
public class Log {
	/**
	 * Registro di alcune eccezioni che sono state verificate durante l'esecuzione del programma
	 */
	private LinkedHashMap<LocalDate, Exception> exceptionLog=  new LinkedHashMap<>();
	/**
	 * 
	 * @param e eccezione da aggiungere al registro delle eccezioni
	 */
	public void addException(Exception e) {
		exceptionLog.put(LocalDate.now(), e);
	}
	/**
	 * @return il registro delle eccezioni
	 */
	public LinkedHashMap<LocalDate, Exception> getExceptionLog() {
		return exceptionLog;
	}

}
