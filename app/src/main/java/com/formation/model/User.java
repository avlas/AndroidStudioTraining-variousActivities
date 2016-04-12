package com.formation.model;

import java.io.Serializable;

public class User implements Serializable {
	public String id;
	private int photo;
	private String firstName;
	private String lastName;
	private boolean isActive;
	
	public User(){		
	}
	
	public User(String id, String firstName, String lastName, int photo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
		this.isActive = false;
	}

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return photo + " " + firstName + " " + lastName + " " + isActive;
	}

}
