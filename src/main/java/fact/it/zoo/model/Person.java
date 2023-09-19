package fact.it.zoo.model;


import java.util.Locale;

// Jef Keppens
// R0885867
public class Person {
    private String firstName;
    private String surName;

    public Person() {
    }

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String toString() {
        return this.surName.toUpperCase() + " " + this.firstName;
    }
}
