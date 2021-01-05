package com.ddgfm.ProgettoOOP.model;

public class City {

	private int id;
	private String name;
	
	public City(int id, String name) {
		
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private class Coord {

		private long lat;
		private long lon;

		public Coord(long lat, long lon) {
			super();
			this.lat = lat;
			this.lon = lon;
		}
	
		
		public long getLat() {
			return lat;
		}
		public void setLat(long lat) {
			this.lat = lat;
		}
		public long getLon() {
			return lon;
		}
		public void setLon(long lon) {
			this.lon = lon;
		}
		
	}

}
