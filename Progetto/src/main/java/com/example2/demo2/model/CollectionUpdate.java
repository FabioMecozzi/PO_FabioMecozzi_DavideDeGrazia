package com.example2.demo2.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

/**
 * Classe rappresenta l'oggetto che l'admin inserisce per modificare la
 * CityRepository
 */
public class CollectionUpdate<ID> implements FromJson {

	/**
	 * Construttore
	 */
	public CollectionUpdate(boolean deleteAll, List<ID> savingingList, List<ID> deletingList) {
		this.deletingList=deletingList;
		this.savingList=savingingList;
		this.delteAll=deleteAll;
	}

	/**
	 * Costruttore da file json
	 */
	public CollectionUpdate(File percorso) {
		try {
			JSONtoJava(percorso);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
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
	public List<ID> deletingList = null;

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

	public List<ID> getDeletinList() {
		return deletingList;
	}

	public void setDeletingList(List<ID> deletingIDList) {
		this.deletingList = deletingIDList;
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
	@SuppressWarnings("unchecked")
	public void JSONtoJava(File json) {
		  @SuppressWarnings("deprecation")
		           
		  
		  JSONParser parser = new JSONParser();
		  
		  Object obj = null;
		try {
			obj = parser.parse(new FileReader(json));
		} catch (FileNotFoundException | net.minidev.json.parser.ParseException e) {
	
		}
				
			
				JSONObject jsonObject = (JSONObject) obj;

	            this.delteAll = (Boolean) jsonObject.get("delteAll");
	            System.out.println(delteAll);

	            JSONArray arrayDeletingList = (JSONArray)jsonObject.get("deletingIDList");    
	            for(int i=0; i < arrayDeletingList.size(); i++)  {
	            	deletingList.add((ID) arrayDeletingList.get(i));
	            }
				
	            JSONArray arraySavingList = (JSONArray)jsonObject.get("SavingList");    
	            for(int i=0; i < arraySavingList.size(); i++)  {
	            	deletingList.add((ID) arraySavingList.get(i));
	            } 
	           
				
	}

	}

