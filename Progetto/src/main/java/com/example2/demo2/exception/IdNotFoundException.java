/**
 * 
 */
package com.example2.demo2.exception;

/**
 * Eccezione personalizzata che indica che un id non è registrato all'interno della repository
 * @author Fabio Mecozzi & Davide De Grazia
 *
 */
public class IdNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IdNotFoundException() {
		super();
	}
	public IdNotFoundException(String message) {
		super(message);
	}

}
