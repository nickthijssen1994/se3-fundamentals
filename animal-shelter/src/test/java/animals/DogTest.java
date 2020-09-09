package animals;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {
    private final Dog dog = new Dog("Sgt. Woof", Gender.MALE);

    @Test
    public void TestConstructor() {
        assertEquals("Sgt. Woof", this.dog.getName());
        assertEquals(Gender.MALE, this.dog.getGender());
        assertNull(this.dog.getReservedBy());
        Date today = new Date();
        assertEquals(today, this.dog.getLastWalk());
        assertFalse(this.dog.needsWalk());
    }

    @Test
    public void TestReservation() {
        assertNull(this.dog.getReservedBy());
        assertTrue(this.dog.Reserve("John Doe"));
        assertNotNull(this.dog.getReservedBy());
        assertEquals("John Doe", this.dog.getReservedBy().getName());
        assertFalse(this.dog.Reserve("Jane Doe"));
    }
}
