package fact.it.zoo;

import fact.it.zoo.model.AnimalWorld;
import fact.it.zoo.model.Visitor;
import fact.it.zoo.model.Zoo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ZooTests {
    /**
     * Test of constructor en getters
     */
    @Test
    public void testConstructorEnGetters() {
        Zoo zoo = new Zoo("Antwerp Zoo");
        assertEquals("Antwerp Zoo", zoo.getName());
    }

    /**
     * Test of setName method, of class Zoo.
     */
    @Test
    public void testSetName() {
        Zoo zoo = new Zoo("Zoo Antwerp");
        zoo.setName("Bellewaerde");
        assertEquals("Bellewaerde", zoo.getName());
    }

    /**
     * check constructor of Zoo + adding of animal world check if you can
     * find an animal world by name
     */
    @Test
    public void checkAddAnimalWorldsAndGetNumber() {
        Zoo zoo1 = new Zoo("Bellewaerde");
        assertEquals(0, zoo1.getNumberOfAnimalWorlds());
        AnimalWorld animalWorld = new AnimalWorld("Aquarium");
        zoo1.addAnimalWorld(animalWorld);
        assertEquals(1, zoo1.getNumberOfAnimalWorlds());
        AnimalWorld animalWorld1 = new AnimalWorld("Reptiles");
        zoo1.addAnimalWorld(animalWorld1);
        assertEquals(2, zoo1.getNumberOfAnimalWorlds());
        AnimalWorld[] array = new AnimalWorld[2];
        array[0] = animalWorld;
        array[1] = animalWorld1;
        assertArrayEquals(array, zoo1.getAnimalWorlds().toArray());
    }

    /**
     * Test of search by name
     */
    @Test
    public void testSearchAnimalWorldbyName() {
        Zoo zoo2 = new Zoo("Antwerp Zoo");
        assertEquals(0, zoo2.getNumberOfAnimalWorlds());
        AnimalWorld aquarium = new AnimalWorld("Aquarium");
        zoo2.addAnimalWorld(aquarium);
        assertEquals(1, zoo2.getNumberOfAnimalWorlds());
        AnimalWorld reptiles = new AnimalWorld("Reptiles");
        zoo2.addAnimalWorld(reptiles);
        assertEquals(2, zoo2.getNumberOfAnimalWorlds());
        assertNotNull(zoo2.searchAnimalWorldByName("Reptiles"));
        assertEquals(reptiles.getName(), zoo2.searchAnimalWorldByName("Reptiles").getName());
        assertNull(zoo2.searchAnimalWorldByName("blabla"));
    }

    /**
     * before a Visitor can go to the zoo, he has to register to the
     * zoo, as result of this registration he will receive a code
     */
    @Test
    public void testRegisterVisitor() {
        Visitor visitor = new Visitor("Donald", "Duck");
        Zoo zoo = new Zoo("Antwerp Zoo");
        zoo.registerVisitor(visitor);
        assertEquals("An1", visitor.getPersonalCode());
        Visitor visitor1 = new Visitor("Mickey", "Mouse");
        zoo.registerVisitor(visitor1);
        assertEquals("An2", visitor1.getPersonalCode());
        assertEquals(2, zoo.getNumberVisitors());
    }
    @Test
    public void testRegisterVisitor2() {
        Visitor visitor = new Visitor("Donald", "Duck");
        Zoo zoo = new Zoo("Antwerp Zoo");
        zoo.registerVisitor(visitor);
        assertEquals("An1", visitor.getPersonalCode());
        assertEquals(1, zoo.getNumberVisitors());
        zoo.registerVisitor(visitor);
        assertEquals("An1", visitor.getPersonalCode());
        assertEquals(2, zoo.getNumberVisitors());
        Visitor visitor1 = new Visitor("Mickey", "Mouse");
        zoo.registerVisitor(visitor1);
        assertEquals("An3", visitor1.getPersonalCode());
        assertEquals(3, zoo.getNumberVisitors());
    }
}
