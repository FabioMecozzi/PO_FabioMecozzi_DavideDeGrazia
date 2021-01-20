package com.example2.demo2.modelTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example2.demo2.model.CollectionUpdate;

public class CollectionUpdateTests {
	
	@BeforeEach
	void setUp() throws Exception {}
	
	@AfterEach 
	void tearDown() throws Exception {}
	
	@SuppressWarnings("unchecked")
	@Test
	void test1 () {
		
		
		String percorso = "{\"delteAll\":false,”savingList”:[1,5,8,24],\"deletingList”:[528,756,999]}";
		
	    // File filejson = new File(percorso);      
		                         		
		        ArrayList <Integer> savingList = new ArrayList<Integer>();
		        savingList.add(1);
		        savingList.add(5);
		        savingList.add(8);
		        savingList.add(24);
		        
		        ArrayList<Integer> deletingList = new ArrayList<Integer>();
		        deletingList.add(528);
		        deletingList.add(756);
		        deletingList.add(999);
		        
         @SuppressWarnings("rawtypes")
		 
         CollectionUpdate ciao;
		        
		 ciao = new CollectionUpdate(false, savingList, deletingList);
		 
		 CollectionUpdate ciao2;
		 
		 ciao2 = new CollectionUpdate(percorso);
		 
		 assertTrue(ciao.equals(ciao2));
		
	
	}
}
	
	


