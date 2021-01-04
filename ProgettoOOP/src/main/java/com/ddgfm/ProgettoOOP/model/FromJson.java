package com.ddgfm.ProgettoOOP.model;

import java.io.File;

/**
 * interfaccia che viene implementata da tutte le classi model delle quali alcuni attributi possono essere inizializzati/modificati da dei file json
 * @author Fabio Mecozzi & Davide De Grazia
 *
 */
public interface FromJson {
	
	/**
	 * setta alcuni attributi della classe che implementa l'interfaccia
	 * @param json file che contiene i valori degli attributi da settare
	 */
	public void JSONtoJava(File json);
}
