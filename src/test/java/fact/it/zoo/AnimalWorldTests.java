package fact.it.zoo;

import fact.it.zoo.model.AnimalWorld;
import fact.it.zoo.model.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnimalWorldTests {
    /**
     * Test of constructor en getters
     */
    @Test
    public void testConstructorEnGetters() {
        AnimalWorld animalWorld = new AnimalWorld("Aquarium");
        assertEquals("Aquarium", animalWorld.getName());
        AnimalWorld animalWorld1 = new AnimalWorld();
        assertEquals(null,animalWorld1.getName());
        AnimalWorld animalWorld2 = new AnimalWorld("Birds");
        assertEquals("Birds", animalWorld2.getName());
    }

    /**
     * Test of setName method, of class AnimalWorld.
     */
    @Test
    public void testSetNaam() {
        AnimalWorld animalWorld = new AnimalWorld();
        animalWorld.setName("Aquarium");
        assertEquals("Aquarium", animalWorld.getName());
    }


    /**
     * Test of setPhoto method, of class AnimalWorld.
     */
    @Test
    public void testSetPhoto() {
        AnimalWorld animalWorld = new AnimalWorld("Aquarium");
        animalWorld.setPhoto("testphoto.jpg");
        assertEquals("testphoto.jpg", animalWorld.getPhoto());
    }

    /**
     * Test of setResponsible method, of class AnimalWorld.
     */
    @Test
    public void testResponsible() {
        AnimalWorld animalWorld = new AnimalWorld("Aquarium");
        Staff Mickey = new Staff("Mickey", "Mouse");
        animalWorld.setResponsible(Mickey);
        assertEquals(Mickey, animalWorld.getResponsible());
    }
}
