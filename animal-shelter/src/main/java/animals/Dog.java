package animals;

import java.util.Date;

public class Dog extends Animal {

    private Date lastWalk;
    private static int counter = 0;
    private int number;

    public Dog(String name, Gender gender) {
        super(name, gender);
        this.lastWalk = new Date();
        this.number = counter;
        counter++;
    }

    public boolean needsWalk() {
        Date today = new Date();
        return (today.getTime() - lastWalk.getTime()) > 86400000;
    }

    public Date getLastWalk() {
        return lastWalk;
    }

    @Override
    public double getPrice() {
        double initialPrice = 500;
        double minPrice = 50;

        initialPrice -= 50 * number;

        return Math.max(initialPrice, minPrice);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
