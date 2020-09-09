package animals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private final Animal animal = new Cat("Oom Boudewijn", Gender.MALE, "Drinkt Teveel");

    @Test
    public void TestConstructor() {
        assertEquals("Oom Boudewijn", this.animal.getName());
        assertEquals(Gender.MALE, this.animal.getGender());
        assertNull(this.animal.getReservedBy());
    }

    @Test
    public void TestReservation() {
        assertNull(this.animal.getReservedBy());
        assertTrue(this.animal.Reserve("John Doe"));
        assertNotNull(this.animal.getReservedBy());
        assertEquals("John Doe", this.animal.getReservedBy().getName());
        assertFalse(this.animal.Reserve("Jane Doe"));
    }
}
