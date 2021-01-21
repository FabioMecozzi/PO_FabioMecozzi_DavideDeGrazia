package com.example2.demo2.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example2.demo2.model.City;
import com.example2.demo2.model.CityWithCountry;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class CityListParser {

	public static HashSet<City> parseIT(File source) {
		Predicate<CityWithCountry> isIT = city -> city.getCountry() == "IT";
		Set<CityWithCountry> filteredCollection = parse(source).stream().filter(isIT).collect(Collectors.toSet());
		HashSet<City> castedCities = new HashSet<City>();
		for (CityWithCountry cityWithCountry : filteredCollection) {
			castedCities.add((City) cityWithCountry);
		}
		return castedCities;
	}

	@SuppressWarnings("deprecation")
	public static ArrayList<CityWithCountry> parse(File source) {

		ArrayList<CityWithCountry> cities = new ArrayList<CityWithCountry>();

		JSONParser parser = new JSONParser();

		JSONObject jsonObject = null;
		try {
			Object obj = parser.parse(new FileReader(source));
			jsonObject = (JSONObject) obj;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}

		JSONArray array = (JSONArray) jsonObject.get("array");

		for (int i = 0; i < array.size(); i++) {
			JSONObject object = (JSONObject) array.get(i);
			CityWithCountry city = new CityWithCountry();
			try {
				Long id = Long.parseLong(object.get("id").toString());
				city.setId(id);
			} catch (NumberFormatException e) {
			}
			city.setName((String) object.get("name"));
			city.setCountry((String) object.get("country"));

			JSONObject coord = (JSONObject) object.get("coord");
			city.setLat(((Double) coord.get("lat")).toString());
			city.setLon(((Double) coord.get("lon")).toString());

			cities.add(city);
		}
		return cities;
	}
}
