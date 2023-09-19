package fact.it.zoo.model;

// Jef Keppens
// R0885867
public class AnimalWorld {
    private String name;
    private String photo;
    private int numberOfAnimals;
    private Staff staff;

    public AnimalWorld() {
    }

    public AnimalWorld(String name) {
        this.name = name;
    }

    public AnimalWorld(String name, int numberOfAnimals) {
        this.name = name;
        this.numberOfAnimals = numberOfAnimals;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setNumberOfAnimals(int numberOfAnimals) {
        this.numberOfAnimals = numberOfAnimals;
    }

    public Staff getResponsible() {
        return this.staff;
    }

    public void setResponsible(Staff responsible) {
        this.staff = responsible;
    }
}
