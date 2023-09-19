package fact.it.zoo.model;

import java.util.ArrayList;

// Jef Keppens
// r0885867
public class Zoo {
    private String name;
    private int numberVisitors;

    private ArrayList <AnimalWorld> animalWorlds = new ArrayList<>();


    public Zoo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumberVisitors() {
        return numberVisitors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList <AnimalWorld> getAnimalWorlds() {
        return this.animalWorlds;
    }

    public int getNumberOfAnimalWorlds() {
        return animalWorlds.size();
    }

    public void addAnimalWorld(AnimalWorld animalWorld) {
        this.animalWorlds.add(animalWorld);
    }

    public AnimalWorld searchAnimalWorldByName(String name) {
        for (int i = 0; i < animalWorlds.size(); i++) {
            if (animalWorlds.get(i).getName().equals(name)) {
                return animalWorlds.get(i);
            }
        }
        return null;
    }

    public void registerVisitor(Visitor visitor) {
        this.numberVisitors += 1;
        visitor.setPersonalCode(this.name.substring(0, 2) + this.numberVisitors);
    }
}
