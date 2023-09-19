package fact.it.zoo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Jef Keppens
// r0885867
public class Staff extends Person{
    private LocalDate startDate;
    private boolean student;

    public Staff(String firstName, String surName) {
        super(firstName, surName);
        this.startDate = LocalDate.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (student) {
            return "Staff member " + super.toString() + " (working student) is employed since " + startDate.format(dtf);
        }
        return "Staff member " + super.toString() + " is employed since " + startDate.format(dtf);
    }
}
