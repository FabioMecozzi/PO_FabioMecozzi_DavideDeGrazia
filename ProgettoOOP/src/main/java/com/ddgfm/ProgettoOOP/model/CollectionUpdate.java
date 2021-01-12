package com.ddgfm.ProgettoOOP.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 * Classe rappresenta l'oggetto che l'admin inserisce per modificare la
 * CityRepository
 */
public class CollectionUpdate<ID> implements FromJson {

	/**
	 * Construttore
	 */
	public CollectionUpdate(boolean deleteByIdsAll, List<ID> addingList, List<ID> deletingList) {

	}

	/**
	 * Costruttore da file json
	 */
	public CollectionUpdate(File json) {
		JSONtoJava(json);
	}

	/**
	 * Indica se si vuole cancellare l'intera repository per aggiungere una nuova
	 * lista di città da 0 Se true deleting list deve essere NULL
	 */
	private boolean delteAll = false;

	/**
	 * Lista degli id delle città da cancellare Se NULL deleteByIdsAll deve essere
	 * true
	 */
	public List<ID> deletingIDList = null;

	/**
	 * Lista di città da salvare Se si inserisce una città con lo stesso id di una
	 * città esistente la città presente nella repository verrà sostituita con la
	 * nuova città in savingList
	 */
	public List<ID> savingList = null;

	public boolean isDelteAll() {
		return delteAll;
	}

	public void setDelteAll(boolean delteAll) {
		this.delteAll = delteAll;
	}

	public List<ID> getDeletingIDList() {
		return deletingIDList;
	}

	public void setDeletingIDList(List<ID> deletingIDList) {
		this.deletingIDList = deletingIDList;
	}

	public List<ID> getSavingList() {
		return savingList;
	}

	public void setSavingList(List<ID> savingList) {
		this.savingList = savingList;
	}

	/**
	 * Funzione che popola i tre attributi della classe
	 * 
	 * @param File json File contenente gli attributi per l'oggetto
	 */
	@SuppressWarnings("deprecation")
	public void JSONtoJava(File json) {
		  JSONParser parser = new JSONParser();
		  Object obj = null;
			
				try {
					obj = parser.parse(new FileReader(json));
				} catch (FileNotFoundException | ParseException e) {
					
					e.printStackTrace();
				}
			
				JSONObject jsonObject = (JSONObject) obj;
				
				
				
	}

}