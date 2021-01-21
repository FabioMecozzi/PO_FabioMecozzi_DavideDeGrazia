package com.example2.demo2.utils;

import java.util.Collection;

import com.example2.demo2.model.CityUV;
import com.example2.demo2.model.StatsImpl;

/**
 * Classe con metodi statici utili per il calcolo delle statistiche
 * @author meefa
 *
 */
public class StatsCalculator {
	/**
	 * Classe che calcola le statistiche a partire da una collezione di CityUV
	 * @param collection collezione della quale si vogliono calcolare le statistiche
	 * @return Oggetto contenente le statistiche per la classe
	 */
	public static StatsImpl CalculateStats(Collection<CityUV> collection) {
		int citiesProcessed= collection.size();
		if (citiesProcessed==0) {
			throw new  IllegalArgumentException("La collection di città da analizzare non può essere vuota");
		}
		double max=-1;
		double min=1000000;
		double mean=0;
		double variance=0;
		int valuesProcessed=0;
		
		for (CityUV cityUV : collection) {
			valuesProcessed+=cityUV.getRecord().size();
			for (Double d : cityUV.getRecord().values()) {
				if (d<min) {
					min=d;
				}
				if (d>max) {
					max=d;
				}
				mean+=d;
				
			}
		}
		try {
			mean/=valuesProcessed;
		} catch (ArithmeticException e) {
			throw new IllegalArgumentException("I record delle città da analizzare non possono essere tutti contemporeanamente vuoti");
		}
		
		
		for (CityUV cityUV : collection) {
			for (Double d : cityUV.getRecord().values()) {
				variance+=Math.pow(d-mean, 2);
			}
		}
		variance/=valuesProcessed;
		return new StatsImpl(max, min, mean, variance, valuesProcessed, citiesProcessed);
	}
	
}
