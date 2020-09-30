package animals;

public class Cat extends Animal {

    private String badHabits;

    public Cat(String name, Gender gender, String badHabits) {
        super(name, gender);
        species = Species.CAT;
        this.badHabits = badHabits;
    }

    public String getBadHabits() {
        return badHabits;
    }

    @Override
    public double getPrice() {
        double initialPrice = 350;
        double minPrice = 35;

        for (int character = 0; character < this.badHabits.length(); character++) {
            initialPrice -= 20;
        }

        return Math.max(initialPrice, minPrice);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + badHabits;
    }
}
