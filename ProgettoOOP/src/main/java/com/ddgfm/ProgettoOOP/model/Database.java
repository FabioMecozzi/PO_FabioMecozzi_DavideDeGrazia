package com.ddgfm.ProgettoOOP.model;

import org.springframework.web.client.HttpClientErrorException.BadRequest;

public class Database {
	public Database() {
		// TODO Auto-generated constructor stub
	}
	public static Settings getSettings(String key) {
		return new Settings();//da implementare
	}
	
	public static void ChangeSettings(Settings newSettings, String key) throws BadRequest {
		
		
		//da implementare
		
	}	
	
}
