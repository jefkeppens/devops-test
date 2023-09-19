package fact.it.zoo.model;

import java.util.ArrayList;

// Jef Keppens
// r0885867
public class Visitor extends Person{
    private String personalCode;
    private int yearOfBirth;
    private ArrayList <String> wishList = new ArrayList<>();
    private Staff guide;

    public Visitor(String firstName, String surName) {
        super(firstName, surName);
        this.personalCode = "undefined";
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public ArrayList<String> getWishList() {
        return wishList;
    }

    public void setPersonalCode(String personalCode) {
        if (this.personalCode.equals("undefined")) {
            this.personalCode = personalCode;
        }
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public boolean addToWishList(String animal) {
        if (wishList.size() < 5) {
            wishList.add(animal);
            return true;
        }
        return false;
    }

    public int getNumberOfWishes() {
        return this.wishList.size();
    }

    public String toString() {
        return "Visitor " + super.toString() + " with personal code " + this.personalCode;
    }

    public Staff getGuide() {
        return this.guide;
    }

    public void setGuide(Staff guide) {
        this.guide = guide;
    }
}
