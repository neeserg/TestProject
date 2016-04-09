package com.test.project;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserService extends CustomService{

	public boolean insertUser(User user){

		EntityManager mgr=null;
		HotelService hotelService = new HotelService();
		mgr=getEntityManager();
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		boolean userInserted=false;
		try{
			
			Long hotelId = user.getHotelId().get(user.getHotelId().size()-1);
			int numStars = user.getStarRatings().get(user.getStarRatings().size()-1);
			mgr.persist(user);
			hotelService.updateHotel(hotelId, numStars);
			txn.commit();
			userInserted=true;
		}finally{
			if(txn.isActive())
			{
				txn.rollback();
			}
			mgr.close();
		}
		return userInserted;

	}
	
	
}
