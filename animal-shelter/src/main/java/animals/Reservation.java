package animals;

import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private List<Animal> animals;

    public Reservation() {
        animals = new ArrayList<>();
    }

    public void newCat(String name, Gender gender, String badHabits) {
        this.animals.add(new Cat(name, gender, badHabits));
    }

    public void newDog(String name, Gender gender) {
        this.animals.add(new Dog(name, gender));
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
