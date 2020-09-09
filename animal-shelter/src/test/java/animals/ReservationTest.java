package animals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationTest {

    private final Reservation reservation = new Reservation();

    @Test
    public void TestNewCat() {
        assertEquals(0, this.reservation.getAnimals().size());
        this.reservation.newCat("Ms. Meow", Gender.FEMALE, "Scratches Couch");
        assertEquals(1, this.reservation.getAnimals().size());
    }

    @Test
    public void TestNewDog() {
        assertEquals(0, this.reservation.getAnimals().size());
        this.reservation.newDog("Sgt. Woof", Gender.MALE);
        assertEquals(1, this.reservation.getAnimals().size());
    }
}
