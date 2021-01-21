package com.example2.demo2.exception;

/**
 * Eccezione utilizzata se si cerca un previsione all'interno del database che
 * non è stata registrata
 * 
 * @author meefa
 *
 */
public class ForecastNotRegisteredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForecastNotRegisteredException() {
		super();
	}

	public ForecastNotRegisteredException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ForecastNotRegisteredException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ForecastNotRegisteredException(String arg0) {
		super(arg0);
	}

	public ForecastNotRegisteredException(Throwable arg0) {
		super(arg0);
	}

}
