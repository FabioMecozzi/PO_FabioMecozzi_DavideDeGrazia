package com.example2.demo2.model;

import java.io.File;
import java.util.List;

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
	public void JSONtoJava(File json) {
		// TODO implement here
	}

}