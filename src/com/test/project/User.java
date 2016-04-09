package com.test.project;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="seq", initialValue=1,allocationSize=100)
public class User {

public User(int age, String fName, String lName, String email) {
	this.age = age;
	this.firstName = fName;
	this.lastName = lName;
	this.email = email;
}


@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq")
private Long id;
private int age;
private String firstName;
private String lastName;
private String email;
private List<Long> hotelId= new ArrayList<Long>();
private List<Integer> starRatings= new ArrayList<Integer>();
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public List<Long> getHotelId() {
	return hotelId;
}
public void addHotelId(Long hotelId) {
	this.hotelId.add(hotelId);
}
public List<Integer> getStarRatings() {
	return starRatings;
}
public void addStarRatings(int starRatings) {
	this.starRatings.add(starRatings);
}




}
