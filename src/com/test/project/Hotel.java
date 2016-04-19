package com.test.project;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private int numRooms;
	private int numStars;
	private int numOfUsers;
	
	
	
	
	public Hotel( String name, int numRooms, int numStars) {


		this.name = name;
		this.numRooms = numRooms;
		this.numStars = numStars;
		this.numOfUsers =0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumRooms() {
		return numRooms;
	}
	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}
	public int getNumStars() {
		return numStars;
	}
	public void setNumStars(int numStars) {
		this.numStars = numStars;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumOfUsers() {
		return numOfUsers;
	}
	public void setNumOfUsers(int numOfUsers) {
		this.numOfUsers = numOfUsers;
	}
	
	
	
	

}
