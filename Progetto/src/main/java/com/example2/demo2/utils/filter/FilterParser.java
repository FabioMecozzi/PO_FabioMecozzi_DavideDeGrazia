package com.example2.demo2.utils.filter;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Vector;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


/**
 * 
 * Classe che esegue il parsing dei filtri che vengono passati nel body.
 * @author Fabius Mecozzi & Davide De Grazia 
 * 
 */

public class FilterParser {
	
	/**
	 * Questo metodo legge il File e passa a eseguire il file, i cicli try/catch servono a contemplate tutti i casi 
	 * dei vari filtri, dato che la chiave nella coppia chiave/valore deve essere passata dal programmatore.
	 * 
	 * v è il vettore contenente le città richieste.
	 * a è il vettore contenente i valori della precisione richiesta.
	 * 
	 * @param json di tipo File
	 */
	public void parsing(File json) {
	
		 
		
	 @SuppressWarnings("deprecation")
	        JSONParser parser = new JSONParser();

	   
	        Object obj = null;
			try {
				obj = parser.parse(new FileReader(json));
			} catch (FileNotFoundException | ParseException e) {
				
				System.out.println("Errore di lettura file");
			}

	      // "name" : {"$not" : [Roma]}
			
	        JSONObject jsonObject = (JSONObject) obj;
	        System.out.println(jsonObject); 
	        
	        
	        JSONObject name = (JSONObject) jsonObject.get("name");
	        
	        JSONArray eq;
			Iterator<Object> iterator;
			
		
			try {
				eq = (JSONArray) name.get("$eq");
				Vector<String> v1 = new Vector<String>();
				iterator = eq.iterator();
				while (iterator.hasNext()) {
					v1.add((String) iterator.next());
         }
				@SuppressWarnings("unused")
				ConditionalFilterImpl<String> filter1 = new  ConditionalFilterImpl<String>("$eq", v1);
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			}
       
			
	        try {
				JSONArray not = (JSONArray) name.get("$not");
				Vector<String> v2 = new Vector<String>();
				Iterator<Object> iterator2 = not.iterator();
				while (iterator2.hasNext()) {
					v2.add((String) iterator2.next());
				}
				@SuppressWarnings("unused")
				ConditionalFilterImpl<String> filter2 = new  ConditionalFilterImpl<String>("$not", v2);
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			}
            	
	        try {
				
				JSONArray nin = (JSONArray) name.get("$nin");
				Vector<String> v3 = new Vector<String>();
				Iterator<Object> iterator3 = nin.iterator();
				while (iterator3.hasNext()) {
					v3.add((String) iterator3.next());
				}
				@SuppressWarnings("unused")
				ConditionalFilterImpl<String> filter3 = new  ConditionalFilterImpl<String>("$nin", v3);
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			}
            
	       
			try {
				
				JSONArray in = (JSONArray) name.get("$in");
				Vector<String> v4 = new Vector<String>();
				Iterator<Object> iterator4 = in.iterator();
				while (iterator4.hasNext()) {
					v4.add((String) iterator4.next());
				}
				@SuppressWarnings("unused")
				ConditionalFilterImpl<String> filter4 = new  ConditionalFilterImpl<String>("$in", v4);
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			}
	        
	        
	        
	       
	        JSONObject accouracy = (JSONObject) jsonObject.get("accouracy");
	        
	        Vector<Double> a;
	        
	        try {
	            JSONArray gt;
			   
		
				gt = (JSONArray) accouracy.get("$gt"); 
				a = new Vector<Double>();
				for(int i=0; i<gt.size(); i++) {
					a.add((Double) gt.get(i));	
				}
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	        
	        
	        
	        
	        
	        try {
				JSONArray gte = (JSONArray) accouracy.get("$gte"); 
				Vector<Double> a2 = new Vector<Double>();
				for(int i=0; i<gte.size(); i++) {
					a2.add((Double) gte.get(i));	
				}
				if(a2 != null) a = a2;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	        
	        
	        
	      
	        
	        
	        try {
				JSONArray lt = (JSONArray) accouracy.get("$lt"); 
				Vector<Double> a3 = new Vector<Double>();
				for(int i=0; i<lt.size(); i++) {
					a3.add((Double) lt.get(i));	
				}
				if(a3 != null) a = a3;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	        
	        
	        
	       
	        
	        try {
				JSONArray lte = (JSONArray) accouracy.get("$lte"); 
				Vector<Double> a4 = new Vector<Double>();
				for(int i=0; i<lte.size(); i++) {
					a4.add((Double) lte.get(i));	
				}
				if(a4 != null) a = a4;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	        
	        
	        
	       
	        try {
				JSONArray bt = (JSONArray) accouracy.get("$bt"); 
				Vector<Double> a5 = new Vector<Double>();
				for(int i=0; i<bt.size(); i++) {
					a5.add((Double) bt.get(i));	
				}
				if(a5 != null) a = a5;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
    
	       

  }
}