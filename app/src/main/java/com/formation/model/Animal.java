package com.formation.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Animal implements Serializable {
    @Getter @Setter public long id;
    @Getter @Setter private String diet;
    @Getter @Setter private String family;
    @Getter @Setter private String name;
    @Getter @Setter private String sex;
    @Getter @Setter private int age;

    public Animal(long id, String diet, String family, String name, String sex, int age) {
        super();
        this.id = id;
        this.diet = diet;
        this.family = family;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return diet + " " + family + " " + name + " " + sex + " " + Integer.toString(age);
    }

}
