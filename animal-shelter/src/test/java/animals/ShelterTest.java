package animals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShelterTest {

    private final Shelter shelter = Shelter.getInstance();

    @Test
    public void TestNewCat() {
        assertEquals(0, this.shelter.getAnimals().size());
        this.shelter.newCat("Ms. Meow", Gender.FEMALE, "Scratches Couch");
        assertEquals(1, this.shelter.getAnimals().size());
    }

    @Test
    public void TestNewDog() {
        assertEquals(0, this.shelter.getAnimals().size());
        this.shelter.newDog("Sgt. Woof", Gender.MALE);
        assertEquals(1, this.shelter.getAnimals().size());
    }
}
