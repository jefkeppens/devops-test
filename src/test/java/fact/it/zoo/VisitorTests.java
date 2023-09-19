package fact.it.zoo;

import fact.it.zoo.model.Visitor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VisitorTests {
    /**
     * Test of constructor en getters
     */
    @Test
    public void testConstructorEnGetters() {

        Visitor visitor = new Visitor("Donald", "Duck");
        assertEquals("Donald", visitor.getFirstName());
        assertEquals("Duck", visitor.getSurName());
        assertEquals("undefined", visitor.getPersonalCode());
        assertEquals(0, visitor.getYearOfBirth());
    }

    /**
     * Test of setFirstName method, of class Visitor.
     */
    @Test
    public void testSetFirstName() {
        Visitor visitor = new Visitor("Donald", "Duck");
        visitor.setFirstName("Mickey");
        assertEquals("Mickey", visitor.getFirstName());
    }

    /**
     * Test of setSurName method, of class Visitor.
     */
    @Test
    public void testSetSurName() {
        Visitor visitor = new Visitor("Donald", "Duck");
        visitor.setSurName("Mouse");
        assertEquals("Mouse", visitor.getSurName());
    }

    /**
     * Test of setPersonalCode method, of class Visitor.
     */
    @Test
    public void testSetZooNumber() {
        Visitor visitor = new Visitor("Donald", "Duck");
        visitor.setPersonalCode("An2");
        assertEquals("An2", visitor.getPersonalCode());
    }

    @Test
    public void testSetZooNumber2() {
        Visitor visitor = new Visitor("Donald", "Duck");
        visitor.setPersonalCode("An2");
        assertEquals("An2", visitor.getPersonalCode());
        visitor.setPersonalCode("Be2");
        assertEquals("An2", visitor.getPersonalCode());
    }
    /**
     * Test of setYearOfBirth of class Visitor.
     */
    @Test
    public void testSetYearOfBirth() {
        Visitor visitor = new Visitor("Donald", "Duck");
        visitor.setYearOfBirth(2015);
        assertEquals(2015, visitor.getYearOfBirth());
    }

    /**
     * check basic functionality of Visitor: constructor, setters/getters and
     * adding animal world to wishlist
     */
    @Test
    public void testWishListBasics() {
        Visitor visitor = new Visitor("Donald", "Duck");
        assertEquals(0, visitor.getNumberOfWishes());
        assertTrue(visitor.addToWishList("Aquarium"));
        assertEquals(1, visitor.getNumberOfWishes());
    }

    /**
     * Visitor can add maximum 5 animal world names to wishlist
     */
    @Test
    public void testWishListMax5() {
        Visitor visitor = new Visitor("Donald", "Duck");
        assertEquals(0, visitor.getNumberOfWishes());
        assertTrue(visitor.addToWishList("Aquarium"));
        assertEquals(1, visitor.getNumberOfWishes());
        assertTrue(visitor.addToWishList("Reptiles"));
        assertEquals(2, visitor.getNumberOfWishes());
        assertTrue(visitor.addToWishList("Monkeys"));
        assertEquals(3, visitor.getNumberOfWishes());
        assertTrue(visitor.addToWishList("Hippotopia"));
        assertEquals(4, visitor.getNumberOfWishes());
        assertTrue(visitor.addToWishList("Savannah"));
        assertEquals(5, visitor.getNumberOfWishes());
        assertFalse(visitor.addToWishList("Birds"));
        assertEquals(5, visitor.getNumberOfWishes());
    }

    /**
     * Test of toString method, of class Visitor.
     */
    @Test
    public void testToString() {
        Visitor donald = new Visitor("Donald", "Duck");
        donald.setPersonalCode("Be5");
        assertEquals("Visitor DUCK Donald with personal code Be5", donald.toString());
    }
}
