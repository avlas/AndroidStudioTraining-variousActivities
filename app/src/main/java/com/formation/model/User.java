package com.formation.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class User implements Serializable {
    @Getter @Setter public String id;
    @Getter @Setter private int photo;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private boolean isActive;

    public User(String id, String firstName, String lastName, int photo) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.isActive = false;
    }

    @Override
    public String toString() {
        return photo + " " + firstName + " " + lastName + " " + isActive;
    }

}
