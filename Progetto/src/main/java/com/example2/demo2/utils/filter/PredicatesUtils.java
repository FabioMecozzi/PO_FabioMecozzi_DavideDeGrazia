package com.example2.demo2.utils.filter;

import java.io.File;
import java.util.function.Predicate;

import com.example2.demo2.model.CityUV;


public class PredicatesUtils {
	
	public static Predicate<CityUV> and(Filter<CityUV,?> p1, Filter<CityUV,?> p2) {
		return x-> p1.filter(x) && p2.filter(x);
	}
	public static Predicate<CityUV> or(Filter<CityUV,?> p1, Filter<CityUV, ?> p2) {
		return x-> p1.filter(x)||p2.filter(x);
	}
	public static Predicate<CityUV> and(Predicate<CityUV> p1, Predicate<CityUV> p2){
		return p1.and(p2);
	}
	public static Predicate<CityUV> or(Predicate<CityUV> p1, Predicate<CityUV> p2){
		return p1.or(p2);
	}
	public static Predicate<CityUV> fromJson(File json) {
		Predicate<CityUV> result= x->x.getAccouracy()>0.5;
		return result;
	}
	

}
