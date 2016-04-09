package com.test.project;

import javax.persistence.EntityManager;

public class CustomService {

	public static EntityManager getEntityManager(){
		
		return EMF.get().createEntityManager();
	}
	
	
}
