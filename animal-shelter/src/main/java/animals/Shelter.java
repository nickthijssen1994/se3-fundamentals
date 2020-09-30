package animals;

import utilities.Observable;

import java.util.ArrayList;
import java.util.List;

public class Shelter extends Observable {

    private final List<Animal> animals;

    public Shelter() {
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        notifyObservers();
    }

    public void newCat(String name, Gender gender, String badHabits) {
        animals.add(new Cat(name, gender, badHabits));
        notifyObservers();
    }

    public void newDog(String name, Gender gender) {
        animals.add(new Dog(name, gender));
        notifyObservers();
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
