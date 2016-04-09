package com.test.project;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;



public class HotelService extends CustomService {
	public boolean Insert(Hotel hotel)
	{

		EntityManager mgr =null;
		boolean issuccess =false;
		try{
			mgr = getEntityManager();

			mgr.persist(hotel);
			issuccess =true;
		}
		finally{
			mgr.close();
		}
		return issuccess;

	}
	
	@SuppressWarnings("unchecked")
	public List<Hotel> listHotels()
	{
		EntityManager mgr=null;
		List<Hotel> hotels=null;
		
		try{
			mgr=getEntityManager();
			
			Query query=mgr.createQuery("Select From Hotel as Hotel");
			hotels=(List<Hotel>)query.getResultList();
			
			
		}finally{
			mgr.close();
		}
		return hotels;
	}
	
	
	
	public void updateHotel(Long hotelId, int numStars)
	{
		EntityManager mgr = null;
		
		
		try{
			mgr = getEntityManager();
			Hotel hotel = mgr.find(Hotel.class,hotelId);
			int newNumStars =  (hotel.getNumOfUsers()*hotel.getNumStars()+numStars)/(hotel.getNumOfUsers()+1);
			hotel.setNumOfUsers(hotel.getNumOfUsers()+1);
			hotel.setNumStars(newNumStars);
			
			
		}
		finally {
			
			mgr.close();
		}
		
		
	}


	
	}

	
