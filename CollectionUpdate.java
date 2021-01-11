  package com.ddgfm.ProgettoOOP.model;


	import java.io.File;
	import java.util.*;

	/**
	 * Classe rappresenta l'oggetto che l'admin inserisce per modificare la CityRepository
	 */
	public class CollectionUpdate<T,ID> implements FromJson {

	    /**
	     * Constructore
	     */
	    public CollectionUpdate(boolean deleteAll, List<T> addingList, List<T> deletingList ) {
	    	
	    }  
	    
	    /**
	     * Costruttore da file json
	     */
	    public CollectionUpdate(File json) {
	    	JSONtoJava(json);
	    }

	    /**
	     * Indica se si vuole cancellare l'intera repository per aggiungere una nuova lista di città da 0
	     * Se true deleting list deve essere NULL
	     */
	    private boolean deleteAll = false;

		/**
	     * Lista degli id delle città da cancellare
	     * Se NULL deleteAll deve essere true
	     */
	    public List<ID> deletingIDList = null;

	    
	    /**
	     * Lista di città da salvare
	     * Se si inserisce una città con lo stesso id di una città esistente la città presente nella repository verrà sostituita con la nuova città in savingList
	     */
	    public List<T> savingList = null;

	    public boolean isDeleteAll() {
			return delteAll;
		}

		public void setDeleteAll(boolean delteAll) {
			this.delteAll = delteAll;
		}

		public List<ID> getDeletingIDList() {
			return deletingIDList;
		}

		public void setDeletingIDList(List<ID> deletingIDList) {
			this.deletingIDList = deletingIDList;
		}

		public List<T> getSavingList() {
			return savingList;
		}

		public void setSavingList(List<T> savingList) {
			this.savingList = savingList;
		}

	   /**
	     * Funzione che popola i tre attributi della classe
	     * @param File json File contenente gli attributi per l'oggetto
	     */
	    public void JSONtoJava(File json) {
	    	
	    	JSONParser parser = new JSONParser();
			Object obj = null;
			
			try {
				obj = parser.parse(new FileReader(file));
			} catch (FileNotFoundException | ParseException e) {
				
				e.printStackTrace();
			}
			JSONObject jsonObject = (JSONObject) obj;
				
	            
			
				this.deleteAll = (Boolean) jsonObject.get("");
				@SuppressWarnings("unused")
				JSONObject coord = (JSONObject) jsonObject.get("coord");
				this.country = (String) jsonObject.get("county");
				this.lat = (String) jsonObject.get("lat");
				this.lon = (String) jsonObject.get("lon");
		
	    }


	}

}
