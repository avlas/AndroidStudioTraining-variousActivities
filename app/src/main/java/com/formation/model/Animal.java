package com.formation.model;

import java.io.Serializable;

public class Animal implements Serializable {
	public long id;
	private String diet;
	private String family;
	private String name;
	private String sex;
	private int age;

	public Animal(){
	}

	public Animal(long id, String diet, String family, String name, String sex, int age) {
		super();
		this.id = id;
		this.diet = diet;
		this.family = family;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return diet + " " + family + " " + name + " " + sex + " " + Integer.toString(age);
	}

}
