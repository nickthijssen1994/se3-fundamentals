package animals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {
    private final Cat cat = new Cat("Ms. Meow", Gender.FEMALE, "Scratches Couch");

    @Test
    public void TestConstructor() {
        assertEquals("Ms. Meow", this.cat.getName());
        assertEquals(Gender.FEMALE, this.cat.getGender());
        assertEquals("Scratches Couch", this.cat.getBadHabits());
        assertNull(this.cat.getReservedBy());
    }

    @Test
    public void TestReservation() {
        assertNull(this.cat.getReservedBy());
        assertTrue(this.cat.reserve("John Doe"));
        assertNotNull(this.cat.getReservedBy());
        assertEquals("John Doe", this.cat.getReservedBy().getName());
        assertFalse(this.cat.reserve("Jane Doe"));
    }

    @Test
    public void TestGetPrice() {
        assertEquals(50, this.cat.getPrice());
    }
}
