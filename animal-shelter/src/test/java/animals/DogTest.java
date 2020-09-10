package animals;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {
    private final Dog dogOne = new Dog("Sgt. Woof", Gender.MALE);
    private final Dog dogTwo = new Dog("Sgt. Woof", Gender.MALE);

    @Test
    public void TestConstructor() {
        assertEquals("Sgt. Woof", this.dogOne.getName());
        assertEquals(Gender.MALE, this.dogOne.getGender());
        assertNull(this.dogOne.getReservedBy());
        Date today = new Date();
        assertTrue(today.getTime() - this.dogOne.getLastWalk().getTime() < 5000);
        assertFalse(this.dogOne.needsWalk());
    }

    @Test
    public void TestReservation() {
        assertNull(this.dogOne.getReservedBy());
        assertTrue(this.dogOne.Reserve("John Doe"));
        assertNotNull(this.dogOne.getReservedBy());
        assertEquals("John Doe", this.dogOne.getReservedBy().getName());
        assertFalse(this.dogOne.Reserve("Jane Doe"));
    }

    @Test
    public void TestGetPrice() {
        assertEquals(500, this.dogOne.getPrice());
        assertEquals(450, this.dogTwo.getPrice());
        dogTwo.setNumber(20);
        assertEquals(50, this.dogTwo.getPrice());
    }
}
